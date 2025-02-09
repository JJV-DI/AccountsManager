package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Util.FloatingPopup;
import model.User;

public class PrivPassConfirmController {
    
    private User userOwner;

    private boolean result;
    
    private Stage stage;
    
    @FXML
    private Button btnCancel;

    @FXML
    private Button btnClose;
    
    @FXML
    private Button btnConfirm;

    @FXML
    private TextField txtUserPass;

    @FXML
    void btnCancelPressed() {
        result = false;
        closeWin();
    }

    @FXML
    void btnClosePressed(ActionEvent event) {
        result = false;
        closeWin();
    }
    
    @FXML
    void btnConfirmPressed() {
        if (userOwner.getUserPass().equals(txtUserPass.getText())) {
            result = true;
            closeWin();
        } else {
            FloatingPopup.showTextFieldPopup(txtUserPass, "Incorrect password");
        }
    }

    public boolean getResult() {
        return result;
    }

    private void closeWin(){
        Stage stage = (Stage) this.btnConfirm.getScene().getWindow();
        stage.close();
    }
    
    public void setUserOwner(User userOwner) {
        this.userOwner = userOwner;
    }

}
