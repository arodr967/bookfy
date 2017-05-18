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
    private JFXTextField EmailAddressTextField;
    @FXML
    private JFXTextField userNameTextField;
    @FXML
    private JFXTextField passwordTextField;
    @FXML
    private JFXButton saveButton;
    @FXML
    private JFXTextField firstNameTextField;
    @FXML
    private JFXTextField lastNameTextField;
    @FXML
    private JFXTextField cityTextField;
    @FXML
    private JFXTextField StateTextField;
    @FXML
    private JFXTextField CountryTextField;
    @FXML
    private JFXTextField verifyPasswordTextField;
    @FXML
    private JFXButton cancelButton;
    @FXML
    private JFXTextField AddressLineTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void saveListener(MouseEvent event) {

        boolean addressFlag = addressFieldsCheck();

        //Create Account - no Address
        //missing validator - if(inputValidationi()){}
        if (!addressFlag && true) {
            String query = "INSERT INTO user(UserID , FirstName, LastName, password)\n"
                    + "VALUES('" + userNameTextField.getText() + "','"
                    + firstNameTextField.getText() + "','"
                    + lastNameTextField.getText() + "','"
                    + passwordTextField.getText() + "');";
            Bookfy.getDatabaseHandler().execAction(query);
        } //Create Account - with Address
        else if (true) {
            String query = "INSERT INTO user(UserID , FirstName, "
                    + "LastName, password,AddressLine,City,State,Country)\n"
                    + "VALUES('" + userNameTextField.getText() + "','"
                    + firstNameTextField.getText() + "','"
                    + lastNameTextField.getText() + "','"
                    + passwordTextField.getText() + "','"
                    + AddressLineTextField.getText() + "','"
                    + StateTextField.getText() + "','"
                    + CountryTextField.getText() + "');'";
            Bookfy.getDatabaseHandler().execAction(query);
        }

    }
//addressfield validator - not implemented 

    private boolean addressFieldsCheck() {

        return false;
    }

    //input validation - not implemented
    private boolean inputValidation() {

        return false;
    }
}
