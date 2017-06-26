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
    private HashMap<String , Boolean> ratings = new HashMap<String , Boolean>();
    
    private ArrayList<Book> books = new ArrayList<>();
    
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
    @FXML
    private Rating rat1;
    @FXML
    private Rating rat2;
    @FXML
    private Rating rat3;
    @FXML
    private Rating rat4;
    @FXML
    private Rating rat5;
    @FXML
    private CheckBox chk1;
    @FXML
    private CheckBox chk2;
    @FXML
    private CheckBox chk3;
    @FXML
    private CheckBox chk4;
    @FXML
    private CheckBox chk5;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rat1.setDisable(true);
        rat2.setDisable(true);
        rat3.setDisable(true);
        rat4.setDisable(true);
        rat5.setDisable(true);
        
        
        chk1.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                ratings.put("1", newValue);
            }
        });
        
        chk2.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                ratings.put("2", newValue);
            }
        });
        
        chk3.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                ratings.put("3", newValue);
            }
        });
        
        chk4.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                ratings.put("4", newValue);
            }
        });
        
        chk5.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                ratings.put("5", newValue);
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
                search("");
            }
        });
        
        search("");
    }    
    
    public void clear(){
        chk1.setSelected(false);
        chk2.setSelected(false);
        chk3.setSelected(false);
        chk4.setSelected(false);
        chk5.setSelected(false);
        chkComputer.setSelected(false);
        chkRomance.setSelected(false);
        chkScifi.setSelected(false);
        chkHorror.setSelected(false);
        chkMystery.setSelected(false);
        chkHistory.setSelected(false);
        chkFiction.setSelected(false);
        chkFantasy.setSelected(false);
        chkDrama.setSelected(false);
        chkNonFiction.setSelected(false);
        chkTopSellers.setSelected(false);
        chkBookfyTimes.setSelected(false);
    }
    
    public void search(String author){                
        String rating = "";  
        Iterator r = ratings.entrySet().iterator();
        while (r.hasNext()) {
            Entry pair = (Entry)r.next();
            if((boolean)pair.getValue() == true){
                rating += "Rating >= " + pair.getKey() + " OR ";
            }   
        }
        if(rating.length() != 0){
            rating = rating.substring(0, rating.length()-4);
        }
        
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
            whereClause = (whereClause.equalsIgnoreCase("")) ? "(" + rating + ")" : (whereClause + " AND (" + rating + ")");
        }
        
        if(!genre.equalsIgnoreCase("")){
            whereClause = whereClause + ((!whereClause.equalsIgnoreCase("")) ? " AND " : "") + "(" + genre + ")" ;
        }
        
        if(!topSeller.equalsIgnoreCase("")){
            whereClause = whereClause + ((!whereClause.equalsIgnoreCase("")) ? " AND " : "") + "(" + topSeller + ")" ;
        }
        
        if(!topBookfyTimes.equalsIgnoreCase("")){
            whereClause = whereClause + ((!whereClause.equalsIgnoreCase("")) ? " AND " : "") + "(" + topBookfyTimes + ")" ;
        }
        
        String sql = "SELECT BookId from Book " + (!whereClause.equalsIgnoreCase("") ? "WHERE " + whereClause : "");

        if(!author.equalsIgnoreCase("")){
            sql = "SELECT BookId from Book WHERE Author = \"" + author + "\"";
        }
        
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
