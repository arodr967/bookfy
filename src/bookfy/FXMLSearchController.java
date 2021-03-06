/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookfy;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Roger Boza
 */
public class FXMLSearchController implements Initializable {

    @FXML
    private TextField txtSearch;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Bookfy.setSearch(newValue);
            }
        });
    }    

    @FXML
    private void myAccount(ActionEvent event) {
        Bookfy.getMainWindowController().displayViewProfile();
    }

    @FXML
    private void search(MouseEvent event) {
        Bookfy.getMainWindowController().displayHome();
        search();
    }

    @FXML
    private void check(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER)
        {
            search();
        }
    }
    
    private void search(){
        Bookfy.getHome().search();
    }
    
}
