/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookfy;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Roger Boza
 */
public class User {
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    
    private Address home;
    private Address shipping;
    
    private ArrayList<CreditCard> creditCards;

    //brand new user. Minimum data. Save into the database
    public User(String firstName, String lastName, String userName, String password, String email, Address home) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.home = home;
        
        //saveBasicUserData(password);
    }

    //user already exist so load it from the database
    public User(String userName) {
        this.userName = userName;
        creditCards = new ArrayList<>();
        loadUserData();
    }

    public String getFullName(){
        return getFirstName() + " " + getLastName();
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getHome() {
        return home;
    }

    public void setHome(Address home) {
        this.home = home;
    }

    public Address getShipping() {
        return shipping;
    }

    public void setShipping(Address shipping) {
        this.shipping = shipping;
    }

    public ArrayList<CreditCard> getCreditCards() {
        return creditCards;
    }
    
    private void loadUserData(){
        String query = "SELECT FirstName, LastName, email, AddressLine, City, State, Country FROM user where UserID = \'" + userName + "\'";

        ResultSet rs = Bookfy.getDatabaseHandler().execQuery(query);

        try {
            if(rs.next()){
                firstName = rs.getString(1);
                lastName = rs.getString(2);
                email = rs.getString(3);
                String addressLine = rs.getString(4);
                String city = rs.getString(5);
                String state = rs.getString(6);
                String country = rs.getString(7);
                home = new Address(firstName + " " + lastName, addressLine, "", city, state, 0, country);
            }
            
            loadShippingInfo();
            loadCreditCards();
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //using a method in case the user wants multiple shipping addresses in the future
    private void loadShippingInfo(){
        String query = "SELECT FullName, AddressLine1, AddressLine2, City, State, Country FROM shipping where UserID = \'" + userName + "\'";

        ResultSet rs = Bookfy.getDatabaseHandler().execQuery(query);

        try {
            if(rs.next()){
                String fullname = rs.getString(1);
                String addressLine1 = rs.getString(2);
                String addressLine2 = rs.getString(3);
                String city = rs.getString(4);
                String state = rs.getString(5);
                String country = rs.getString(6);
                shipping = new Address(fullname, addressLine1, addressLine2, city, state, 0, country);
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadCreditCards(){
        creditCards.clear();
        
        String query = "SELECT FullName, CardNumber, Expiration, CVV, AddressLine1, AddressLine2, City, State, Country, ccID FROM creditcard where UserID = \'" + userName + "\' AND LazyDelete = '0'";

        ResultSet rs = Bookfy.getDatabaseHandler().execQuery(query);

        try {
            while(rs.next()){
                String fullname = rs.getString(1);
                String number = rs.getString(2);
                String expiration = rs.getString(3);
                String cvv = rs.getString(4);
                String addressLine1 = rs.getString(5);
                String addressLine2 = rs.getString(6);
                String city = rs.getString(7);
                String state = rs.getString(8);
                String country = rs.getString(9);
                int ccID = rs.getInt(10);
                Address billing = new Address(fullname, addressLine1, addressLine2, city, state, 0, country);
                CreditCard card = new CreditCard(ccID, fullname, number, expiration, cvv, billing);
                
                creditCards.add(card);
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void saveBasicUserData(String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void updateShippingToDB(){
        String query = "SELECT userID FROM shipping WHERE UserID= \'" + userName + "\'";

        ResultSet rs = Bookfy.getDatabaseHandler().execQuery(query);

        try {
            String command;
            if(rs.next()){
                command = "UPDATE shipping "
                    + "SET FullName ='"+ shipping.getName() + "',"
                    + "AddressLine1 ='"+ shipping.getAddressLine1() + "',"
                    + "AddressLine2='"+ shipping.getAddressLine2() + "',"
                    + "City='"+ shipping.getCity() + "',"
                    + "State='"+ shipping.getState() + "',"
                    + "Country='"+ shipping.getCountry() + "'"
                    + " WHERE UserID='" + getUserName() + "'";
            }
            else{
                command = "INSERT into shipping(UserID, FullName, AddressLine1, AddressLine2, City, State, Country) "
                    + " VALUES('" + Bookfy.getUser().getUserName() + "', '" + shipping.getName() + "', '" + shipping.getAddressLine1() + "', '" + shipping.getAddressLine2() + "', '" + shipping.getCity() + "', '" + shipping.getState() + "', '" + shipping.getCountry() + "')";
            }
            Bookfy.getDatabaseHandler().execAction(command);
            JOptionPane.showMessageDialog(null, "The shipping address has been updated.", "Bookfy", JOptionPane.INFORMATION_MESSAGE);
            Bookfy.getMainWindowController().displayViewProfile();
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    public void updateBasicUserData(String password){
        String query = "UPDATE user "
                    + "SET FirstName='"+getFirstName() +"',"
                    + "LastName='"+getLastName() + "',"
                    + "email='"+ getEmail() + "',"
                    + "AddressLine='"+ home.getAddressLine1() + "',"
                    + "City='"+ home.getCity() + "',"
                    + "State='"+ home.getState() + "',"
                    + "Country='"+ home.getCountry() + "',"
                    + "password='" + password 
                    + "' WHERE UserID='" + getUserName() + "';";
        
       Bookfy.getDatabaseHandler().execAction(query);
    }
    
    public void deactivate(){
         String query = "UPDATE user SET LazyDelete = '" + 1 + "' WHERE UserID='" + getUserName() + "';";
         Bookfy.getDatabaseHandler().execAction(query);
    }
}
