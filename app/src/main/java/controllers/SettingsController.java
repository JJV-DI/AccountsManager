package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class SettingsController implements Initializable{

    @FXML
    private ComboBox<String> cmbBoxTheme;

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
    void checkUpdatePressed(ActionEvent event) {

    }

    @FXML
    void setAdminPassPressed(ActionEvent event) {
        
        /*NEW ADMIN PASS*/
        MainAppController.viewLoader.loadAdminNewPassConfirm();
        
        /*UPDATE ADMIN PASS*/
        MainAppController.viewLoader.loadAdminUpdatePassConfirm();
    }

    @FXML
    void updateDataDirPressed(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbBoxTheme.getItems().add("Dark theme");
        cmbBoxTheme.getItems().add("Light theme");
        cmbBoxTheme.setValue("Dark theme");
    }

}
