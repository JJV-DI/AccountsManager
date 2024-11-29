package controllers;

import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.Account;
import model.DAO.AccountDAO;
import model.SocialNetwork;
import model.DAO.SocialNetworkDAO;
import model.User;
import model.Util.FieldValidator;

public class AccountCreatorController implements Initializable{
    
    private ObservableList<SocialNetwork> socialNetworksUnpurged;
    private ObservableList<SocialNetwork> socialNetworksPurged;
    
    private List<Account> accountsOfUser;

    private boolean result;
    
    private User userOwner;
    
    private Account accountOwner;
    
    private SocialNetwork currentSN;

    @FXML
    private VBox vboxScrollBody;

    @FXML
    private VBox vboxSocialNetworkTab;
    
    @FXML
    private Button btnCancel;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnSave;
    
    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnSocialNetwork;

    @FXML
    private Button btnTooglePassVis;

    @FXML
    private Label lblSocialNetworkSelected;
    
    @FXML
    private Label lblNewUpdate;

    @FXML
    private TextField txtSearchSN;
    
    @FXML
    private TextField txtAccountName;

    @FXML
    private TextField txtAccountPass;
    
    @FXML
    private ImageView imgSocialNetworkSelected;
    
    @FXML
    private HBox hboxBody;

    
    public boolean getResult() {
        return result;
    }
    
    @FXML
    void btnCancelPressed() {
        result = false;
        closeWin();
    }

    @FXML
    void btnClosePressed() {
        result = false;
        closeWin();
    }

    @FXML
    void btnSavePressed() {
        if (validateFields()) {
            new AccountDAO().insertAccount(new Account(userOwner.getEmail(), txtAccountName.getText(), txtAccountPass.getText(), currentSN.getIdRed(), currentSN.getNombreRed(), currentSN.getIconoRed()), userOwner);
            result = true;
            closeWin();
        }
    }
    
     @FXML
    void btnUpdatePressed() {
        if (validateFields()) {
            new AccountDAO().updateAccount(new Account(userOwner.getEmail(), txtAccountName.getText(), txtAccountPass.getText(), currentSN.getIdRed(), currentSN.getNombreRed(), currentSN.getIconoRed()), accountOwner, userOwner);
            result = true;
            closeWin();
        }
    }

