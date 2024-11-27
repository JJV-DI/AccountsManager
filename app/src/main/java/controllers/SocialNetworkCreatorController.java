package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.SocialNetwork;

public class SocialNetworkCreatorController implements Initializable{

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
        result = true;
        closeWin();
    }
    
    @FXML
    void btnUpdatePressed() {
        result = true;
        closeWin();
    }

    @FXML
    void btnEditImgPressed() {

    }

    @FXML
    void btnRemoveImgPressed() {

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
        imgSocialNetwork.setImage(socialNetworkOwner.getIconoRed());
    }

}
