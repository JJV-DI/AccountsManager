package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.ConfigProvider;
import model.SocialNetwork;
import model.SocialNetworkDAO;

public class SocialNetworkController implements Initializable {
    
    private boolean adminExist;

    private ObservableList<SocialNetwork> socialNetworks = FXCollections.observableArrayList();
    
    @FXML
    private Label txtSeachSocialNetwork;

    @FXML
    private VBox vBoxScrollBody;

    @FXML
    void addSocialNetworkPressed() {
        if (adminExist) {
            if (MainAppController.viewLoader.loadAdminPassConfirm() && MainAppController.viewLoader.loadSocialNetworkCreator()) {
                clearBody();
                loadSocialNetworks();
            }
        } else if(MainAppController.viewLoader.loadSocialNetworkCreator()) {
            clearBody();
            loadSocialNetworks();
        }
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String adminPass = new ConfigProvider().loadAdminPass();
        adminExist = !adminPass.isEmpty();
        loadSocialNetworks();
    }
    
    public void loadSocialNetworks() {
        socialNetworks = new SocialNetworkDAO().loadSocialNetworks();
        for (SocialNetwork socialNetwork : socialNetworks) {
            MainAppController.viewLoader.loadSocialNetworksCard(vBoxScrollBody, socialNetwork, this);
        }
    }
    
    public void clearBody() {
        vBoxScrollBody.getChildren().clear();
    }
}
