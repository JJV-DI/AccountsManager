package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import model.Account;
import model.AccountDAO;
import model.User;
import model.ViewManager;
import model.ViewStatus;

public class UserInfoController implements Initializable{
    
    private List<Account> accounts = FXCollections.observableArrayList();
    
    private VBox vboxBody;
    
    private ViewManager viewManager;
    
    private User userOwner;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnToggleFilter;

    @FXML
    private ImageView imgFilter;

    @FXML
    private ImageView imgUserImage;

    @FXML
    private Label lblUserEmail;

    @FXML
    private Label lblUserName;

    @FXML
    private TextField txtSearch;

    @FXML
    private VBox vboxScrollBody;

    @FXML
    void btnEditPressed() {
        MainAppController.viewLoader.loadProfileCreator(vboxBody, viewManager, userOwner, true);
    }

    @FXML
    void btnToggleFilterPressed() {

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Circle clip = new Circle(30, 30, 30);
        imgUserImage.setClip(clip);
    }
    
    public void initComponents() {
        accounts = new AccountDAO().loadAccountsFromDB(userOwner);
        lblUserName.setText(userOwner.getUserName());
        lblUserEmail.setText(userOwner.getEmail());
        imgUserImage.setImage(userOwner.getImgUser());
        showAccountCards();
    }
    
    public void showAccountCards(){
        for (Account account : accounts){
            MainAppController.viewLoader.loadUserInfoAccountCards(vboxScrollBody, account);
        }
        MainAppController.viewLoader.loadUserInfoAccountAddButton(vboxScrollBody);
    }
    
    public void setVboxBody(VBox vboxBody) {
        this.vboxBody = vboxBody;
    }

    public void setUserOwner(User userOwner) {
        this.userOwner = userOwner;
    }

    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }
}
