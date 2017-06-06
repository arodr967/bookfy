/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookfy;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Roger Boza
 */
public class FXMLEditShippingController implements Initializable {

    @FXML
    private JFXTextField txtFullName;
    @FXML
    private JFXTextField txtAddressLine1;
    @FXML
    private JFXTextField txtAddressLine2;
    @FXML
    private JFXTextField txtCity;
    @FXML
    private JFXTextField txtState;
    @FXML
    private JFXTextField txtCountry;
    @FXML
    private Button btnSave;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Address shipping = Bookfy.getUser().getShipping();
        if(shipping != null){
            txtFullName.setText(shipping.getName());
            txtAddressLine1.setText(shipping.getAddressLine1());
            txtAddressLine2.setText(shipping.getAddressLine2());
            txtCity.setText(shipping.getCity());
            txtState.setText(shipping.getState());
            txtCountry.setText(shipping.getCity());
        }
        
        RequiredFieldValidator nameValidator = new RequiredFieldValidator();
        txtFullName.getValidators().add(nameValidator);
        nameValidator.setMessage("Name cannot be empty");
        txtFullName.focusedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    txtFullName.validate();
                }
            }
        });
        
        RequiredFieldValidator addressLine1Validator = new RequiredFieldValidator();
        txtAddressLine1.getValidators().add(addressLine1Validator);
        addressLine1Validator.setMessage("Address Line 1 cannot be empty");
        txtAddressLine1.focusedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    txtAddressLine1.validate();
                }
            }
        });
        
        RequiredFieldValidator cityValidator = new RequiredFieldValidator();
        txtCity.getValidators().add(cityValidator);
        cityValidator.setMessage("City cannot be empty");
        txtCity.focusedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    txtCity.validate();
                }
            }
        });
        
        RequiredFieldValidator stateValidator = new RequiredFieldValidator();
        txtState.getValidators().add(stateValidator);
        stateValidator.setMessage("State cannot be empty");
        txtState.focusedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    txtState.validate();
                }
            }
        });
        
        RequiredFieldValidator countryValidator = new RequiredFieldValidator();
        txtCountry.getValidators().add(countryValidator);
        countryValidator.setMessage("Country cannot be empty");
        txtCountry.focusedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    txtCountry.validate();
                }
            }
        });
    }    

    @FXML
    private void save(ActionEvent event) {
        String fullName = txtFullName.getText();
        String addressLine1 = txtAddressLine1.getText();
        String addressLine2 = txtAddressLine2.getText();
        String city = txtCity.getText();
        String state = txtState.getText();
        String country = txtCountry.getText();
        
        if(!fullName.trim().equalsIgnoreCase("") && !addressLine1.trim().equalsIgnoreCase("") && !city.trim().equalsIgnoreCase("") && !state.trim().equalsIgnoreCase("") && !country.trim().equalsIgnoreCase("")){
            Address shipping = new Address(fullName, addressLine1, addressLine2, city, state, 0, country);
            Bookfy.getUser().setShipping(shipping);
            Bookfy.getUser().updateShippingToDB();
        }
        else{
            JOptionPane.showMessageDialog(null, "All required fields must be completed first.", "Bookfy", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @FXML
    private void cancel(ActionEvent event) {
        Bookfy.getMainWindowController().displayViewProfile();
    }
    
}
