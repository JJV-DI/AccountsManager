package controllers;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Util.ConfigProvider;

public class SettingsController implements Initializable{

    private boolean passExist = false;
    
    @FXML
    private ComboBox<String> cmbBoxTheme;

    @FXML
    private ImageView imgAddRefreshPass;
    
    @FXML
    private ImageView imgAdminStatus;

    @FXML
    private ImageView imgCheckUpdateBtnIcon;

    @FXML
    private Label lblActualVersion;

    @FXML
    private Label lblAdminPass;

    @FXML
    private Label lblDataDir;

    @FXML
    private Hyperlink lnkOfficialWeb;

    @FXML
    private Hyperlink lnkSupport;

    @FXML
    private Label txtSeachSocialNetwork;
    
    @FXML
    private Tooltip toolTipAdminStatus;
    
    @FXML
    private Button btnApplyTheme;
    
    @FXML
    private Button btnRemoveAdminPass;

    @FXML
    void btnApplyThemePressed(ActionEvent event) {
        switch (cmbBoxTheme.getValue()) {
            case "Dark theme" -> {
            
            }
        }
    }
    
    @FXML
    void checkUpdatePressed() {

    }

    @FXML
    void setAdminPassPressed() {
        if (passExist) MainAppController.viewLoader.loadAdminUpdatePassConfirm();
        else MainAppController.viewLoader.loadAdminNewPassConfirm();
        loadAdminPassGraphics();
    }

    @FXML
    void updateDataDirPressed() {

    }
    
    @FXML
    void btnRemoveAdminPassPressed() {
        if (MainAppController.viewLoader.loadAdminPassConfirm()) {
            new ConfigProvider().saveAdminPass("");
            loadAdminPassGraphics();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbBoxTheme.getItems().add("Dark theme");
        cmbBoxTheme.getItems().add("Light theme");
        cmbBoxTheme.setValue("Dark theme");
        loadAdminPassGraphics();
    }
    
    private void loadAdminPassGraphics() {
        String adminPass = new ConfigProvider().loadAdminPass();
        if (!adminPass.isEmpty()) {
            imgAdminStatus.setImage(new Image("/vistas/media/icon/key_darkMode.png"));
            imgAddRefreshPass.setImage(new Image("/vistas/media/icon/refresh_darkMode.png"));
            String dotPass = "";
            for (int i = 0; i < adminPass.length(); i++) {
                dotPass += "●";
            }
            lblAdminPass.setText(dotPass);
            passExist = true;
            toolTipAdminStatus.setText("Protected");
            btnRemoveAdminPass.setDisable(false);
        } else {
            imgAdminStatus.setImage(new Image("/vistas/media/icon/keyCrossed_darkMode.png"));
            imgAddRefreshPass.setImage(new Image("/vistas/media/icon/addNotRounded_darkMode.png"));            
            lblAdminPass.setText("Not administrator set");
            passExist = false;
            toolTipAdminStatus.setText("Unprotected");
            btnRemoveAdminPass.setDisable(true);
        }
    }
    
    private void replaceStyleSheet(String mode) {
        Path mainStyleFile = Paths.get("/vistas/styles/mainStyle.css");
        
        switch (mode) {
            case "Dark theme" -> {
                Path darkStyleFile = Paths.get("/vistas/styles/darkMode.css");
                if (Files.deleteIfExists(mainStyleFile)) {
                    Files.copy(mainStyleFile, darkStyleFile);
                }
            }
            case "Light theme" -> {
                Path lightStyleFile = Paths.get("/vistas/styles/lightMode.css");
            }
        }
    }

}
