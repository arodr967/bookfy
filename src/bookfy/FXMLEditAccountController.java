package bookfy;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class FXMLEditAccountController implements Initializable {

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
    
    private User user;
    private Address address;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //testing - to be deleted
        //user = new User("jdoe001");
        
        
        user = Bookfy.getUser();
        address = user.getHome();
        getAccount();

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
            Address addressUpdate = new Address(firstNameTextField.getText()+" "+
                                       lastNameTextField.getText(), 
                                       AddressLineTextField.getText(),"",
                                       cityTextField.getText(),
                                       StateTextField.getText(),0,
                                       CountryTextField.getText());
                    
            User userUpdate = new User(firstNameTextField.getText(),
                                       lastNameTextField.getText(),
                                       userNameTextField.getText(),
                                       passwordTextField.getText(),
                                       EmailAddressTextField.getText(),
                                       addressUpdate);
            userUpdate.updateBasicUserData(passwordTextField.getText());
            user = userUpdate;
            address = addressUpdate;
            Bookfy.getMainWindowController().displayHome(user);
            
        } else if (!addressFieldsCheck() & inputValidation() & passwordFlag) {
            User userUpdate = new User(firstNameTextField.getText(),
                                       lastNameTextField.getText(),
                                       userNameTextField.getText(),
                                       passwordTextField.getText(),
                                       EmailAddressTextField.getText(),
                                       address);
            userUpdate.updateBasicUserData(passwordTextField.getText());
            user = userUpdate;
            Bookfy.getMainWindowController().displayHome(user);
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

        if (passwordTextField.getText().equals(verifyPasswordTextField.getText())) {
            passwordMismatchLabel.setText("");
            return true;
        } else {
            passwordMismatchLabel.setText("Password Mismatch");
        }

        return false;
    }

    @FXML
    private void cancel(ActionEvent event) {
        Bookfy.getMainWindowController().displayHome(user);
    }

    private void getAccount()  {
            //System.out.println(result.getString("FirstName"));
            firstNameTextField.setText(user.getFirstName());
            lastNameTextField.setText(user.getLastName());
            EmailAddressTextField.setText(user.getEmail());
            
            AddressLineTextField.setText(address.getAddressLine1());
            cityTextField.setText(address.getCity());
            StateTextField.setText(address.getState());
            CountryTextField.setText(address.getCountry());
            userNameTextField.setText(user.getUserName());            
        
    }
}
