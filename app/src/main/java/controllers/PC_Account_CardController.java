package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import model.Account;

public class PC_Account_CardController implements Initializable{

    private Account accountOwner;
    
    @FXML
    private Button btnRemoveAccount;

    @FXML
    private HBox hbAccountContainer;

    @FXML
    private Label lblAcc;

    @FXML
    void btnRemoveAccountPressed() {
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void setAccountOwner(Account accountOwner){
        this.accountOwner = accountOwner;
    }
    
    public void setName() {
        lblAcc.setText(accountOwner.getNombreCuenta());
    }

}