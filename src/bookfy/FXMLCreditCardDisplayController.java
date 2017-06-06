/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookfy;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Roger Boza
 */
public class FXMLCreditCardDisplayController implements Initializable {
    private CreditCard card;
    
    @FXML
    private Label lblCardInfo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    public void setCard(CreditCard card){
        this.card = card;
        displayCard();
    }
    
    private void displayCard(){
        lblCardInfo.setText(card.getName() + "\nCard Ending in " + card.getLast4Digits());
    }

    @FXML
    private void editCreditCard(ActionEvent event) {
        Bookfy.getMainWindowController().displayEditCard(card);
    }
    
}
