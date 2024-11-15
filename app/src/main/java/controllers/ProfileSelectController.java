package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.User;
import model.UserDAO;
import model.ViewLoader;
import model.ViewManager;

public class ProfileSelectController implements Initializable{
    
    private ViewManager viewManager;
    
    private ObservableList<User> users = FXCollections.observableArrayList();
    
    private VBox vboxBody;
    
    @FXML
    private GridPane gridPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        users = new UserDAO().loadUsersFromDB();
    }
    
    public void showUsers(){
        int index = 0;
        int rows = ((users.size() + 1) + 4 - 1) / 4; 
        boolean addSetted = false;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < 4; col++) {
                if (index < users.size()) {
                    MainAppController.viewLoader.loadUserButton(gridPane, col, row, users, index, vboxBody, viewManager);
                    index++;   
                } else if (!addSetted) {
                    MainAppController.viewLoader.loadAddButton(gridPane, col, row, vboxBody, viewManager);
                    addSetted = true;
                }
            }
        }
    }
    
    public void setVboxBody(VBox vboxBody){
        this.vboxBody = vboxBody;
    }

    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
        System.out.println(viewManager.getViewStatus());
    }
}