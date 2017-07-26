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
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;

/**
 * FXML Controller class
 *
 * @author Roger Boza
 */
public class FXMLSearchController implements Initializable {

    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnHome;
    @FXML
    private ImageView btnShoppingCart;
    @FXML
    private ImageView imgSearch;
    @FXML
    private Label lblQty;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Glyph g = new Glyph("FontAwesome", FontAwesome.Glyph.HOME).size(24).color(new Color(244.0/255.0, 67.0/255.0, 54.0/255.0, 1));
        btnHome.setGraphic(g);
        
        imgSearch.setCursor(Cursor.HAND);
        btnShoppingCart.setCursor(Cursor.HAND);
        lblQty.setCursor(Cursor.HAND);
        
        txtSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Bookfy.setSearch(newValue);
                search();
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
        Bookfy.getHome().search(Bookfy.getSearch());
    }

    @FXML
    private void home(MouseEvent event) {
        Bookfy.getHome().clear();
        Bookfy.getHome().search("");
        Bookfy.getMainWindowController().displayHome();
    }

    @FXML
    private void viewCart(MouseEvent event) {
        Bookfy.getMainWindowController().displayShoppingCart();
    }
    
    public void setQty(int qty){
        lblQty.setText(qty + "");
    }
    
    public void setSearchFocus(){
        txtSearch.requestFocus();
    }
}
