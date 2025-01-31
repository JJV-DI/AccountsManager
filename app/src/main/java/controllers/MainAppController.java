package controllers;

import app.MainApp;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Util.ConfigProvider;
import model.Util.ViewLoader;
import model.Util.ViewManager;
import model.Util.ViewStatus;

public class MainAppController implements Initializable{
    
    public static ViewLoader viewLoader = new ViewLoader();
    
    private static List<Button> buttons = new ArrayList<>();
    
    private Stage guideStage;
    
    private boolean guideShown = false;
    
    @FXML
    private Button btnGuide;
    
    @FXML
    private Button btnReports;
    
    @FXML
    private Button btnPower;

    @FXML
    private Button btnProfiles;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnSocial;
    
    @FXML
    private VBox vbBody;
    
    @FXML
    private GridPane paneGridBody;
    
    
    private ViewManager viewManager = new ViewManager();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        profilesPressed();
        buttons.add(btnProfiles);
        buttons.add(btnSocial);
        buttons.add(btnSettings);
        buttons.add(btnReports);
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
    void informPressed() {
        loadReports();
    }
    
    @FXML
    void powerPressed() {
        MainApp.closeApp();
    }
    
    @FXML
    void guidePressed() {
        if (!guideShown) {
            guideShown = true;            
            guideStage = new ViewLoader().loadUsersGuideDisplay();
            guideStage.setOnCloseRequest(event2 -> {
                guideShown = false;
                guideStage = null;                
                styleGuideButton(guideShown);
            });
            styleGuideButton(guideShown);
        }
    }
    
    private void loadProfiles() {
        buttonSelected(btnProfiles);
        if (viewManager.getViewStatus() != ViewStatus.PROFILES) {
            viewManager.setStatus(ViewStatus.PROFILES);
            viewLoader.loadProfileSelect(vbBody, viewManager);
        }
    }
    
    private void loadSocialNetworks() {
        buttonSelected(btnSocial);
        if (viewManager.getViewStatus() != ViewStatus.SOCIAL) {
            viewManager.setStatus(ViewStatus.SOCIAL);
            viewLoader.loadSocialNetworks(vbBody, viewManager);
        }
    }
    
    private void loadSettings() {
        buttonSelected(btnSettings);
        if (viewManager.getViewStatus() != ViewStatus.SETTINGS) {
            viewManager.setStatus(ViewStatus.SETTINGS);
            viewLoader.loadSettings(vbBody);
        }
    }
    
    private void loadReports() {
        if (viewManager.getViewStatus() != ViewStatus.REPORTS) {
            if (!new ConfigProvider().loadAdminPass().isEmpty()) {
                if (MainAppController.viewLoader.loadAdminPassConfirm()) {
                    viewManager.setStatus(ViewStatus.REPORTS);
                    viewLoader.loadReports(vbBody);
                    buttonSelected(btnReports);
                }
            } else {
                viewManager.setStatus(ViewStatus.REPORTS);
                viewLoader.loadReports(vbBody);
                buttonSelected(btnReports);
            }
        }
    }
    
    private void buttonSelected(Button button) {
        resetButtonsStyle();
        button.getStyleClass().clear();
        button.getStyleClass().add("primary-color");
        button.getStyleClass().add("saw-border");
    }
    
    public static void resetButtonsStyle(){
        for(Button button : buttons) {
            button.getStyleClass().clear();
            button.getStyleClass().add("secondary-color");
            button.getStyleClass().add("saw-border");
            button.getStyleClass().add("hover-color");
        }
    }
    
    //Users guide
    public void initGuideListener() {
        paneGridBody.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.F1 && !guideShown) {
                guideShown = true;
                guideStage = new ViewLoader().loadUsersGuideDisplay();
                guideStage.setOnCloseRequest(event2 -> {
                    guideShown = false;
                    guideStage = null;
                    styleGuideButton(guideShown);
                });
                styleGuideButton(guideShown);
            }
        });
    }
    
    private void styleGuideButton(boolean selected) {
        btnGuide.getStyleClass().clear();
        if (selected) {
            btnGuide.getStyleClass().add("primary-color");
            btnGuide.getStyleClass().add("saw-border");
        } else {
            btnGuide.getStyleClass().add("secondary-color");
            btnGuide.getStyleClass().add("saw-border");
            btnGuide.getStyleClass().add("hover-color");
        }
    }
}