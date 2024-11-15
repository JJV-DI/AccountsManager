package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdminUpdatePassConfirmController {

    @FXML
    private Button btnCancel;
    
    @FXML
    private Button btnClose;
    
    @FXML
    private Button btnConfirm;

    @FXML
    private Button btnToggleCurPassVis;

    @FXML
    private Button btnToggleNewPassVis;

    @FXML
    private TextField txtCurAdminPass;

    @FXML
    private TextField txtNewAdminPass;

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
    void btnToggleCurPassVisPressed(ActionEvent event) {

    }

    @FXML
    void btnToggleNewPassVisPressed(ActionEvent event) {

    }
    
    private void closeWin() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

}
