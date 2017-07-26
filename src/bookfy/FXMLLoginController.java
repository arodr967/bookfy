/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookfy;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 *
 * @author Roger Boza
 */
public class FXMLLoginController implements Initializable {
    @FXML
    private JFXTextField txtUsername;

    @FXML
    private JFXPasswordField txtPassword;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //start logic here
    }    

    private void logIn(){
        Bookfy.getMainWindowController().maskPane("Logging into the system.");
        LogInHelper helper = new LogInHelper();            
        helper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
               @Override
               public void handle(WorkerStateEvent t) {
                   //addData((ArrayList<PortedEnclosureRecommendation>)t.getSource().getValue());
                   boolean success = (boolean)t.getSource().getValue();
                   if(success){
                       try{
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/bookfy/FXMLShoppingCart.fxml"));
                            AnchorPane shoppingCart = loader.load();
                            Bookfy.setShoppingCart(loader.getController());
                            Bookfy.setShoppingCartPane(shoppingCart);

                            User user = new User(txtUsername.getText());   
                            Bookfy.getMainWindowController().displayHome(user);   
                        }
                        catch(IOException ex){
                            System.out.println(ex);
                        }
                   }
                    else{
                       Bookfy.getMainWindowController().unmaskPane();
                   }
               }
           });
        helper.start();    
    }
    
    @FXML
    private void signUp(ActionEvent event) {
        Bookfy.getMainWindowController().displayCreateAccount();
    }
    
    @FXML
    private void forgotPassword(ActionEvent event) {
    }

    @FXML
    private void check(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER)
        {
            logIn();
        }
    }
        
    @FXML
    private void makeLogin(MouseEvent event) {
        logIn();
    }
    
    class LogInHelper extends Service<Boolean>{
        protected Task<Boolean> createTask() {
            return new Task<Boolean>() {
                protected Boolean call(){
                    try {
                    String userName = txtUsername.getText();

                    String query = "SELECT FirstName, LastName, LazyDelete FROM user where UserID = \'" + userName + "\' AND password = \"" + txtPassword.getText() + "\"";

                    ResultSet rs = Bookfy.getDatabaseHandler().execQuery(query);

                        /*For testing
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        */
                        
                    if(rs.next()){
                        if(!rs.getString(3).equalsIgnoreCase("0")){//account has been deactivated
                            JOptionPane.showMessageDialog(null, "Your account has been deactivated.", "Bookfy", JOptionPane.INFORMATION_MESSAGE);
                            return false;
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Oppps... Wrong username or password.");
                        return false;
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                    return false; 
                }
                
                    return true;
                }
            };
        }
    }
}
