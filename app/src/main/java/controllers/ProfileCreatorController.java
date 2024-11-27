package controllers;

import java.io.File;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Account;
import model.AccountDAO;
import model.FieldValidator;
import model.FloatingPopup;
import model.Tools;
import model.User;
import model.ViewManager;
import model.ViewStatus;

public class ProfileCreatorController implements Initializable{
    
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
        if (MainAppController.viewLoader.loadAccountCreator()) loadUserAccountsCards();
    }
    
    @FXML
    void btnCancelPressed() {
        backToProfileSelect();
    }
    
    @FXML
    void btnRemovePressed() {
        if (MainAppController.viewLoader.loadDeletionConfirm("user", userOwner.getEmail())) {
            //CRUD DELETE USER
            backToProfileSelect();
        }
    }
    
    @FXML
    void btnUpdatePressed() {
        //CRUD UPDATE USER
        System.out.println(
                "User updating:\n" + 
                "Name: " + userOwner.getUserName() + " to " + txtNick.getText() + "\n" +
                "Email: " + userOwner.getEmail() + " to " + txtEmail.getText() + "\n" +
                "Password: " + userOwner.getUserPass() + " to " + txtPass.getText() + "\n" +
                "Privacity: " + userOwner.isPrivate()+ " to " + privStatus + "\n" +
                "Image: " + imgUserImage.getImage() + " to " + imgUserAux
        );
        
        if (validateFields()) {
            
        }
    }

    @FXML
    void btnSavePressed() {
        //CRUD INSERT USER
        backToProfileSelect();
    }
    
    public void initComponents(boolean updating){
        if (updating) {
            accounts = new AccountDAO().loadAccountsFromDB(userOwner);
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
        String validMessage = "";
        if (!nickNotEmpty) validMessage += "-User name must not be empty\n";
        if (!nickMatchRegex) validMessage += "-User name can only contain aplhanumeric characters or simple symbols (-_@.)\n";
        if (!nickInLength) validMessage += "-User name must not be more than 30 characters\n";
        if (!nickNotEmpty || !nickMatchRegex || !nickInLength) FloatingPopup.showPopup(txtNick, validMessage);
        
        return nickNotEmpty && nickMatchRegex && nickInLength;
    }
    
    private boolean validateEmail() {
        boolean emailNotEmpty = FieldValidator.emptinessValidation(txtEmail.getText());
        boolean emailMatchRegex = true;
        boolean emailNotRepited = true;
        boolean emailInLength = true;
        if (emailNotEmpty) {
            emailMatchRegex = FieldValidator.emailValidation(txtEmail.getText());
            emailNotRepited = !FieldValidator.repeatedUserValidation(txtEmail.getText());
            emailInLength = FieldValidator.lengthValidation(50, txtEmail.getText());
        }
        String validMessage = "";
        if (!emailNotEmpty) validMessage += "-Email must not be empty\n";
        if (!emailMatchRegex) validMessage += "-Email must have an email structure\n";
        if (!emailInLength) validMessage += "-Email must not be more than 30 characters\n";
        if (!emailNotRepited) validMessage += "-This email already exists\n";
        if (!emailNotEmpty || !emailMatchRegex || !emailInLength || !emailNotRepited) FloatingPopup.showPopup(txtEmail, validMessage);
        
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
            String validMessage = "";
            if (!passNotEmpty) validMessage += "-Password must not be empty\n";
            if (!passMatchRegex) validMessage += "-Password can only contain aplhanumeric characters or simple symbols (-_@.)\n";
            if (!passInLenght) validMessage += "-Password must not be more than 30 characters\n";
            if (!passNotEmpty || !passMatchRegex || !passInLenght) FloatingPopup.showPopup(txtPass, validMessage);
            
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
