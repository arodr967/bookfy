/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookfy;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author Roger Boza
 */
public class Book{
    private int ID;
    private String title;
    private String author;
    private String genre;
    private double price;
    private String description;
    private double rating;
    private boolean topSeller;
    private Image image;
    private boolean bookfyTimes;

    public Book(int ID, String title, String author, String genre, double price, String description, double rating, boolean topSeller, Image image, boolean bookfyTimes) {
        this.ID = ID;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.price = price;
        this.description = description;
        this.rating = rating;
        this.bookfyTimes = bookfyTimes;
        this.topSeller = topSeller;
        this.image = image;
    }

    public Book(int ID) throws InvalidBookException{
        this.ID = ID;
        loadBook();
    }

    private void loadBook() throws InvalidBookException{
        try {
            String sql = "SELECT Title, Author, Genre, Price, description, Rating, TopSeller, BookfyTimes, BookCover from book WHERE BookId = " + ID;
            ResultSet rs = Bookfy.getDatabaseHandler().execQuery(sql);

            if(rs.next()){
                title = rs.getString(1);
                author = rs.getString(2);
                genre = rs.getString(3);
                price = rs.getDouble(4);
                description = rs.getString(5);
                rating =  rs.getInt(6);
                topSeller = rs.getBoolean(7);                
                bookfyTimes = rs.getBoolean(8);
                Blob blob = rs.getBlob(9);
                if(blob != null){
                    InputStream is = blob.getBinaryStream();
                    image = new Image(is);
                    is.close();
                }
            }
            else{
                throw new InvalidBookException("There is no book with ID = " + ID + " in the database.");
            }
        } catch (SQLException ex) {
            //Logger.getLogger(Bookfy.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public boolean isTopSeller() {
        return topSeller;
    }

    public void setTopSeller(boolean topSeller) {
        this.topSeller = topSeller;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public boolean isBookfyTimes() {
        return bookfyTimes;
    }

    public void setBookfyTimes(boolean bookfyTimes) {
        this.bookfyTimes = bookfyTimes;
    }
    

    
}
