/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookfy;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javax.swing.JOptionPane;
import org.controlsfx.control.Rating;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;

/**
 * FXML Controller class
 *
 * @author Roger Boza
 */
public class FXMLBookDetailsController implements Initializable {
    Book book;
    
    @FXML
    private Label lblGenra;
    @FXML
    private Label lblTitle;
    @FXML
    private Hyperlink lblAuthor;
    @FXML
    private Label lblPrice;
    @FXML
    private Label lblDescription;
    @FXML
    private Label lblAboutAuthor;
    @FXML
    private Label lblAuthorBio;
    @FXML
    private Label lblPublisher;
    @FXML
    private Label lblReleaseDate;
    @FXML
    private Button btnShoppingCart;
    @FXML
    private ImageView imgImage;
    @FXML
    private Rating ratRating;

    public FXMLBookDetailsController(Book book) {
        this.book = book;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ratRating.setDisable(true);
        
        Glyph g = new Glyph("FontAwesome", FontAwesome.Glyph.SHOPPING_CART).size(18).color(Color.WHITE);
        btnShoppingCart.setGraphic(g);
        
        lblDescription.setWrapText(true);
        lblAuthorBio.setWrapText(true);
         
        lblTitle.setText(book.getTitle());
        lblAuthor.setText(book.getAuthor());
        lblGenra.setText(book.getGenre());
        lblPrice.setText("$" + book.getPrice());
        lblDescription.setText(book.getDescription());
        lblAboutAuthor.setText("About " + book.getAuthor());
        lblAuthorBio.setText(book.getAuthorBio());
        lblPublisher.setText("Publisher: " + book.getPublisher());
        lblReleaseDate.setText("Publication Date: " + book.getReleaseDate());
        if(book.getImage() != null){
            imgImage.setImage(book.getImage());
        }
        
        ratRating.setRating(book.getRating());
    }    

    @FXML
    private void back(MouseEvent event) {
        Bookfy.getMainWindowController().displayHome();
    }

    @FXML
    private void browse(MouseEvent event) {
        Bookfy.getHome().search(book.getAuthor());
        Bookfy.getMainWindowController().displayHome();
    }

    @FXML
    private void expand(MouseEvent event) {
        Bookfy.getMainWindowController().displayImageView(book);
    }

    @FXML
    private void addToCart(MouseEvent event) {
        Bookfy.getShoppingCart().addToCart(book);
        JOptionPane.showMessageDialog(null, book.getTitle() + " has bee added to the cart.");
    }
    
}
