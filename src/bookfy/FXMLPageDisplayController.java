/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookfy;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Roger Boza
 */
public class FXMLPageDisplayController implements Initializable {
    ArrayList<Book> books;
    
    @FXML
    private GridPane grid;

    public FXMLPageDisplayController(ArrayList<Book> books) {
        this.books = books;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(books != null){
            if(books.isEmpty()){
                grid.add(new Label("No book Found!"), 1, 0);
            }
            else{
                int counter = 0;
                for(Book b : books){
                    try {
                        FXMLBookCompactController controller = new FXMLBookCompactController(b);
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/bookfy/FXMLBookCompact.fxml"));
                        loader.setController(controller);

                        AnchorPane book = loader.load();

                        int row = counter / 3;
                        int col = counter % 3;
                        grid.add(book, col, row);
                        counter++;
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLMainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } 
        }
    }    
    
}
