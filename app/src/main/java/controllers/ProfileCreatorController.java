package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import model.Account;
import model.AccountDAO;
import model.User;
import model.ViewManager;
import model.ViewStatus;

public class ProfileCreatorController implements Initializable{
    
    private ViewManager viewManager;
    
    private VBox vboxBody;
    
    private ObservableList<Account> accounts = FXCollections.observableArrayList();
    
    private User userOwner;
    
    private boolean privStatus = false;

    @FXML
    private Button btnAddAccount;

    @FXML
    public Button btnCancel;

    @FXML
    private Button btnEditImage;

    @FXML
    private Button btnPrivStatus;

    @FXML
    public Button btnRemove;

    @FXML
    private Button btnRemoveImage;

    @FXML
    public Button btnSave;

    @FXML
    private Button btnTogglePass;

    @FXML
    public Button btnUpdate;

    @FXML
    private FlowPane flowPaneAccounts;

    @FXML
    private HBox hboxPassWarn;

    @FXML
    private ImageView imgKey;

    @FXML
    private ImageView imgPassVisEye;

    @FXML
    private ImageView imgUserImage;

    @FXML
    private Label lblPrivStatus;
    
    @FXML
    private Label lblAccountsCount;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNick;

    @FXML
    private TextField txtPass;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Circle clip = new Circle(40, 40, 40);
        imgUserImage.setClip(clip);
    }

    @FXML
    void btnEditImagePressed() {

    }

    @FXML
    void btnPrivStatusPressed() {

    }

    @FXML
    void btnRemoveImagePressed() {

    }

    @FXML
    void btnTogglePassPressed() {
        
    }
    
    @FXML
    void btnAddAccountPressed() {
        MainAppController.viewLoader.loadAccountCreator();
    }
    
    @FXML
    void btnUpdatePressed() {
        backToProfileSelect();
    }
    
    @FXML
    void btnCancelPressed() {
        backToProfileSelect();
    }
    
    @FXML
    void btnRemovePressed() {
        MainAppController.viewLoader.loadDeletionConfirm("user", userOwner.getEmail());
    }

    @FXML
    void btnSavePressed() {
        backToProfileSelect();
    }
    
    public void initComponents(boolean updating){
        if (updating) {
            accounts = new AccountDAO().loadAccountsFromDB(userOwner);
            txtNick.setText(userOwner.getUserName());
            txtEmail.setText(userOwner.getEmail());
            imgUserImage.setImage(userOwner.getImgUser());
            privStatus = userOwner.isPrivate();
            if (privStatus) {
                setPrivateStatus();
                txtPass.setText(userOwner.getUserPass());
            } else {
                setPublicStatus();
            }
            loadUserAccountsCards();
            btnSave.setVisible(false);
            btnSave.setManaged(false);
        } else {
            setPublicStatus();
            btnAddAccount.setDisable(true);
            btnRemove.setManaged(false);
            btnUpdate.setManaged(false);
        }
    }
    
    void setPrivateStatus(){
        hboxPassWarn.setVisible(false);
        privStatus = true;
        lblPrivStatus.setText("PRIVATE");
        btnPrivStatus.setStyle("-fx-background-color: #EFB810;");
        imgKey.setImage(new Image("/vistas/media/icon/key_darkMode.png"));
        txtPass.setDisable(false);
        btnTogglePass.setDisable(false);
        imgPassVisEye.setImage(new Image("/vistas/media/icon/eyeCrossed_darkMode.png"));
    }
    
    void setPublicStatus(){
        hboxPassWarn.setVisible(false);
        privStatus = false;
        lblPrivStatus.setText("PUBLIC");
        btnPrivStatus.setStyle("-fx-background-color: #BEBEBE;");
        imgKey.setImage(new Image("/vistas/media/icon/keyCrossed_darkMode.png"));
        txtPass.setDisable(true);
        txtPass.clear();
        btnTogglePass.setDisable(true);
        imgPassVisEye.setImage(new Image("/vistas/media/icon/eyeCrossed_darkMode.png"));
    }
    
    void loadUserAccountsCards(){
        flowPaneAccounts.getChildren().clear();
        for (Account account : accounts) {
            MainAppController.viewLoader.loadProfileCreatorUserAccountsCards(flowPaneAccounts, account);
        }
        lblAccountsCount.setText(String.valueOf(accounts.size()));
    }

    private void backToProfileSelect() {
        vboxBody.getChildren().clear();
        viewManager.setStatus(ViewStatus.PROFILES);
        MainAppController.viewLoader.backToProfileSelect(vboxBody, viewManager);
    }
    
    public void setVboxBody(VBox vboxBody) {
        this.vboxBody = vboxBody;
    }
    
    public void setUserOwner(User userOwner){
        this.userOwner = userOwner;
    }

    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }
}
