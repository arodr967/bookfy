/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookfy;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author curaj
 */
public class FXMLCreateAccountController implements Initializable {

    @FXML
    private Label fullNameLabel;
    @FXML
    private JFXTextField fullNameTextField;
    @FXML
    private Label emailAddressLabel;
    @FXML
    private JFXTextField EmailAddressTextField;
    @FXML
    private Label userNameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label verifyPasswordLabel;
    @FXML
    private JFXTextField userNameTextField;
    @FXML
    private JFXTextField passwordTextField;
    @FXML
    private JFXButton saveButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void verifyPasswordTextField(ActionEvent event) {
    }

    @FXML
    private void saveListener(MouseEvent event) {
        
            if (inputValidation()) {
                String query = "INSERT INTO user(UserID , FirstName, password)\n"
                        + "VALUES('" + userNameTextField.getText() + "','" + fullNameTextField.getText() + "','"
                        + passwordTextField.getText() + "');";
            Bookfy.getDatabaseHandler().execAction(query);
            System.out.println(query);
        }
        
    }

    //input validation 
    private boolean inputValidation() {
        if(userNameTextField.getText() == null&&fullNameTextField.getText()==null);
        return false;
    }
}
