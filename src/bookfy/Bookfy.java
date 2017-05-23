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
    private static DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
    
    @Override
    public void start(Stage stage) throws Exception {
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLMain.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLCreateAccount.fxml"));
        Parent root = loader.load();
        
        Scene scene = new Scene(root);
        
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
}
