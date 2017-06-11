/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookfy;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import org.controlsfx.control.Rating;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;

/**
 * FXML Controller class
 *
 * @author Roger Boza
 */
public class FXMLBookCompactController implements Initializable {
    Book book;
    
    @FXML
    private Rating ratRating;
    @FXML
    private ImageView imgView;
    @FXML
    private Button btnView;
    @FXML
    private Button btnAdd;

    public FXMLBookCompactController(Book book) {
        this.book = book;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Glyph g = new Glyph("FontAwesome", FontAwesome.Glyph.SHOPPING_CART).size(18).color(Color.WHITE);
        btnAdd.setGraphic(g);
        
        ratRating.setRating(book.getRating());
        if(book.getImage() != null){
            imgView.setImage(book.getImage());
        }
        
    }    

    @FXML
    private void addToCart(ActionEvent event) {
    }
    
}
