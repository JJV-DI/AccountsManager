package controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.SocialNetwork;
import model.SocialNetworkDAO;

public class AccountCreatorController implements Initializable{
    
    private List<SocialNetwork> socialNetworks = FXCollections.observableArrayList();

    private boolean result;

    @FXML
    private VBox vboxScrollBody;

    @FXML
    private VBox vboxSocialNetworkTab;
    
    @FXML
    private Button btnCancel;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnSocialNetwork;

    @FXML
    private Button btnTooglePassVis;

    @FXML
    private Label lblSocialNetworkSelected;

    @FXML
    private TextField txtSearchSN;
    
    @FXML
    private TextField txtAccountName;

    @FXML
    private TextField txtAccountPass;
    
    @FXML
    private HBox hboxBody;

    
    public boolean getResult() {
        return result;
    }
    
    @FXML
    void btnCancelPressed() {
        result = false;
        closeWin();
    }

    @FXML
    void btnClosePressed() {
        result = false;
        closeWin();
    }

    @FXML
    void btnSavePressed() {
        result = true;
        closeWin();
    }

    @FXML
    void btnSocialNetworkPressed() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        if (vboxSocialNetworkTab.isManaged()) {
            stage.setWidth(702);
        } else {
            stage.setWidth(952);
        }
        vboxSocialNetworkTab.setManaged(!vboxSocialNetworkTab.isManaged());
        vboxSocialNetworkTab.setVisible(!vboxSocialNetworkTab.isVisible());      
    }

    private void closeWin() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        socialNetworks = new SocialNetworkDAO().loadSocialNetworks();
        vboxSocialNetworkTab.setManaged(false);
        vboxSocialNetworkTab.setVisible(false);
        showSocialNetworks();
    }

    private void showSocialNetworks(){
        for(SocialNetwork socialNetwork : socialNetworks) {
            MainAppController.viewLoader.loadAccountCreatorSocialNetworks(vboxScrollBody, socialNetwork);
        }
    }
}
