package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.stage.Stage;
import model.ConfigProvider;
import model.FieldValidator;
import model.FloatingPopup;

public class AdminNewPassConfirmController {
    
    private boolean result;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnClose;
    
    @FXML
    private Button btnConfirm;

    @FXML
    private Button btnTogglePassVis;

    @FXML
    private TextField txtAdminPass;

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
    void btnConfirmPressed() {
        if (FieldValidator.emptinessValidate(txtAdminPass.getText())) {
            new ConfigProvider().saveAdminPass(txtAdminPass.getText());
            result = true;
            closeWin();
        } 
        else FloatingPopup.showPopup(txtAdminPass, "Password must not be empty");
    }

    @FXML
    void btnTogglePassVisPressed() {

    }
    
    private void closeWin() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    public boolean getResult() {
        return result;
    }
}
