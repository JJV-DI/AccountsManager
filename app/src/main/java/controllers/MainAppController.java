package controllers;

import app.MainApp;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Util.ViewLoader;
import model.Util.ViewManager;
import model.Util.ViewStatus;

public class MainAppController implements Initializable{
    
    public static ViewLoader viewLoader = new ViewLoader();
    
    @FXML
    private VBox vbBody;
    
    @FXML
    private GridPane paneGridBody;
    
    
    private ViewManager viewManager = new ViewManager();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        profilesPressed();
    }
    
    @FXML
    void profilesPressed() {
        loadProfiles();
    }

    @FXML
    void socialPressed() {
        loadSocialNetworks();
    }

    @FXML
    void settingsPressed() {
        loadSettings();
    }
    
    @FXML
    void powerPressed() {
        MainApp.closeApp();
    }
    
    private void loadProfiles() {
        if (viewManager.getViewStatus() != ViewStatus.PROFILES) {
            viewManager.setStatus(ViewStatus.PROFILES);
            viewLoader.loadProfileSelect(vbBody, viewManager);
        }
    }
    
    private void loadSocialNetworks() {
        if (viewManager.getViewStatus() != ViewStatus.SOCIAL) {
            viewManager.setStatus(ViewStatus.SOCIAL);
            viewLoader.loadSocialNetworks(vbBody, viewManager);
        }
    }
    
    private void loadSettings() {
        if (viewManager.getViewStatus() != ViewStatus.SETTINGS) {
            viewManager.setStatus(ViewStatus.SETTINGS);
            viewLoader.loadSettings(vbBody);
        }
    }
}
