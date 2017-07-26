/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookfy;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javax.swing.DefaultComboBoxModel;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;

/**
 * FXML Controller class
 *
 * @author Roger Boza
 */
public class FXMLShoppingCartLaterLineItemController implements Initializable {
    private Book book;
    
    @FXML
    private ImageView imgImage;
    @FXML
    private Label lblTitle;
    @FXML
    private Label lblAuthor;
    @FXML
    private Label lblPrice;
    @FXML
    private JFXComboBox<Integer> cboQuantity;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnAddToCart;

    public FXMLShoppingCartLaterLineItemController(Book book) {
        this.book = book;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnDelete.setCursor(Cursor.HAND);
        btnAddToCart.setCursor(Cursor.HAND);
        
        Vector comboBoxItems=new Vector();
        final DefaultComboBoxModel model = new DefaultComboBoxModel(comboBoxItems);
            for(int i = 1; i <= 10; i++){
                cboQuantity.getItems().addAll(i);
        } 

        imgImage.setImage(book.getImage());
        lblTitle.setText(book.getTitle());
        lblAuthor.setText(book.getAuthor());
        lblPrice.setText("$ " + book.getPrice());
        
        Glyph g = new Glyph("FontAwesome", FontAwesome.Glyph.TRASH).size(24).color(Color.web("#f44336"));
        btnDelete.setGraphic(g); 
        
        Glyph g1 = new Glyph("FontAwesome", FontAwesome.Glyph.SHOPPING_CART).size(24).color(Color.WHITE);
        btnAddToCart.setGraphic(g1); 
        
        cboQuantity.getSelectionModel().select(book.getCartQty() -1);
        
        cboQuantity.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                book.setCartQty(newValue);
            }
        });
    }    

    @FXML
    private void removeFromCart(ActionEvent event) {
        Bookfy.getShoppingCart().removeFromCart(book);
    }   

    @FXML
    private void addToCart(MouseEvent event) {
        Bookfy.getShoppingCart().addToCart(book);
    }

}
