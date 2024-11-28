package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import model.User;
import model.UserDAO;
import model.ViewManager;

public class PS_BtnController implements Initializable{
    
    private ProfileSelectController psController;
    
    private ViewManager viewManager;
    
    private VBox vboxBody;
    
    private User userOwner;

    @FXML
    public Button btnUser;

    @FXML
    private ImageView imgUserImage;

    @FXML
    private ContextMenu ctxtMenu;
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        Circle circleShape = new Circle(25, 25, 25);
        imgUserImage.setClip(circleShape);
    }
    
    public void setUserOwner(User userOwner) {
        this.userOwner = userOwner;
    }
    
    public void setImage(){
        imgUserImage.setPreserveRatio(true);
        imgUserImage.setImage(userOwner.getImgUser());
    }
    
    public void setText(){
        btnUser.setText(userOwner.getUserName());
    }

    @FXML
    void btnUserPressed() {
        if (userOwner.isPrivate()) {
            if (MainAppController.viewLoader.loadUserPassConfirm(userOwner)) MainAppController.viewLoader.loadUserInfo(vboxBody, viewManager, userOwner);
        } else {
            MainAppController.viewLoader.loadUserInfo(vboxBody, viewManager, userOwner);
        }
    }

    @FXML
    void ctxtEditPressed() {
        if (userOwner.isPrivate()) {
            if (MainAppController.viewLoader.loadUserPassConfirm(userOwner)) MainAppController.viewLoader.loadProfileCreator(vboxBody, viewManager, userOwner, true);
        } else {
            MainAppController.viewLoader.loadProfileCreator(vboxBody, viewManager, userOwner, true);
        }
    }

    @FXML
    void ctxtMoveBackPressed() {
        /*Mueve el botón del usuario un puesto anterior en el grid pane*/
    }

    @FXML
    void ctxtMoveForPressed() {
        /*Mueve el botón del usuario un puesto posterior en el grid pane*/
    }

    @FXML
    void ctxtRemovePressed() {
        if (userOwner.isPrivate()) {
            if (MainAppController.viewLoader.loadUserPassConfirm(userOwner) && MainAppController.viewLoader.loadDeletionConfirm("user", userOwner.getEmail())) {
                new UserDAO().deleteUser(userOwner);
                psController.loadUsers();
                psController.showUsers();
            }
        } else if (MainAppController.viewLoader.loadDeletionConfirm("user", userOwner.getEmail())) {
            new UserDAO().deleteUser(userOwner);
            psController.loadUsers();
            psController.showUsers();
        }
    }
    
    public void setVboxBody(VBox vboxBody) {
        this.vboxBody = vboxBody;
    }

    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void setPsController(ProfileSelectController psController) {
        this.psController = psController;
    }
}
