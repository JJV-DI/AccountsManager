package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class SettingsController {

    @FXML
    private ComboBox<?> cmbBoxTheme;

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

    }

    @FXML
    void updateDataDirPressed(ActionEvent event) {

    }

}
