/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookfy;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Roger Boza
 */
public class FXMLViewProfileController implements Initializable {

    @FXML
    private Label lblHomeAddress;
    @FXML
    private Label lblShippingAddress;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadHomeAddress();
        loadShippingAddress();
    }    
    
    private void loadHomeAddress(){
        Address home = Bookfy.getUser().getHome();
        lblShippingAddress.setText(home.formattedShort());
    }
    
    private void loadShippingAddress(){
        Address shipping = Bookfy.getUser().getShipping();
        lblShippingAddress.setText(shipping.toString());
    }
    
}
