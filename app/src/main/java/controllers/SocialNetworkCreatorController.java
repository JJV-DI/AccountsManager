package controllers;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Util.FieldValidator;
import model.SocialNetwork;
import model.DAO.SocialNetworkDAO;
import model.Util.Tools;

public class SocialNetworkCreatorController implements Initializable{

    private Image imgSNAux = null;
    
    private boolean result;
    
    private SocialNetwork socialNetworkOwner;
    
    @FXML
    private Button btnCancel;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnEditImg;

    @FXML
    private Button btnRemoveImg;

    @FXML
    private Button btnSave;
    
    @FXML
    private Button btnUpdate;

    @FXML
    private ImageView imgSocialNetwork;

    @FXML
    private TextField txtSNName;
    
    @FXML
    private Label lblNewUpdate;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Circle clip = new Circle(50,50,50);
        imgSocialNetwork.setClip(clip);
        btnUpdate.setManaged(false);
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
        if (validateName()) {
            new SocialNetworkDAO().insertSocialNetwork(new SocialNetwork(-1, txtSNName.getText(), imgSNAux));
            result = true;
            closeWin();
        }
    }
    
    @FXML
    void btnUpdatePressed() {
        if (validateName()) {
            new SocialNetworkDAO().updateSocialNetwork(new SocialNetwork(socialNetworkOwner.getIdRed(), txtSNName.getText(), imgSNAux));
            result = true;
            closeWin();
        }
    }

    @FXML
    void btnEditImgPressed() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select an image file");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpeg", "*.jpg"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            imgSNAux = Tools.resizeImageToSquare(new Image(selectedFile.toURI().toString()));
            imgSocialNetwork.setImage(imgSNAux);
        }
    }

    @FXML
    void btnRemoveImgPressed() {
        imgSNAux = null;
        imgSocialNetwork.setImage(Tools.loadImgFromX64(null, "social_network"));
    }
    
    private boolean validateName() {
        boolean nameNotEmpty = FieldValidator.emptinessValidation(txtSNName.getText());
        boolean nameMatchRegex = true;
        boolean nameNotRepited = true;
        boolean nameInLenght = true;
        if (nameNotEmpty) {
            nameMatchRegex = FieldValidator.commonNameValidation(txtSNName.getText());
            nameInLenght = FieldValidator.lengthValidation(50, txtSNName.getText());
            if (socialNetworkOwner != null) {
                if (!txtSNName.getText().equals(socialNetworkOwner.getNombreRed())) {
                    nameNotRepited = !FieldValidator.repeatedSocialNetworkValidation(txtSNName.getText());
                }
            } else {
                nameNotRepited = !FieldValidator.repeatedSocialNetworkValidation(txtSNName.getText());
            }
        }
        StringBuilder validMessage = new StringBuilder("");
        if (!nameNotEmpty) validMessage.append("-Name must not be empty\n");
        if (!nameMatchRegex) validMessage.append("-Name can only contain aplhanumeric characters or simple symbols (-_@.)\n");
        if (!nameInLenght) validMessage.append("-Name must not be more than 50 characters\n");
        if (!nameNotRepited) validMessage.append("-This social network already exists\n");
        
        FieldValidator.toggleTextFieldInError(!nameNotEmpty || !nameMatchRegex || !nameInLenght || !nameNotRepited, txtSNName, validMessage.toString());
        
        return nameNotEmpty && nameMatchRegex && nameInLenght && nameNotRepited;
    }
    
    private void closeWin() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
    
    public void setSocialNetworkOwner(SocialNetwork socialNetworkOwner) {
        this.socialNetworkOwner = socialNetworkOwner;
        if (socialNetworkOwner != null) {
            lblNewUpdate.setText("Update");
            btnSave.setManaged(false);
            btnUpdate.setManaged(true);
            loadSNData();
        }
    }
    
    private void loadSNData() {
        txtSNName.setText(socialNetworkOwner.getNombreRed());
        imgSNAux = socialNetworkOwner.getIconoRed();
        imgSocialNetwork.setImage(imgSNAux);
    }
    
    public boolean getResult() {
        return this.result;
    }
}
