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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Roger Boza
 */
public class FXMLMainController implements Initializable {

    @FXML
    private HBox mainContent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displayLogIn();
    }    
    
    public void displayLogIn(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/bookfy/FXMLLogin.fxml"));
            AnchorPane logIn = loader.load();

            showContent(logIn);
        } catch (IOException ex) {
            Logger.getLogger(FXMLMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void displayAccount(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/bookfy/FXMLCreateAccount.fxml"));
            AnchorPane account = loader.load();

            showContent(account);
        } catch (IOException ex) {
            Logger.getLogger(FXMLMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void showContent(AnchorPane pane){
        mainContent.getChildren().clear();
        mainContent.getChildren().add(pane);
    }
}
