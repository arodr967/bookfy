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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Pagination;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.converter.LocalDateStringConverter;
import javax.swing.JOptionPane;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author Roger Boza
 */
public class FXMLHomeController implements Initializable {
    private HashMap<String , Boolean> filters = new HashMap<String , Boolean>();
    //private HashMap<String , Boolean> ratings = new HashMap<String , Boolean>();
    String rating = "Rating >= 1";
    
    private ArrayList<Book> masterBooks = new ArrayList<>();
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
    private RadioButton radio1;
    @FXML
    private ToggleGroup group;
    @FXML
    private RadioButton radio2;
    @FXML
    private RadioButton radio3;
    @FXML
    private RadioButton radio4;
    @FXML
    private RadioButton radio5;
    @FXML
    private ComboBox cboSort;
    @FXML
    private ComboBox cboView;

    /**
     * Initializes the controller class.
     */
    
    private void prepareSortCombo(){
        cboSort.getItems().clear();
        cboSort.getItems().addAll("Book title", "Author name", "Price", "Book rating", "Release date");
    }
    
    private boolean ignore=false;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {       
        rat1.setMouseTransparent(true);
        rat2.setMouseTransparent(true);
        rat3.setMouseTransparent(true);
        rat4.setMouseTransparent(true);
        rat5.setMouseTransparent(true);
        
        prepareSortCombo();
        cboSort.valueProperty().addListener(new ChangeListener<String>() {
            @Override 
            public void changed(ObservableValue ov, String t, String t1) {
                sortBooks();
                displayBooks();
            } 
        });

        cboView.getItems().addAll("View 3", "View 6", "View 9");
        cboView.getSelectionModel().select(2);
        cboView.valueProperty().addListener(new ChangeListener<String>() {
            @Override 
            public void changed(ObservableValue ov, String t, String t1) {
                if(t1.startsWith("View 3")){
                    itemsPerPage = 3;
                }
                else if(t1.startsWith("View 6")){
                    itemsPerPage = 6;
                }
                else if(t1.startsWith("View 9")){
                    itemsPerPage = 9;
                }
                displayBooks();
            } 
        });
        
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
                 if (group.getSelectedToggle() != null) {
                     if(new_toggle.toString().contains("1")){
                         rating = "Rating >= 1";
                     }
                     else if(new_toggle.toString().contains("2")){
                         rating = "Rating >= 2";
                     }
                     else if(new_toggle.toString().contains("3")){
                         rating = "Rating >= 3";
                     }
                     else if(new_toggle.toString().contains("4")){
                         rating = "Rating >= 4";
                     }
                     else if(new_toggle.toString().contains("5")){
                         rating = "Rating >= 5";
                     }
                     
                     search(Bookfy.getSearch());
                 }
             } 
        });

        
        chkComputer.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                filters.put("Computer", newValue);
                search(Bookfy.getSearch());
            }
        });
        
        chkRomance.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                filters.put("Romance", newValue);
                search(Bookfy.getSearch());
            }
        });
        
        chkScifi.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                filters.put("Science Fiction", newValue);
                search(Bookfy.getSearch());
            }
        });
        
        chkHorror.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                filters.put("Horror", newValue);
                search(Bookfy.getSearch());
            }
        });
        
        chkMystery.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                filters.put("Mystery", newValue);
                search(Bookfy.getSearch());
            }
        });
        
        chkHistory.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                filters.put("History", newValue);
                search(Bookfy.getSearch());
            }
        });
        
        chkFiction.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                filters.put("Fiction", newValue);
                search(Bookfy.getSearch());
            }
        });
        
        chkFantasy.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                filters.put("Fantasy", newValue);
                search(Bookfy.getSearch());
            }
        });
        
        chkDrama.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                filters.put("Drama", newValue);
                search(Bookfy.getSearch());
            }
        });
        
        chkNonFiction.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                filters.put("Non Fiction", newValue);
                search(Bookfy.getSearch());
            }
        });
        
        chkBookfyTimes.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                search(Bookfy.getSearch());
            }
        });
        
        chkTopSellers.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                search(Bookfy.getSearch());
            }
        });
                
        loadBooks();
    }    
    
    public void clear(){
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
    
    private void localSearch(String author){
        books.clear();
        for(Book b : masterBooks){
            if((author != null && !author.isEmpty() && (!b.getAuthor().toLowerCase().contains(author.toLowerCase()))) &&  (author != null && !author.isEmpty() && (!b.getTitle().toLowerCase().contains(author.toLowerCase())))){
                continue;
            }
            
            Iterator it = filters.entrySet().iterator();
            String genre = "";
            while (it.hasNext()) {
                Entry pair = (Entry)it.next();
                if((boolean)pair.getValue() == true){
                    genre += pair.getKey();
                }   
            }
            
            if(!genre.isEmpty() && !genre.contains(b.getGenre())){
                continue;
            }
            
            if(!rating.equalsIgnoreCase("") && b.getRating() < Double.valueOf(rating.substring(rating.length()-1, rating.length()))){
                continue;
            }
            
            if(chkBookfyTimes.isSelected() && !b.isBookfyTimes()){
                continue;
            }
            
            if(chkTopSellers.isSelected() && !b.isTopSeller()){
                continue;
            }
            
            books.add(b);  
        }
    }
    
    private void loadBooks(){
        liveSearch("");
    }
    
    public void search(String author){     
        localSearch(author);
        sortBooks();
        displayBooks();
    }  
    
    public void liveSearch(String author){
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
        
        Bookfy.getMainWindowController().maskPane("Loading books from live server!");
        SearchHelper helper = new SearchHelper(sql);            
        helper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
               @Override
               public void handle(WorkerStateEvent t) {
                   //addData((ArrayList<PortedEnclosureRecommendation>)t.getSource().getValue());
                   masterBooks = (ArrayList<Book>)t.getSource().getValue();
                   for(Book b : masterBooks){
                        books.add(b);
                    }
                   displayBooks();
                   Bookfy.getMainWindowController().unmaskPane();
               }
           });
        helper.start(); 
    }
    
    public void sortBooks(){
        if(cboSort.getSelectionModel().getSelectedItem() != null){
            String selection = cboSort.getSelectionModel().getSelectedItem().toString();
            if(selection.startsWith("Book title")){
                Collections.sort(books, new BookTitleComparator());
            }
            else if(selection.startsWith("Author name")){
                Collections.sort(books, new BookAuthorComparator());
            }
            else if(selection.startsWith("Price")){
                Collections.sort(books, new BookPriceComparator());
            }
            else if(selection.startsWith("Book rating")){
                Collections.sort(books, new BookRatingComparator());
            }
            else if(selection.startsWith("Release date")){
                Collections.sort(books, new BookReleaseDateComparator()); 
            }
        }   
    }
    
    public void reverseList(){
        Collections.reverse(books);
    }
    
    int itemsPerPage = 9;
    public void displayBooks(){
        int pageCount = books.size() / itemsPerPage;
        if(books.size() % itemsPerPage != 0){
            pageCount++;
        }
        pages.setPageCount(pageCount);
        pages.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer pageIndex) {          
                return createPage(pageIndex);               
            }
        });
    }
    
    public AnchorPane createPage(int pageIndex) {        
        try {
            ArrayList<Book> b = new ArrayList<>();
            for(int i = pageIndex*itemsPerPage; i < (pageIndex+1)*itemsPerPage && i < books.size(); i++){
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
    
    private class BookTitleComparator implements Comparator<Book> {
        @Override
        public int compare(Book a, Book b) {
            return a.getTitle().compareToIgnoreCase(b.getTitle());
        }   
    }
    
    private class BookAuthorComparator implements Comparator<Book> {
        @Override
        public int compare(Book a, Book b) {
            return a.getAuthor().compareToIgnoreCase(b.getAuthor());
        }   
    }
    
    private class BookPriceComparator implements Comparator<Book> {
        @Override
        public int compare(Book a, Book b) {
            return a.getPrice() < b.getPrice() ? -1 : a.getPrice() == b.getPrice() ? 0 : 1;
        }   
    }
    
    private class BookRatingComparator implements Comparator<Book> {
        @Override
        public int compare(Book a, Book b) {
            return a.getRating() < b.getRating() ? -1 : a.getRating() == b.getRating() ? 0 : 1;
        }   
    }
    
    private class BookReleaseDateComparator implements Comparator<Book> {
        @Override
        public int compare(Book a, Book b) {
            try {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                Date date1 =  df.parse(a.getReleaseDate());
                Date date2 =  df.parse(b.getReleaseDate());
                
                return date1.compareTo(date2);
            } catch (ParseException ex) {
                Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 0;
        }   
    }
    
    class SearchHelper extends Service<ArrayList<Book>>{
        String sql;
        
        public SearchHelper(String sql){
            this.sql = sql;
        }
        
        protected Task<ArrayList<Book>> createTask() {
            return new Task<ArrayList<Book>>() {
                protected ArrayList<Book> call(){
                    ArrayList<Book> books = new ArrayList<>();
                    
                    /*
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }*/
                                        
                    
                    try {
                        ResultSet rs = Bookfy.getDatabaseHandler().execQuery(sql);
                        while(rs.next()){
                            int bookID = Integer.valueOf(rs.getString(1));
                            Book book = new Book(bookID);
                            books.add(book);
                        }
                    } catch (SQLException ex) {
                        //Logger.getLogger(Bookfy.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(null, ex);
                    }
                    catch (Exception e){
                        JOptionPane.showMessageDialog(null, e);
                    }

                    return books;
                }
            };
        }
    }
}