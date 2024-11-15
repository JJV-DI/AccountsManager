package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdminNewPassConfirmController {

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
    void btnCancelPressed(ActionEvent event) {
        closeWin();
    }
    
    @FXML
    void btnClosePressed(ActionEvent event) {
        closeWin();
    }

    @FXML
    void btnConfirmPressed(ActionEvent event) {
        closeWin();
    }

    @FXML
    void btnTogglePassVisPressed(ActionEvent event) {

    }
    
    private void closeWin() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

}
