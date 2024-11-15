package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class SocialNetworkCreatorController {

    private boolean result;
    
    @FXML
    private Button btnCancel;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnEditImg;

    @FXML
    private Button btnRemoveImg;

    @FXML
    private Button btnSave;

    @FXML
    private ImageView imgSocialNetwork;

    @FXML
    private TextField txtSNName;

    @FXML
    void btnCancelPressed(ActionEvent event) {
        result = false;
        closeWin();
    }

    @FXML
    void btnClosePressed(ActionEvent event) {
        result = false;
        closeWin();
    }
    
    @FXML
    void btnSavePressed(ActionEvent event) {
        result = true;
        closeWin();
    }

    @FXML
    void btnEditImgPressed(ActionEvent event) {

    }

    @FXML
    void btnRemoveImgPressed(ActionEvent event) {

    }
    
    private void closeWin() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

}
