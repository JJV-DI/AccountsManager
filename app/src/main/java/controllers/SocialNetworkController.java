package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import model.Util.ConfigProvider;
import model.SocialNetwork;
import model.DAO.SocialNetworkDAO;
import model.Util.FieldValidator;

public class SocialNetworkController implements Initializable {
    
    private boolean adminExist;

    private ObservableList<SocialNetwork> socialNetworks = FXCollections.observableArrayList();
    
    @FXML
    private TextField txtSearchSocialNetwork;

    @FXML
    private VBox vBoxScrollBody;

    @FXML
    void addSocialNetworkPressed() {
        if (adminExist) {
            if (MainAppController.viewLoader.loadAdminPassConfirm() && MainAppController.viewLoader.loadSocialNetworkCreator()) loadSocialNetworks();
        } else if(MainAppController.viewLoader.loadSocialNetworkCreator()) loadSocialNetworks();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String adminPass = new ConfigProvider().loadAdminPass();
        adminExist = !adminPass.isEmpty();
        initSearchAction();
        loadSocialNetworks();
    }
    
    private void initSearchAction() {
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event -> {
            if (validateSearch() && !txtSearchSocialNetwork.getText().isEmpty()) {
                loadSocialNetworks(new SocialNetworkDAO().loadSocialNetworksByName(txtSearchSocialNetwork.getText()));
            } else {
                loadSocialNetworks();
            }
            
        });
        txtSearchSocialNetwork.textProperty().addListener((ObservableList, oldValue, newValue) -> {
            pause.playFromStart();
        });
    }
    
    public void loadSocialNetworks(ObservableList<SocialNetwork> socialNetworks) {
        clearBody();
        this.socialNetworks = socialNetworks;
        if (socialNetworks == null || socialNetworks.isEmpty()) {
            MainAppController.viewLoader.loadSocialNetworksNotFoundCard(vBoxScrollBody);
        } else {
            for (SocialNetwork socialNetwork : socialNetworks) {
                MainAppController.viewLoader.loadSocialNetworksCard(vBoxScrollBody, socialNetwork, this);
            }
        }
    }
    
    public void loadSocialNetworks() {
        loadSocialNetworks(new SocialNetworkDAO().loadSocialNetworks());
    }
    
    public void clearBody() {
        vBoxScrollBody.getChildren().clear();
    }
    
    private boolean validateSearch() {
        boolean searchMatchRegex = true;
        if (txtSearchSocialNetwork.getText() != null && !txtSearchSocialNetwork.getText().isEmpty()) {
            searchMatchRegex = FieldValidator.commonNameValidation(txtSearchSocialNetwork.getText());
        }
        FieldValidator.toggleTextFieldSecondaryInError(!searchMatchRegex, txtSearchSocialNetwork, "The search can only contain aplhanumeric characters or simple symbols (-_@.)\n");
        return searchMatchRegex;
    }
}
