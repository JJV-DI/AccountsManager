package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.stage.Stage;
import model.Util.ConfigProvider;
import model.Util.FieldValidator;
import model.Util.FloatingPopup;

public class AdminUpdatePassConfirmController {

    private boolean result;
    
    @FXML
    private Button btnCancel;
    
    @FXML
    private Button btnClose;
    
    @FXML
    private Button btnConfirm;

    @FXML
    private TextField txtCurAdminPass;

    @FXML
    private TextField txtNewAdminPass;

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
    void btnConfirmPressed(ActionEvent event) {
        if (new ConfigProvider().loadAdminPass().equals(txtCurAdminPass.getText())) {
            if (FieldValidator.emptinessValidation(txtNewAdminPass.getText())) {
                new ConfigProvider().saveAdminPass(txtNewAdminPass.getText());
                result = true;
                closeWin();
            } else {
                FloatingPopup.showTextFieldPopup(txtNewAdminPass, "Password must not be empty");
            }
        } else {
            FloatingPopup.showTextFieldPopup(txtCurAdminPass, "Password does not match\n with current password");
        }
    }
    
    private void closeWin() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    public boolean getResult() {
        return result;
    }
}
