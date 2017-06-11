/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookfy;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Pagination;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author Roger Boza
 */
public class FXMLHomeController implements Initializable {
    private HashMap<String , Boolean> filters = new HashMap<String , Boolean>();
    private ArrayList<Book> books = new ArrayList<>();
    
    @FXML
    private Rating ratRating;
    @FXML
    private CheckBox chkRomance;
    @FXML
    private CheckBox chkScifi;
    @FXML
    private CheckBox chkHorror;
    @FXML
    private CheckBox chkMystery;
    @FXML
    private CheckBox chkHistory;
    @FXML
    private CheckBox chkFiction;
    @FXML
    private CheckBox chkFantasy;
    @FXML
    private CheckBox chkDrama;
    @FXML
    private CheckBox chkNonFiction;
    @FXML
    private CheckBox chkTopSellers;
    @FXML
    private CheckBox chkBookfyTimes;
    @FXML
    private Pagination pages;
    @FXML
    private CheckBox chkComputer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ratRating.ratingProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,Number oldValue, Number newValue) {
            }
        });
        
        chkComputer.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                filters.put("Computer", newValue);
            }
        });
        
        chkRomance.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                filters.put("Romance", newValue);
            }
        });
        
        chkScifi.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                filters.put("Science Fiction", newValue);
            }
        });
        
        chkHorror.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                filters.put("Horror", newValue);
            }
        });
        
        chkMystery.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                filters.put("Mystery", newValue);
            }
        });
        
        chkHistory.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                filters.put("History", newValue);
            }
        });
        
        chkFiction.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                filters.put("Fiction", newValue);
            }
        });
        
        chkFantasy.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                filters.put("Fantasy", newValue);
            }
        });
        
        chkDrama.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                filters.put("Drama", newValue);
            }
        });
        
        chkNonFiction.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                filters.put("Non Fiction", newValue);
                search();
            }
        });
        
        search();
    }    
    
    public void search(){                
        String rating = "Rating >= " + (int)ratRating.getRating();
        
        String genre = "";
        Iterator it = filters.entrySet().iterator();
        while (it.hasNext()) {
            Entry pair = (Entry)it.next();
            if((boolean)pair.getValue() == true){
                genre += "Genre = '" + pair.getKey() + "' OR ";
            }   
        }
        if(genre.length() != 0){
            genre = genre.substring(0, genre.length()-4);
        }
        
        String topSeller = (chkTopSellers.isSelected()) ? "TopSeller = '1'" : "";
        String topBookfyTimes = (chkBookfyTimes.isSelected()) ? "BookfyTimes = '1'" : "";
        
        String title = (Bookfy.getSearch().equalsIgnoreCase("")) ? "" : "Title LIKE '%" + Bookfy.getSearch() + "%'";
        
        String whereClause = "";
        if(!title.equalsIgnoreCase("")){
            whereClause += "(" + title + ")";
        }
        
        if(!rating.equalsIgnoreCase("")){
            whereClause = (whereClause.equalsIgnoreCase("")) ? rating : (whereClause + " AND (" + rating + ")");
        }
        
        if(!genre.equalsIgnoreCase("")){
            whereClause = (whereClause.equalsIgnoreCase("")) ? rating : (whereClause + " AND (" + genre + ")");
        }
        
        if(!topSeller.equalsIgnoreCase("")){
            whereClause = (whereClause.equalsIgnoreCase("")) ? rating : (whereClause + " AND (" + topSeller + ")");
        }
        
        if(!topBookfyTimes.equalsIgnoreCase("")){
            whereClause = (whereClause.equalsIgnoreCase("")) ? rating : (whereClause + " AND (" + topBookfyTimes + ")");
        }
        
        String sql = "SELECT BookId from Book WHERE " + whereClause;
        System.out.println(sql);
        
        try {
            ResultSet rs = Bookfy.getDatabaseHandler().execQuery(sql);
            
            books.clear();
            while(rs.next()){
                int bookID = Integer.valueOf(rs.getString(1));
                Book book = new Book(bookID);
                books.add(book);
            }
            
            int pageCount = books.size() / 9 + 1;
            pages.setPageCount(pageCount);
            pages.setPageFactory(new Callback<Integer, Node>() {
                @Override
                public Node call(Integer pageIndex) {          
                    return createPage(pageIndex);               
                }
            });
        } catch (SQLException ex) {
            //Logger.getLogger(Bookfy.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }  
    
    public AnchorPane createPage(int pageIndex) {        
        try {
            ArrayList<Book> b = new ArrayList<>();
            for(int i = pageIndex*9; i < (pageIndex+1)*9 && i < books.size(); i++){
                b.add(books.get(i));
            }
            FXMLPageDisplayController controller = new FXMLPageDisplayController(b);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/bookfy/FXMLPageDisplay.fxml"));
            loader.setController(controller);

            AnchorPane page = loader.load();

            return page;

        } catch (IOException ex) {
            Logger.getLogger(FXMLMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
