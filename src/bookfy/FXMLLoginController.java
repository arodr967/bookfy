/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookfy;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javax.swing.JOptionPane;

/**
 *
 * @author Roger Boza
 */
public class FXMLLoginController implements Initializable {
    @FXML
    private JFXTextField username;
    
    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton login;

    @FXML
    private JFXButton cancel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //start logic here
    }    

    @FXML
    void makeCancel(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void makeLogin(ActionEvent event) {
        try {
            String query = "SELECT FirstName, LastName FROM user where UserID = \'" + username.getText() + "\' AND password = \"" + password.getText() + "\"";

            ResultSet rs = Bookfy.getDatabaseHandler().execQuery(query);

            if(rs.next()){
                JOptionPane.showMessageDialog(null, "Welcome " + rs.getString(1) + " " + rs.getString(2));
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
}