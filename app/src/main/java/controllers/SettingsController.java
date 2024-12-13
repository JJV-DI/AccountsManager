package controllers;

import app.MainApp;
import java.io.IOException;
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
        cmbBoxTheme.setValue(new ConfigProvider().loadTheme());
        loadAdminPassGraphics();
        cmbBoxTheme.valueProperty().addListener((observable, oldValue, newValue) -> {
            new ConfigProvider().saveTheme(newValue);
            MainApp.restartApp();
        });
    }
    
    private void loadAdminPassGraphics() {
        String adminPass = new ConfigProvider().loadAdminPass();
        if (!adminPass.isEmpty()) {
            imgAdminStatus.setImage(new Image("/vistas/media/cur_icons/key.png"));
            imgAddRefreshPass.setImage(new Image("/vistas/media/cur_icons/refresh.png"));
            String dotPass = "";
            for (int i = 0; i < adminPass.length(); i++) {
                dotPass += "●";
            }
            lblAdminPass.setText(dotPass);
            passExist = true;
            toolTipAdminStatus.setText("Protected");
            btnRemoveAdminPass.setDisable(false);
        } else {
            imgAdminStatus.setImage(new Image("/vistas/media/cur_icons/keyCrossed.png"));
            imgAddRefreshPass.setImage(new Image("/vistas/media/cur_icons/addNotRounded.png"));            
            lblAdminPass.setText("Not administrator set");
            passExist = false;
            toolTipAdminStatus.setText("Unprotected");
            btnRemoveAdminPass.setDisable(true);
        }
    }
}
