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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Roger Boza
 */
public class FXMLImageViewController implements Initializable {
    private Book book;
    
    @FXML
    private ImageView imgImage;

    public FXMLImageViewController(Book book) {
        this.book = book;
    }
    /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imgImage.setImage(book.getImage());
    }    

    @FXML
    private void close(MouseEvent event) {
        Bookfy.getMainWindowController().displayBookDetail(book);
    }
    
}
