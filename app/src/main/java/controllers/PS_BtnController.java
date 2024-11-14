package controllers;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import model.Tools;
import model.User;

public class PS_BtnController implements Initializable{
    
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
        
    }

    @FXML
    void ctxtEditPressed() {
        vboxBody.getChildren().clear();
        try {
            FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("/vistas/frmProfileCreator.fxml"));
            vboxBody.getChildren().add(fxmlLoader2.load());
            ProfileCreatorController profileCreatorController = fxmlLoader2.getController();
            profileCreatorController.setUserOwner(userOwner);
            profileCreatorController.setVboxBody(vboxBody);
            profileCreatorController.initComponents(true);
        } catch (IOException ex) {
            System.err.println("Error in " + this.getClass().toString() + " loading profile creator fxml file");
            System.err.println(ex.getCause());
        }
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
    
    public void setVboxBody(VBox vboxBody) {
        this.vboxBody = vboxBody;
    }

}
