package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import model.Tools;
import model.User;
import model.UserDAO;
import org.mariadb.jdbc.Connection;

public class ProfileSelectController implements Initializable{
    
    private ObservableList<User> users = FXCollections.emptyObservableList();
    
    @FXML
    private GridPane gridPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        users = new UserDAO().loadUsersFromDB();
        showUsers();
    }
    
    private void showUsers(){
        try {
            int index = 0;
            int rows = (int) Math.ceil((users.size() + 1) / 4);

            for (int row = 0; row < rows; row++) {

                if (rows > 1) gridPane.addRow(row, (Node) null);

                for (int col = 0; col < 4; col++) {
                    FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource("/vistas/frmPS_Btn.fxml"));
                    gridPane.add((Node) fxmlLoader.load(), row, col);
                    fxmlLoader.getController();
                    index++;
                }
            }
        } catch (IOException ex) {
            System.err.println("Error in " + this.getClass().toString() + " loading fxml file");
        }
    }

}