package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import model.User;

public class UF_Account_AddBtnController {

    private User userOwner;
    
    private UserInfoController ufController;
    
    @FXML
    private Button btnAddAccount;

    @FXML
    void btnAddAccountPressed() {
        if (MainAppController.viewLoader.loadAccountCreator(userOwner)) {
            ufController.showAccountCards();
        }
    }

    public void setUserOwner(User userOwner) {
        this.userOwner = userOwner;
    }

    public void setUfController(UserInfoController ufController) {
        this.ufController = ufController;
    }

}
