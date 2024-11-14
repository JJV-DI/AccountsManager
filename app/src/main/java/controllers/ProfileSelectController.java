package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.User;
import model.UserDAO;

public class ProfileSelectController implements Initializable{
    
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
                    initUserButton(index, col, row);
                    index++;   
                } else if (!addSetted) {
                    initAddButton(col, row);
                    addSetted = true;
                }
            }
        }
    }
    
    public void setVboxBody(VBox vboxBody){
        this.vboxBody = vboxBody;
    }
    
    private void initUserButton(int index, int col, int row) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistas/frmPS_Btn.fxml"));
            gridPane.add((Node) fxmlLoader.load(), col, row);
            PS_BtnController ps_BtnController = fxmlLoader.getController();
            ps_BtnController.setUserOwner(users.get(index));
            ps_BtnController.setText();
            ps_BtnController.setImage();
            ps_BtnController.setVboxBody(vboxBody);
        } catch (IOException e) {
            System.err.println("Error in " + this.getClass().toString() + " loading user button fxml file");
            System.err.println(e.getMessage());
        }
    }
    
    private void initAddButton(int col, int row){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistas/frmPS_Add.fxml"));
            gridPane.add((Node) fxmlLoader.load(), col, row);
            PS_AddController ps_AddController = fxmlLoader.getController();
            ps_AddController.setVboxBody(vboxBody);
        } catch (IOException ex) {
                System.err.println("Error in " + this.getClass().toString() + " loading profile creator fxml file");
                System.err.println(ex.getCause());
        }
    }
}