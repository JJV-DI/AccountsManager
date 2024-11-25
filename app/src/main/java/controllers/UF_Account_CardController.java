package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import model.Account;

public class UF_Account_CardController implements Initializable{

    private Account accountOwner;
    
    @FXML
    private Button btnRemoveAccount;
    
    @FXML
    private Button btnUpdateAccount;

    @FXML
    private ImageView imgSN;

    @FXML
    private Label lblNick;

    @FXML
    private Label lblPass;

    @FXML
    private Label lblSN;

    @FXML
    void btnRemoveAccountPressed() {
        MainAppController.viewLoader.loadDeletionConfirm("account", accountOwner.getNombreCuenta() + " (" + accountOwner.getNombreRed() + ")");
    }
    
    @FXML
    void btnUpdateAccountPressed() {
        MainAppController.viewLoader.loadAccountCreator(accountOwner);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Circle clip = new Circle(25, 25, 25);
        imgSN.setClip(clip);
    }
    
    private void initData(){
        lblNick.setText(accountOwner.getNombreCuenta());
        lblPass.setText(accountOwner.getPassCuenta());
        lblSN.setText(accountOwner.getNombreRed());
        imgSN.setImage(accountOwner.getIconoRed());
    }
    
    public void setAccountOwner(Account accountOwner) {
        this.accountOwner = accountOwner;
        initData();
    }

}
