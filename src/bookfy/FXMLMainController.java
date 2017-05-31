/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookfy;

import com.jfoenix.controls.JFXScrollPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Roger Boza
 */
public class FXMLMainController implements Initializable {
    private AnchorPane home;
    

    @FXML
    private HBox searchPane;
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
            //no user logged in
            Bookfy.setUser(null);
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/bookfy/FXMLLogin.fxml"));
            AnchorPane logIn = loader.load();

            showContent(logIn);
            hideSearch();
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
    
    public void viewProfile(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/bookfy/FXMLViewUserProfile.fxml"));
            AnchorPane account = loader.load();

            showContent(account);
        } catch (IOException ex) {
            Logger.getLogger(FXMLMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void displayHome(){
        if(home != null){
            showContent(home);
        }
        else{
            JOptionPane.showMessageDialog(null, "cannot display home screen becuase the pane is null.");
        }
    }
    
    public void displayHome(User user){
        try {  
            Bookfy.setUser(user);
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/bookfy/FXMLHome.fxml"));
            home = loader.load();

            showContent(home);
            displaySearch();
        } catch (IOException ex) {
            Logger.getLogger(FXMLMainController.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }  
    
    private void displaySearch(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/bookfy/FXMLSearch.fxml"));
            AnchorPane search = loader.load();

            searchPane.getChildren().add(search);
        } catch (IOException ex) {
            Logger.getLogger(FXMLMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void hideSearch(){
        searchPane.getChildren().clear();
    }
    
    private void showContent(AnchorPane pane){
        //mainContent.setContent(pane);
        mainContent.getChildren().clear();
        mainContent.getChildren().add(pane);
    }
}
