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
import javafx.scene.layout.GridPane;
import model.User;
import model.UserDAO;

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
            int rows = ((users.size() + 1) + 4 - 1) / 4; 
            boolean addSetted = false;
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < 4; col++) {
                    if (index < users.size()) {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistas/frmPS_Btn.fxml"));
                        gridPane.add((Node) fxmlLoader.load(), col, row);
                        PS_BtnController pS_BtnController = fxmlLoader.getController();
                        pS_BtnController.setUserOwner(users.get(index));
                        pS_BtnController.setText();
                        pS_BtnController.setImage();
                        index++;   
                    } else if (!addSetted) {
                        gridPane.add((Node) FXMLLoader.load(getClass().getResource("/vistas/frmPS_Add.fxml")), col, row);
                        addSetted = true;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error in " + this.getClass().toString() + " loading fxml file");
            System.err.println(e.getMessage());
        }
    }
    
    
}