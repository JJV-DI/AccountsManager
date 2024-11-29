package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import model.SocialNetwork;

public class AC_Tab_BtnController implements Initializable{

    private SocialNetwork socialNetworkOwner;
    
    @FXML
    public Button btnSocialNetwork;

    @FXML
    private ImageView imgSocialNetwork;

    @FXML
    private Label lblSocialNetwork;

    @FXML
    void btnSocialNetworkPressed(ActionEvent event) {

    }

    public void setSocialNetworkOwner(SocialNetwork socialNetworkOwner) {
        this.socialNetworkOwner = socialNetworkOwner;
        initComponents();
    }

    public SocialNetwork getSocialNetworkOwner() {
        return socialNetworkOwner;
    }
    

    private void initComponents() {
        imgSocialNetwork.setImage(socialNetworkOwner.getIconoRed());
        lblSocialNetwork.setText(socialNetworkOwner.getNombreRed());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Circle clip = new Circle(17.5, 17.5, 17.5);
        imgSocialNetwork.setClip(clip);
    }
}
