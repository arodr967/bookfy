/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookfy;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Roger Boza
 */
public class FXMLShoppingCartController implements Initializable {
    private ArrayList<Book> cartBooks = new ArrayList();
    private ArrayList<Book> laterBooks = new ArrayList();
    
    @FXML
    private VBox vboxShoppingCart;
    @FXML
    private Label lblSubTotal;
    @FXML
    private VBox vboxSavedForLater;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void addToCart(Book book){
        //if the book came from the saved for later then we just need to do a swap on the array lists
        for(Book b : laterBooks){
            if(b.equals(book)){
                laterBooks.remove(b);
                cartBooks.add(b);
                displayBooks();
                displaySavedForLater();
                return;
            }
        }
        
        for(Book b : cartBooks){
            if(b.equals(book)){
                b.setCartQty(b.getCartQty() + 1);
                displayBooks();
                return;
            }
        }
        
        book.setCartQty(1);
        cartBooks.add(book);
        displayBooks();
    }
    
    public void updateCart(){
        displayBooks();
        displaySavedForLater();
    }
    
    public void removeFromCart(Book book){
        cartBooks.remove(book);
        laterBooks.remove(book);
        displayBooks();
        displaySavedForLater();
    }
    
    public void moveToLater(Book book){
        cartBooks.remove(book);
        laterBooks.add(book);
        
        displaySavedForLater();
        displayBooks();
    }
    
    private void displaySavedForLater(){
        vboxSavedForLater.getChildren().clear();
        
        for(Book b : laterBooks){
            try {              
                FXMLShoppingCartLaterLineItemController controller = new FXMLShoppingCartLaterLineItemController(b);
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/bookfy/FXMLShoppingCartLaterLineItem.fxml"));
                loader.setController(controller);

                AnchorPane bookLine = loader.load();
                vboxSavedForLater.getChildren().add(bookLine);
            } catch (IOException ex) {
                Logger.getLogger(FXMLMainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void displayBooks(){
        clearCart();
        
        double subTotal = 0;
        int totalBooks = 0;
        
        for(Book b : cartBooks){
            try {
                totalBooks += b.getCartQty();
                subTotal += b.getPrice() * b.getCartQty();
                
                FXMLShoppingCartLineController controller = new FXMLShoppingCartLineController(b);
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/bookfy/FXMLShoppingCartLine.fxml"));
                loader.setController(controller);

                AnchorPane bookLine = loader.load();
                vboxShoppingCart.getChildren().add(bookLine);
            } catch (IOException ex) {
                Logger.getLogger(FXMLMainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        Bookfy.getMainWindowController().getSearchController().setQty(totalBooks);
        updateSubTotal(subTotal);
    }
    
    private void updateSubTotal(double subTotal){
        DecimalFormat myFormatter = new DecimalFormat("$###,###.###");
        lblSubTotal.setText("Subtotal (" + cartBooks.size() + "): " + myFormatter.format(subTotal));
    }
    
    private void clearCart(){
        vboxShoppingCart.getChildren().clear();
    }
}
