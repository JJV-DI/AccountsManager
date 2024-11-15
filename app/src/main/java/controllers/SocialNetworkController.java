package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.SocialNetwork;
import model.SocialNetworkDAO;

public class SocialNetworkController implements Initializable {

    private ObservableList<SocialNetwork> socialNetworks = FXCollections.observableArrayList();
    
    @FXML
    private Label txtSeachSocialNetwork;

    @FXML
    private VBox vBoxScrollBody;

    @FXML
    void addSocialNetworkPressed() {
        if (MainAppController.viewLoader.loadAdminPassConfirm()) MainAppController.viewLoader.loadSocialNetworkCreator();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadAccounts();
    }
    
    private void loadAccounts() {
        socialNetworks = new SocialNetworkDAO().loadSocialNetworks();
        for (SocialNetwork socialNetwork : socialNetworks) {
            MainAppController.viewLoader.loadSocialNetworksAccounts(vBoxScrollBody, socialNetwork);
        }
    }
}
