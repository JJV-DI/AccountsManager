package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import model.SocialNetwork;

public class SN_CardController implements Initializable {

    private SocialNetwork socialNetworkOwner;
    
    @FXML
    private ImageView imgSocialNetwork;

    @FXML
    private Label txtSocialNetwork;

    @FXML
    void editSNPressed(ActionEvent event) {

    }

    @FXML
    void removeSNPressed(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Circle circleShape = new Circle(25, 25, 25);
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
