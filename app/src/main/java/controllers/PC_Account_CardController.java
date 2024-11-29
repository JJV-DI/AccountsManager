package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import model.Account;
import model.DAO.AccountDAO;
import model.User;

public class PC_Account_CardController implements Initializable{

    private ProfileCreatorController pcController;
    
    private User userOwner;
    
    private Account accountOwner;
    
    @FXML
    private Button btnRemoveAccount;

    @FXML
    private HBox hbAccountContainer;

    @FXML
    private Label lblAcc;
    
    @FXML
    private ImageView imgRed;

    @FXML
    void btnEditAccountPressed() {
        if(MainAppController.viewLoader.loadAccountCreator(accountOwner, userOwner)) pcController.loadUserAccountsCards();
    }
    
    @FXML
    void btnRemoveAccountPressed() {
        if (MainAppController.viewLoader.loadDeletionConfirm("account", accountOwner.getNombreCuenta() + " (" + accountOwner.getNombreRed() + ")")){
            new AccountDAO().deleteAccount(accountOwner, userOwner);
            pcController.loadUserAccountsCards();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Circle clip = new Circle(10, 10, 10);
        imgRed.setClip(clip);
    }

    public void setUserOwner(User userOwner) {
        this.userOwner = userOwner;
    }
    
    public void setAccountOwner(Account accountOwner){
        this.accountOwner = accountOwner;
    }
    
    public void setName() {
        lblAcc.setText(accountOwner.getNombreCuenta());
    }
    
    public void setImage() {
        imgRed.setImage(accountOwner.getIconoRed());
    }

    public void setPcController(ProfileCreatorController pcController) {
        this.pcController = pcController;
    }

}