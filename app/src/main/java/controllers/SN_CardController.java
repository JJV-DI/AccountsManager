package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import model.Util.ConfigProvider;
import model.SocialNetwork;
import model.DAO.SocialNetworkDAO;

public class SN_CardController implements Initializable {

    private boolean adminExist;
    
    private SocialNetworkController socialNetworkController;
    
    private SocialNetwork socialNetworkOwner;
    
    @FXML
    private ImageView imgSocialNetwork;

    @FXML
    private Label txtSocialNetwork;

    @FXML
    void editSNPressed() {
        if (adminExist) {
            if (MainAppController.viewLoader.loadAdminPassConfirm() && MainAppController.viewLoader.loadSocialNetworkCreator(socialNetworkOwner)) {
                socialNetworkController.clearBody();
                socialNetworkController.loadSocialNetworks();
            }
        } else if (MainAppController.viewLoader.loadSocialNetworkCreator(socialNetworkOwner)) {
            socialNetworkController.clearBody();
            socialNetworkController.loadSocialNetworks();
        }
    }

    @FXML
    void removeSNPressed() {
        if (adminExist) {
            if (MainAppController.viewLoader.loadAdminPassConfirm() && MainAppController.viewLoader.loadDeletionConfirm("Social Network", socialNetworkOwner.getNombreRed())) {
                new SocialNetworkDAO().deleteSocialNetwork(socialNetworkOwner);
                socialNetworkController.clearBody();
                socialNetworkController.loadSocialNetworks();
            }
        } else if (MainAppController.viewLoader.loadDeletionConfirm("Social Network", socialNetworkOwner.getNombreRed())) {
            new SocialNetworkDAO().deleteSocialNetwork(socialNetworkOwner);
            socialNetworkController.clearBody();
            socialNetworkController.loadSocialNetworks();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Circle circleShape = new Circle(22.5,22.5,22.5);
        imgSocialNetwork.setClip(circleShape);
        String adminPass = new ConfigProvider().loadAdminPass();
        adminExist = !adminPass.isEmpty();
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
    
    public void setSocialNetworkController (SocialNetworkController snController) {
        this.socialNetworkController = snController;
    }

}
