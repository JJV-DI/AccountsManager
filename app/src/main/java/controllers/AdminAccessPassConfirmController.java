package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Util.ConfigProvider;
import model.Util.FloatingPopup;

public class AdminAccessPassConfirmController {

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
        if (new ConfigProvider().loadAdminPass().equals(txtAdminPass.getText())) {
            result = true;
            closeWin();
        } else {
            FloatingPopup.showTextFieldPopup(txtAdminPass, "Password does not match\n with current password");
        }
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