    @FXML
    void btnSocialNetworkPressed() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        if (vboxSocialNetworkTab.isManaged()) {
            stage.setWidth(702);
        } else {
            stage.setWidth(952);
        }
        vboxSocialNetworkTab.setManaged(!vboxSocialNetworkTab.isManaged());
        vboxSocialNetworkTab.setVisible(!vboxSocialNetworkTab.isVisible());      
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vboxSocialNetworkTab.setManaged(false);
        vboxSocialNetworkTab.setVisible(false);
        btnUpdate.setManaged(false);
        Circle clip = new Circle(50,50,50);
        imgSocialNetworkSelected.setClip(clip);
        socialNetworksUnpurged = new SocialNetworkDAO().loadSocialNetworks();
        socialNetworksPurged = FXCollections.observableArrayList();
    }
    
    private boolean validateFields() {
        boolean validName = validateName();
        boolean validPass = validatePassword();
        boolean validSN = validateSNSelected();
        return validName && validPass && validSN;
    }
    
    private boolean validateName() {
        boolean nameNotEmpty = FieldValidator.emptinessValidation(txtAccountName.getText());
        boolean nameMatchRegex = true;
        boolean nameInLenght = true;
        if (nameNotEmpty) {
            nameMatchRegex = FieldValidator.commonNameValidation(txtAccountName.getText());
            nameInLenght = FieldValidator.lengthValidation(30, txtAccountName.getText());
        }
        StringBuilder validMessage = new StringBuilder("");
        if (!nameNotEmpty) validMessage.append("-Account name must not be empty\n");
        if (!nameMatchRegex) validMessage.append("-Account name can only contain aplhanumeric characters or simple symbols (-_@.)\n");
        if (!nameInLenght) validMessage.append("-Account name must not be more than 30 characters\n");
        
        FieldValidator.toggleTextFieldInError(!nameNotEmpty || !nameMatchRegex || !nameInLenght, txtAccountName, validMessage.toString());
        
        return nameNotEmpty && nameMatchRegex && nameInLenght;
    }
    
    private boolean validatePassword() {
        boolean passNotEmpty = FieldValidator.emptinessValidation(txtAccountPass.getText());
        boolean passMatchRegex = true;
        boolean passInLenght = true;
        if (passNotEmpty) {
            passMatchRegex = FieldValidator.passwordValidation(txtAccountPass.getText());
            passInLenght = FieldValidator.lengthValidation(30, txtAccountPass.getText());
        }
        StringBuilder validMessage = new StringBuilder("");
        if (!passNotEmpty) validMessage.append("-Password must not be empty\n");
        if (!passMatchRegex) validMessage.append("-Password can only contain aplhanumeric characters or simple symbols (-_@.)\n");
        if (!passInLenght) validMessage.append("-Password must not be more than 30 characters\n");

        FieldValidator.toggleTextFieldInError(!passNotEmpty || !passMatchRegex || !passInLenght, txtAccountPass, validMessage.toString());
        FieldValidator.toggleHBoxInError(!passNotEmpty || !passMatchRegex || !passInLenght,(HBox) txtAccountPass.getParent());

        return passNotEmpty && passMatchRegex && passInLenght;
    }
    
    private boolean validateSNSelected() {
        boolean validSN = currentSN != null;
        FieldValidator.toggleLabelInError(!validSN, lblSocialNetworkSelected);
        return validSN;
    }
    
    private void closeWin() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
    
    private void purgeRepeatedSocialNetworks() {
        socialNetworksPurged.addAll(socialNetworksUnpurged);
        for (SocialNetwork socialNetwork : socialNetworksUnpurged) {
            for (Account accountOfUser : accountsOfUser) {
                if (socialNetwork.getNombreRed().equals(accountOfUser.getNombreRed())) {
                    if (currentSN != null && !socialNetwork.getNombreRed().equals(currentSN.getNombreRed())) {
                        socialNetworksPurged.remove(socialNetwork);
                    } else {
                        socialNetworksPurged.remove(socialNetwork);
                    }
                }
            }
        }
    }

    private void showSocialNetworks(){
        reorderSNList();
        vboxScrollBody.getChildren().clear();
        for(SocialNetwork socialNetwork : socialNetworksPurged) {
            if (currentSN != null) {
                if (!currentSN.getNombreRed().equals(socialNetwork.getNombreRed())) {
                    createSNCard(socialNetwork);
                }
            } else {
                createSNCard(socialNetwork);
            }
        }
    }
    
    private void createSNCard(SocialNetwork socialNetwork){
        AC_Tab_BtnController aC_Tab_BtnController = MainAppController.viewLoader.loadAccountCreatorSocialNetworkCard(vboxScrollBody, socialNetwork);
        aC_Tab_BtnController.btnSocialNetwork.setOnAction(event -> {
            currentSN = aC_Tab_BtnController.getSocialNetworkOwner();
            loadSNComponents();
            showSocialNetworks();
        });
    }
    
    public void setAccountOwner(Account accountOwner) {
        if (accountOwner != null) {
            this.accountOwner = accountOwner;
            this.lblNewUpdate.setText("Update");
            this.btnSave.setManaged(false);
            this.btnUpdate.setManaged(true);
            loadAccountComponents();
            loadInitialSocialNetwork();
            loadSNComponents();
            showSocialNetworks();
        }
    }

    public void setUserOwner(User userOwner) {
        this.userOwner = userOwner;
        accountsOfUser = new AccountDAO().loadAccountsFromDB(userOwner);
        purgeRepeatedSocialNetworks();
        showSocialNetworks();
    }
    
    private void loadAccountComponents() {
        txtAccountName.setText(accountOwner.getNombreCuenta());
        txtAccountPass.setText(accountOwner.getPassCuenta());
    }
    
    private void loadSNComponents() {
        lblSocialNetworkSelected.setText(currentSN.getNombreRed());
        imgSocialNetworkSelected.setImage(currentSN.getIconoRed());
    }
    
    private void loadInitialSocialNetwork() {
        for(SocialNetwork socialNetwork : socialNetworksUnpurged) {
            if (socialNetwork.getNombreRed().equals(accountOwner.getNombreRed())) {
                currentSN = socialNetwork;
                socialNetworksPurged.add(currentSN);    
            } 
        }
    }
    
    private void reorderSNList() {
        FXCollections.sort(socialNetworksPurged, Comparator.comparing(SocialNetwork::getNombreRed));
    }
}
