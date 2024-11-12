package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        socialNetworks = new SocialNetworkDAO().loadSocialNetworks();
        
        for (SocialNetwork socialNetwork : socialNetworks) {
            try {
                FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/vistas/frmSN_Card.fxml"));
                vBoxScrollBody.getChildren().add(fXMLLoader.load());
                SN_CardController sN_CardController = fXMLLoader.getController();
                sN_CardController.setSocialNetworkOwner(socialNetwork);
                sN_CardController.setText();
                sN_CardController.setImage();
            } catch (IOException e) {
                System.err.println("Error in " + this.getClass().toString() + " failed chargin social networks cards");
            }
            
        }
    }

}
