package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.SocialNetwork;

public class SN_CardController implements Initializable {

    private SocialNetwork socialNetworkOwner;
    
    @FXML
    private ImageView imgSocialNetwork;

    @FXML
    private Label txtSocialNetwork;

    @FXML
    void editSNPressed() {
        MainAppController.viewLoader.loadAdminPassConfirm();
    }

    @FXML
    void removeSNPressed() {
        MainAppController.viewLoader.loadAdminPassConfirm();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Circle circleShape = new Circle(22.5,22.5,22.5);
        imgSocialNetwork.setClip(circleShape);
        
    }
    
    public void setSocialNetworkOwner(SocialNetwork socialNetwork) {
        socialNetworkOwner = socialNetwork;
    }
    
    public void setText(){
        txtSocialNetwork.setText(socialNetworkOwner.getNombreRed());
    }
    
    public void setImage(){
        imgSocialNetwork.setImage(socialNetworkOwner.getIconoRed());
    }

}
