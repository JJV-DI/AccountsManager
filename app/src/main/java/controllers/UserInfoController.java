package controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import model.Account;
import model.DAO.AccountDAO;
import model.DAO.SocialNetworkDAO;
import model.User;
import model.Util.FieldValidator;
import model.Util.ViewManager;

public class UserInfoController implements Initializable{
    
    private RotateTransition rotateTransition;
    
    private List<Account> accounts = FXCollections.observableArrayList();
    
    private VBox vboxBody;
    
    private ViewManager viewManager;
    
    private User userOwner;
    
    private boolean searchByDefault = true;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnToggleFilter;

    @FXML
    private ImageView imgFilter;

    @FXML
    private ImageView imgUserImage;

    @FXML
    private Label lblUserEmail;

    @FXML
    private Label lblUserName;

    @FXML
    private TextField txtSearch;

    @FXML
    private VBox vboxScrollBody;

    @FXML
    void btnEditPressed() {
        MainAppController.viewLoader.loadProfileCreator(vboxBody, viewManager, userOwner, true);
    }

    @FXML
    void btnToggleFilterPressed() {
        searchByDefault = !searchByDefault;
        txtSearch.clear();
        if (searchByDefault) {
            txtSearch.setPromptText("Search by name");
            initSearchByNameAction();
            rotateTransition.setByAngle(360);
            System.out.println("Giro 90");
        } else {
            txtSearch.setPromptText("Search by social");
            initSearchBySNAction();
            rotateTransition.setByAngle(-360);
            System.out.println("Giro -90");
        }
        rotateTransition.play();
    }
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Circle clip = new Circle(30, 30, 30);
        imgUserImage.setClip(clip);
        rotateTransition = new RotateTransition(Duration.seconds(0.2), imgFilter);
    }
    
    public void initComponents() {
        lblUserName.setText(userOwner.getUserName());
        lblUserEmail.setText(userOwner.getEmail());
        imgUserImage.setImage(userOwner.getImgUser());
        showAccountCards();
        initSearchByNameAction();
    }
    
    private void initSearchByNameAction() {
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event -> {
            if (validateSearch() && !txtSearch.getText().isEmpty()) {
                showAccountCards(new AccountDAO().loadAccountsByName(txtSearch.getText(), userOwner));
            } else {
                showAccountCards();
            }
        });
        txtSearch.textProperty().addListener((ObservableList, oldValue, newValue) -> {
            pause.playFromStart();
        });
    }
    
    private void initSearchBySNAction() {
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event -> {
            if (validateSearch() && !txtSearch.getText().isEmpty()) {
                showAccountCards(new AccountDAO().loadAccountsBySN(txtSearch.getText(), userOwner));
            } else {
                showAccountCards();
            }
        });
        txtSearch.textProperty().addListener((ObservableList, oldValue, newValue) -> {
            pause.playFromStart();
        });
    }
    
    private boolean validateSearch() {
        boolean searchMatchRegex = true;
        if (txtSearch.getText() != null && !txtSearch.getText().isEmpty()) {
            searchMatchRegex = FieldValidator.commonNameValidation(txtSearch.getText());
        }
        FieldValidator.toggleTextFieldPrimaryInError(!searchMatchRegex, txtSearch, "The search can only contain aplhanumeric characters or simple symbols (-_@.)\n");
        return searchMatchRegex;
    }
    
    public void showAccountCards(ObservableList<Account> accounts){
        this.accounts = accounts;
        vboxScrollBody.getChildren().clear();
        if (accounts == null || accounts.isEmpty()) {
            MainAppController.viewLoader.loadAccountNotFoundCard(vboxScrollBody);
        } else {
            for (Account account : accounts){
                MainAppController.viewLoader.loadUserInfoAccountCards(vboxScrollBody, account, userOwner, this);
            }
        }
        MainAppController.viewLoader.loadUserInfoAccountAddButton(vboxScrollBody, userOwner, this);
    }
    
    public void showAccountCards() {
        showAccountCards(new AccountDAO().loadAccountsFromDB(userOwner));
    }
    
    public void setVboxBody(VBox vboxBody) {
        this.vboxBody = vboxBody;
    }

    public void setUserOwner(User userOwner) {
        this.userOwner = userOwner;
    }

    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }
}
