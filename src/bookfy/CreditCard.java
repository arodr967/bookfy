/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookfy;

/**
 *
 * @author Roger Boza
 */
public class CreditCard {
    private String name;
    private String number;
    private String expiration;
    private String cvv;
    private int cCID;
    private Address billing;

    public CreditCard(int cCID, String name, String number, String expiration, String cvv, Address billing) {
        this.cCID = cCID;
        this.name = name;
        this.number = number;
        this.expiration = expiration;
        this.cvv = cvv;
        this.billing = billing;
    }

    public int getcCID() {
        return cCID;
    }

    public void setcCID(int cCID) {
        this.cCID = cCID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }
    
    public String getLast4Digits() {
        int len = number.length();
        return number.substring(len-4, len);
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public Address getBilling() {
        return billing;
    }

    public void setBilling(Address billing) {
        this.billing = billing;
    }
    
    public void saveCardIntoDB(){
        User user = Bookfy.getUser();
        String sql = "INSERT INTO creditcard (UserID, FullName, CardNumber, Expiration, CVV, AddressLine1, AddressLine2, City, State, Country)";
        sql += " VALUES('" + user.getUserName() + "', '" + name + "', '" + number + "', '" + expiration + "', '" + cvv + "', '" + billing.getAddressLine1() + "', '"  + billing.getAddressLine2() + "', '" + billing.getCity() + "', '" + billing.getState() + "', '" + billing.getCountry() + "')";
        
        Bookfy.getDatabaseHandler().execAction(sql);
    }
    
    public void updateCardInDB(){
        if(cCID == 0){
            return;
        }

        String sql = "UPDATE creditcard "
                    + "SET FullName='" + name + "',"
                    + "Expiration='"+ expiration + "',"
                    + "CVV='"+ cvv + "',"
                    + "AddressLine1='"+ billing.getAddressLine1() + "',"
                    + "AddressLine2='"+ billing.getAddressLine2() + "',"
                    + "City='"+ billing.getCity() + "',"
                    + "State='"+ billing.getState() + "',"
                    + "Country='" + billing.getCountry() 
                    + "' WHERE ccID=" + cCID + ";";
        
        Bookfy.getDatabaseHandler().execAction(sql);
    }
    
    public void deactivate(){
        if(cCID == 0){
            return;
        }
         String query = "UPDATE creditcard SET LazyDelete = '" + 1 + "' WHERE ccID=" + cCID + ";";
         Bookfy.getDatabaseHandler().execAction(query);
    }
}
