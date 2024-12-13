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
import model.DAO.AccountDAO;
import model.User;
import model.Util.PassLblToggler;
import model.Util.TogglerBuilder;

public class UF_Account_CardController implements Initializable{
    
    private PassLblToggler passLblToggler;
    
    private UserInfoController userInfoController;

    private User userOwner;
    
    private Account accountOwner;
    
    @FXML
    private Button btnTogglePass;
    
    @FXML
    private Button btnRemoveAccount;
    
    @FXML
    private Button btnUpdateAccount;

    @FXML
    private ImageView imgTogglePass;
    
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
        if (MainAppController.viewLoader.loadDeletionConfirm("account", accountOwner.getNombreCuenta() + " (" + accountOwner.getNombreRed() + ")")) {
            new AccountDAO().deleteAccount(accountOwner, userOwner);
            userInfoController.showAccountCards();
        }
    }
    
    @FXML
    void btnUpdateAccountPressed() {
        if (MainAppController.viewLoader.loadAccountCreator(accountOwner, userOwner)) userInfoController.showAccountCards();
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
        this.passLblToggler = TogglerBuilder.buildLblToggler(btnTogglePass, lblPass, true, imgTogglePass);
    }

    public void setUserOwner(User userOwner) {
        this.userOwner = userOwner;
    }
    
    public void setAccountOwner(Account accountOwner) {
        this.accountOwner = accountOwner;
        initData();
    }

    public void setUserInfoController(UserInfoController userInfoController) {
        this.userInfoController = userInfoController;
    }

}
