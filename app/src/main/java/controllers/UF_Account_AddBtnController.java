package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class UF_Account_AddBtnController {

    @FXML
    private Button btnAddAccount;

    @FXML
    void btnAddAccountPressed() {
        MainAppController.viewLoader.loadAccountCreator();
    }

}
