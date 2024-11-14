package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import model.ViewStatus;

public class MainAppController implements Initializable{

    @FXML
    private VBox vbBody;
    
    private ViewStatus viewStatus;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        profilesPressed();
    }
    
    @FXML
    void profilesPressed() {
        if (viewStatus != ViewStatus.PROFILES) {
            untoggleAllToolbarButtons();
            viewStatus = ViewStatus.PROFILES;
            /*CAMBIAR COLOR FONDO -> GRIS CLARO*/
            vbBody.getChildren().clear();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistas/frmProfileSelect.fxml"));
                vbBody.getChildren().add(fxmlLoader.load());
                ProfileSelectController profileSelectController = fxmlLoader.getController();
                profileSelectController.setVboxBody(vbBody);
                profileSelectController.showUsers();
            } catch (IOException ex) {
                System.err.println("Error in " + this.getClass().toString() + " loading fxml file");
            }
            System.out.println("Profiles selected");
        }
    }

    @FXML
    void socialPressed() {
        if (viewStatus != ViewStatus.SOCIAL) {
            untoggleAllToolbarButtons();
            viewStatus = ViewStatus.SOCIAL;
            /*CAMBIAR COLOR FONDO -> GRIS CLARO*/
            vbBody.getChildren().clear();
            try {
                vbBody.getChildren().add(FXMLLoader.load(getClass().getResource("/vistas/frmSocialNetworks.fxml")));
            } catch (IOException ex) {
                System.err.println("Error in " + this.getClass().toString() + " loading fxml file");
            }
            System.out.println("Social selected");
        }
    }

    @FXML
    void settingsPressed() {
        if (viewStatus != ViewStatus.SETTINGS) {
            untoggleAllToolbarButtons();
            viewStatus = ViewStatus.SETTINGS;
            /*CAMBIAR COLOR FONDO -> GRIS CLARO*/
            vbBody.getChildren().clear();
            try {
                vbBody.getChildren().add(FXMLLoader.load(getClass().getResource("/vistas/frmSettings.fxml")));
            } catch (IOException ex) {
                System.err.println("Error in " + this.getClass().toString() + " loading fxml file");
            }
            System.out.println("Settings selected");
        }
    }
    
    @FXML
    void powerPressed() {

    }
    
    private void untoggleAllToolbarButtons(){
        setBtnBcColor("profile", false);
        setBtnBcColor("social", false);
        setBtnBcColor("settings", false);
    }
    
    private void setBtnBcColor(String typeButton, boolean sel){
        switch (typeButton) {
            case "profile" -> {
                
            }
            case "social" -> {
                
            }
            case "settings" -> {
                
            }
        }
    }
}
