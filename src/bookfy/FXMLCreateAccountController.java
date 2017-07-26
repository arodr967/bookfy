package bookfy;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
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
    private JFXPasswordField passwordTextField;
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
    private JFXPasswordField verifyPasswordTextField;
    @FXML
    private JFXButton cancelButton;
    @FXML
    private JFXTextField AddressLineTextField;
    @FXML
    private Label addressMissingInputLabel;

    //validators
    private TextFieldValidator firstNameValidator;
    private TextFieldValidator lastNameValidator;
    private TextFieldValidator emailAddressValidator;
    private TextFieldValidator userNameValidator;
    private TextFieldValidator passwordValidator;
    private TextFieldValidator verifyPasswordValidator;

    private TextFieldValidator addressLineValidator;
    private TextFieldValidator cityValidator;
    private TextFieldValidator stateValidator;
    private TextFieldValidator countryValidator;
    @FXML
    private Label passwordMismatchLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        firstNameValidator = new TextFieldValidator(firstNameTextField, "Missing First Name");
        lastNameValidator = new TextFieldValidator(lastNameTextField, "Missing Last Name");
        emailAddressValidator = new TextFieldValidator(EmailAddressTextField, "Missing Email Address");
        userNameValidator = new TextFieldValidator(userNameTextField, "Missing User Name");
        passwordValidator = new TextFieldValidator(passwordTextField, "Missing Password");
        verifyPasswordValidator = new TextFieldValidator(verifyPasswordTextField, "Missing Password Verification");

        addressLineValidator = new TextFieldValidator(AddressLineTextField, "");
        cityValidator = new TextFieldValidator(cityTextField, "");
        stateValidator = new TextFieldValidator(StateTextField, "");
        countryValidator = new TextFieldValidator(CountryTextField, "");
    }

    @FXML
    private void saveListener(MouseEvent event) {
        boolean passwordFlag = verifyPasswordMatch();
        
        if (addressFieldsCheck() & inputValidation() & passwordFlag) {
            String query = "INSERT INTO user(UserID , FirstName, "
                    + "LastName, email, password,AddressLine,City,State,Country)\n"
                    + "VALUES('" + userNameTextField.getText() + "','"
                    + firstNameTextField.getText() + "','"
                    + lastNameTextField.getText() + "','"
                    + EmailAddressTextField.getText() + "','"
                    + passwordTextField.getText() + "','"
                    + AddressLineTextField.getText() + "','"
                    + cityTextField.getText() + "','"
                    + StateTextField.getText() + "','"
                    + CountryTextField.getText() + "');";
            Bookfy.getDatabaseHandler().execAction(query);
            Bookfy.getMainWindowController().displayLogIn();
        } else if (!addressFieldsCheck() & inputValidation() & passwordFlag) {
            String query = "INSERT INTO user(UserID , FirstName, LastName, email, password)\n"
                    + "VALUES('" + userNameTextField.getText() + "','"
                    + firstNameTextField.getText() + "','"
                    + lastNameTextField.getText() + "','"
                    + EmailAddressTextField.getText() + "','"
                    + passwordTextField.getText() + "');";
            Bookfy.getDatabaseHandler().execAction(query);
            Bookfy.getMainWindowController().displayLogIn();
        }

    }

    //address fields input validation 
    private boolean addressFieldsCheck() {
        if (addressLineValidator.validate() & cityValidator.validate()
                & stateValidator.validate() & countryValidator.validate()) {
            addressMissingInputLabel.setText("");
            return true;
        } else {
            addressMissingInputLabel.setText("Missing Address");
        }

        return false;
    }

    //basic fields input validation 
    private boolean inputValidation() {
        if (firstNameValidator.validate() & lastNameValidator.validate()
                & emailAddressValidator.validate() & userNameValidator.validate()
                & passwordValidator.passwordValidate() & verifyPasswordValidator.passwordValidate()) {
            return true;
        }
        return false;
    }

    private boolean verifyPasswordMatch() {
        
        
        if(passwordTextField.getText().equals(verifyPasswordTextField.getText())){        
        passwordMismatchLabel.setText("");
        return true;
        }else{
        passwordMismatchLabel.setText("Password Mismatch");
        }
        
        return false;
    }

    @FXML
    private void cancel(ActionEvent event) {
        Bookfy.getMainWindowController().displayLogIn();
    }
}
