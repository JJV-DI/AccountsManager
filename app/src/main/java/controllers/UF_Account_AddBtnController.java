package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import model.User;

public class UF_Account_AddBtnController {

    private User userOwner;
    
    @FXML
    private Button btnAddAccount;

    @FXML
    void btnAddAccountPressed() {
        MainAppController.viewLoader.loadAccountCreator(userOwner);
    }

    public void setUserOwner(User userOwner) {
        this.userOwner = userOwner;
    }

}
