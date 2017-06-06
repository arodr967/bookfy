/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookfy;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import java.awt.Color;
import javafx.scene.paint.Paint;

/**
 *
 * @author curaj
 */
public class TextFieldValidator {

    RequiredFieldValidator validator = new RequiredFieldValidator();
    JFXTextField textField;
    JFXPasswordField passwordField;

    TextFieldValidator(JFXTextField textField, String message) {
        this.textField = textField;
        this.textField.getValidators().add(validator);
        validator.setMessage("" + message);

    }
    //passwordField
    TextFieldValidator(JFXPasswordField textField, String message) {
        this.passwordField = textField;
        this.passwordField.getValidators().add(validator);
        validator.setMessage("" + message);
    }

    public boolean validate() {
        boolean valid = false;
        if (textField.validate()) {
            valid = true;            
        }//else -> change focus color - not implemented 
        return valid;
    }
    public boolean passwordValidate(){
    boolean valid = false;
        if (passwordField.validate()) {
            valid = true;            
        }//else -> change focus color - not implemented 
        return valid;
    }

}
