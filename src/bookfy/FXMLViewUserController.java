/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookfy;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Roger Boza
 */
public class FXMLViewUserController implements Initializable {

    @FXML
    private Label lblShippingAddress;
    @FXML
    private Label lblHomeAddress;
    @FXML
    private VBox creditCardPane;
    @FXML
    private Label lblName;
    @FXML
    private Label lblUserID;
    @FXML
    private Label lblEmail;
    //load user image
    @FXML
    private ImageView usersImage;
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        User user = Bookfy.getUser();
        displayLabelInfo();
        loadShippingAddress();
        LoadHomeAddress();
        loadCreditCards();
        //load user image into window
        if(user.getUserImage() != null)
        {
            usersImage.setImage(user.getUserImage());
        }
    }
    
    private void displayLabelInfo(){
        User user = Bookfy.getUser();
        lblName.setText(user.getFullName());
        lblUserID.setText(user.getUserName());
        lblEmail.setText(user.getEmail());
    }
    
    private void loadShippingAddress(){
        User user = Bookfy.getUser();
        if(user.getShipping() != null){
            lblShippingAddress.setText(Bookfy.getUser().getShipping().formattedLong());
        }
        else{
            lblShippingAddress.setText("No shipping address on record.");
        }
    }
    
    private void LoadHomeAddress(){
        lblHomeAddress.setText(Bookfy.getUser().getHome().formattedShort());
    }

    private void loadCreditCards(){
        for(CreditCard cc : Bookfy.getUser().getCreditCards()){            
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/bookfy/FXMLCreditCardDisplay.fxml"));
                AnchorPane creditCard = loader.load();
                FXMLCreditCardDisplayController controller = loader.getController();
                controller.setCard(cc);
                
                creditCardPane.getChildren().add(creditCard);
            } catch (IOException ex) {
                Logger.getLogger(FXMLViewUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        //dynamic location of button
        Button btnAddCC = new Button("ADD CREDIT CARD");
        btnAddCC.getStyleClass().add("button-flat");
        btnAddCC.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Bookfy.getMainWindowController().displayCreditCard();
            }
        });
        creditCardPane.getChildren().add(btnAddCC);
    }
    
    @FXML
    private void editUser(ActionEvent event) {
        Bookfy.getMainWindowController().displayEditAccount();
    }

    @FXML
    private void editShippingInfo(ActionEvent event) {
        Bookfy.getMainWindowController().displayEditShippingInfo();
    }

    @FXML
    private void back(ActionEvent event) {
        Bookfy.getMainWindowController().displayHome();
    }

    @FXML
    private void signoff(ActionEvent event) {
        Bookfy.getMainWindowController().getSearchController().setQty(0);
        Bookfy.getMainWindowController().displayLogIn();
    }
    
    @FXML
    private void uploadImage(ActionEvent event) {
        //call userImage.java class
        new userImage();
    }
    
    
}
