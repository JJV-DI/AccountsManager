package controllers;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.EventType;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Account;
import model.DAO.AccountDAO;
import model.Util.FieldValidator;
import model.Util.Tools;
import model.User;
import model.DAO.UserDAO;
import model.Util.ViewManager;
import model.Util.ViewStatus;

public class ProfileCreatorController implements Initializable {
    
    private ViewManager viewManager;
    
    private VBox vboxBody;
    
    private ObservableList<Account> accounts = FXCollections.observableArrayList();
    
    private User userOwner;
    
    private boolean privStatus = false;
    
    private Image imgUserAux;

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
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select an image file");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpeg", "*.jpg"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            imgUserAux = Tools.resizeImageToSquare(new Image(selectedFile.toURI().toString()));
            imgUserImage.setImage(imgUserAux);
        }
    }
    
    @FXML
    void btnRemoveImagePressed() {
        imgUserAux = null;
        imgUserImage.setImage(Tools.loadImgFromX64(null, "user"));
    }

    @FXML
    void btnPrivStatusPressed() {
        if (privStatus) setPublicStatus();
        else setPrivateStatus();
    }

    @FXML
    void btnTogglePassPressed() {
        
    }
    
    @FXML
    void btnAddAccountPressed() {
        if (MainAppController.viewLoader.loadAccountCreator(userOwner)) loadUserAccountsCards();
    }
    
    @FXML
    void btnCancelPressed() {
        backToProfileSelect();
    }
    
    @FXML
    void btnRemovePressed() {
        if (MainAppController.viewLoader.loadDeletionConfirm("user", userOwner.getEmail())) {
            new UserDAO().deleteUser(userOwner);
            backToProfileSelect();
        }
    }
    
    @FXML
    void btnUpdatePressed() {  
        if (validateFields()) {
            String privVal;
            if (privStatus) {
                privVal = "Y";
            } else {
                privVal = "N";
            }
            new UserDAO().updateUser(new User(txtEmail.getText(),txtNick.getText(),txtPass.getText(),privVal,imgUserAux), userOwner);
            backToProfileSelect();
        }
    }

    @FXML
    void btnSavePressed() {
        if (validateFields()) {
            String privVal;
            if (privStatus) {
                privVal = "Y";
            } else {
                privVal = "N";
            }
            new UserDAO().insertUser(new User(txtEmail.getText(),txtNick.getText(),txtPass.getText(),privVal,imgUserAux));
            backToProfileSelect();
        }
    }
    
    public void initComponents(boolean updating){
        if (updating) {
            loadAccountsFromDB();
            txtNick.setText(userOwner.getUserName());
            txtEmail.setText(userOwner.getEmail());
            imgUserImage.setImage(userOwner.getImgUser());
            imgUserAux = userOwner.getImgUser();
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
    
    public void loadAccountsFromDB() {
        accounts = new AccountDAO().loadAccountsFromDB(userOwner);
    }
    
    void setPrivateStatus(){
        hboxPassWarn.setVisible(false);
        privStatus = true;
        lblPrivStatus.setText("PRIVATE");
        btnPrivStatus.setStyle("-fx-background-color: #EFB810;");
        imgKey.setImage(new Image("/vistas/media/cur_icons/key.png"));
        txtPass.setDisable(false);
        btnTogglePass.setDisable(false);
        imgPassVisEye.setImage(new Image("/vistas/media/cur_icons/eyeCrossed.png"));
    }
    
    void setPublicStatus(){
        hboxPassWarn.setVisible(false);
        privStatus = false;
        lblPrivStatus.setText("PUBLIC");
        btnPrivStatus.setStyle("-fx-background-color: #BEBEBE;");
        imgKey.setImage(new Image("/vistas/media/cur_icons/keyCrossed.png"));
        txtPass.setDisable(true);
        txtPass.clear();
        btnTogglePass.setDisable(true);
        imgPassVisEye.setImage(new Image("/vistas/media/cur_icons/eyeCrossed.png"));
    }
    
    void loadUserAccountsCards(){
        loadAccountsFromDB();
        flowPaneAccounts.getChildren().clear();
        for (Account account : accounts) {
            MainAppController.viewLoader.loadProfileCreatorUserAccountsCards(flowPaneAccounts, account, userOwner, this);
        }
        lblAccountsCount.setText(String.valueOf(accounts.size()));
    }

    private void backToProfileSelect() {
        vboxBody.getChildren().clear();
        viewManager.setStatus(ViewStatus.PROFILES);
        MainAppController.viewLoader.backToProfileSelect(vboxBody, viewManager);
    }
    
    
    private boolean validateFields() {
        boolean nameValid = validateUserName();
        boolean emailValid = validateEmail();
        boolean passValid = validatePassword();
        return nameValid && emailValid && passValid;
    }
    
    private boolean validateUserName() {
        boolean nickNotEmpty = FieldValidator.emptinessValidation(txtNick.getText());        
        boolean nickMatchRegex = true;
        boolean nickInLength = true;
        if (nickNotEmpty) {
            nickMatchRegex = FieldValidator.commonNameValidation(txtNick.getText());
            nickInLength = FieldValidator.lengthValidation(30, txtNick.getText());
        } 
        StringBuilder validMessage = new StringBuilder("");
        if (!nickNotEmpty) validMessage.append("-User name must not be empty\n");
        if (!nickMatchRegex) validMessage.append("-User name can only contain aplhanumeric characters or simple symbols (-_@.)\n");
        if (!nickInLength) validMessage.append("-User name must not be more than 30 characters\n");
        
        FieldValidator.toggleTextFieldInError(!nickNotEmpty || !nickMatchRegex || !nickInLength, txtNick, validMessage.toString());
        
        return nickNotEmpty && nickMatchRegex && nickInLength;
    }
    
    private boolean validateEmail() {
        boolean emailNotEmpty = FieldValidator.emptinessValidation(txtEmail.getText());
        boolean emailMatchRegex = true;
        boolean emailNotRepited = true;
        boolean emailInLength = true;
        if (emailNotEmpty) {
            emailMatchRegex = FieldValidator.emailValidation(txtEmail.getText());
            if (userOwner == null) {
                emailNotRepited = !FieldValidator.repeatedUserValidation(txtEmail.getText());
            } else {
                if (!userOwner.getEmail().equals(txtEmail.getText())) {
                    emailNotRepited = !FieldValidator.repeatedUserValidation(txtEmail.getText());
                }
            }
            emailInLength = FieldValidator.lengthValidation(50, txtEmail.getText());
        }
        StringBuilder validMessage = new StringBuilder("");
        if (!emailNotEmpty) validMessage.append("-Email must not be empty\n");
        if (!emailMatchRegex) validMessage.append("-Email must have an email structure\n");
        if (!emailInLength) validMessage.append("-Email must not be more than 50 characters\n");
        if (!emailNotRepited) validMessage.append("-This email already exists\n");
        
        FieldValidator.toggleTextFieldInError(!emailNotEmpty || !emailMatchRegex || !emailInLength || !emailNotRepited, txtEmail, validMessage.toString());
        
        return emailNotEmpty && emailMatchRegex && emailNotRepited && emailInLength;
    }
    
    private boolean validatePassword() {
        if (privStatus) {
            boolean passNotEmpty = FieldValidator.emptinessValidation(txtPass.getText());
            boolean passMatchRegex = true;
            boolean passInLenght = true;
            if (passNotEmpty) {
                passMatchRegex = FieldValidator.passwordValidation(txtPass.getText());
                passInLenght = FieldValidator.lengthValidation(30, txtPass.getText());
            }
            StringBuilder validMessage = new StringBuilder("");
            if (!passNotEmpty) validMessage.append("-Password must not be empty\n");
            if (!passMatchRegex) validMessage.append("-Password can only contain aplhanumeric characters or simple symbols (-_@.)\n");
            if (!passInLenght) validMessage.append("-Password must not be more than 30 characters\n");
            
            FieldValidator.toggleTextFieldInError(!passNotEmpty || !passMatchRegex || !passInLenght, txtPass, validMessage.toString());
            
            return passNotEmpty && passMatchRegex && passInLenght;
        } else {
            return true;
        }
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
