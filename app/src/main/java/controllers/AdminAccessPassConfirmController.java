package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
        result = true;
        closeWin();
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
