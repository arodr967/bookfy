/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookfy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Roger Boza
 */
public class Bookfy extends Application {
    private static final DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
    private static FXMLMainController mainWindowController;
    private static User user;
    private static String search;
    private static FXMLHomeController home;
    
    public enum Account {
        SIGNUP,
        UPDATE,
        DELETE
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLMain.fxml"));
        Parent root = loader.load();
        
        mainWindowController = loader.getController();
        
        search = "";
        
        Scene scene = new Scene(root);
        
        //if we need to start maxmized
        //stage.setMaximized(true);
        stage.setScene(scene);
        stage.setTitle("Bookfy");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public static DatabaseHandler getDatabaseHandler() {
        return databaseHandler;
    }

    public static FXMLMainController getMainWindowController() {
        return mainWindowController;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        Bookfy.user = user;
    }

    public static String getSearch() {
        return search;
    }

    public static void setSearch(String search) {
        Bookfy.search = search;
    }

    public static FXMLHomeController getHome() {
        return home;
    }

    public static void setHome(FXMLHomeController home) {
        Bookfy.home = home;
    }
}
