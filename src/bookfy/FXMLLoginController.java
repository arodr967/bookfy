/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookfy;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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

    @FXML
    void makeLogin(ActionEvent event) {
        logIn();
    } 

    private void logIn(){
        try {
            String userName = txtUsername.getText();
            
            String query = "SELECT FirstName, LastName FROM user where UserID = \'" + userName + "\' AND password = \"" + txtPassword.getText() + "\"";

            ResultSet rs = Bookfy.getDatabaseHandler().execQuery(query);

            if(rs.next()){
                //JOptionPane.showMessageDialog(null, "Welcome " + rs.getString(1) + " " + rs.getString(2));
                User user = new User(userName);
                Bookfy.getMainWindowController().displayHome(user);
            }
            else{
                JOptionPane.showMessageDialog(null, "Oppps... Wrong username or password.");
            }
        } catch (SQLException ex) {
            //Logger.getLogger(Bookfy.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    @FXML
    private void signUp(ActionEvent event) {
        Bookfy.getMainWindowController().displayAccount();
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
}
