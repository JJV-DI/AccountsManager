package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ConfirmDelController {

    private boolean result;
    
    @FXML
    private Button btnCancel;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnDelete;

    @FXML
    private Label lblElement;

    @FXML
    private Label lblType;

    @FXML
    void btnCancelPressed(ActionEvent event) {
        result = false;
        closeWindow();
    }

    @FXML
    void btnClosePressed(ActionEvent event) {
        result = false;
        closeWindow();
    }

    @FXML
    void btnDeletePressed(ActionEvent event) {
        result = true;
        closeWindow();
    }
    
    private void closeWindow() {
        Stage stage = (Stage) this.lblType.getScene().getWindow();
        stage.close();
    }
    
    public void setType(String type) {
        lblType.setText(type);
    }
    
    public void setElement(String element) {
        lblElement.setText(element);
    }

    public boolean getResult() {
        return result;
    }
    
}
