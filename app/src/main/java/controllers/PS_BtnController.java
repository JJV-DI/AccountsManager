package controllers;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import model.Tools;
import model.User;

public class PS_BtnController implements Initializable{
    
    private User userOwner;

    @FXML
    private Button btnUser;

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

    }

    @FXML
    void ctxtEditPressed() {

    }

    @FXML
    void ctxtMoveBackPressed() {

    }

    @FXML
    void ctxtMoveForPressed() {

    }

    @FXML
    void ctxtRemovePressed() {

    }

}
