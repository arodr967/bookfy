/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookfy;

import com.jfoenix.controls.JFXTextArea;
import java.awt.Font;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Leo
 */
public class FXMLViewProfileController implements Initializable {
    @FXML
    private Label userName;
    @FXML
    private Label name;
    @FXML
    private Label email;
    @FXML
    private Label address1;
    @FXML
    private Label editCreditCard;
    @FXML
    private Label displayCreditCard1;
    @FXML
    private Label displayCreditCard;
    @FXML
    private Label displayShipping;
    @FXML
    private Label editShippingInfo;
    @FXML
    private Label editUserAccount;
    @FXML
    private Label uploadImage;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        User user = Bookfy.getUser();
        CreditCard creditCardNumber = Bookfy.getCreditCardNumber();
        Address address = Bookfy.getUserAddress();
        name.setText(user.getFirstName()+ " " + user.getLastName());
        userName.setText(user.getUserName());
        email.setText(user.getEmail());
        //System.out.println(creditCardNumber.getNumber());
        displayCreditCard.setText(user.getFirstName() + " " + user.getLastName() + "\n" + "Card Ending in " /*+ creditCardNumber.getNumber()*/);
        displayCreditCard1.setText(user.getFirstName() + " " + user.getLastName() + "\n" + "Card Ending in "  /*+ creditCardNumber.getNumber()*/);
        displayShipping.setText(user.getFirstName() + " " + user.getLastName() + "\n" /*+ address.getUserAddress()*/);
        
        
        
       
        
        
        
    } 
    @FXML
    void editCreditCard(MouseEvent event)
    {
       //Bookfy.getMainWindowController().???();   What ever the edit credit window will be
       
    }
    @FXML
    void editShippingInfo(MouseEvent event)
    {
       //Bookfy.getMainWindowController().???();   What ever the edit shipping window will be
       
    }
    @FXML
    void editAccountInfo(MouseEvent event)
    {
       //Bookfy.getMainWindowController().???();   What ever the edit account window will be
       
    }
    @FXML
    void uploadUserImage(MouseEvent event)
    {
       //Bookfy.getMainWindowController().???();   What ever the upload user image window will be
       
    }
    
   
    
}
