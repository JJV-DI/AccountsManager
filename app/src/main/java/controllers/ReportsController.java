package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;

public class ReportsController implements Initializable{

    private String entitySelected;
    
    @FXML
    private Button btnSNEntity;

    @FXML
    private Button btnUserEntity;

    @FXML
    private CheckBox chkAnalytics;

    @FXML
    private CheckBox chkDetailed;

    @FXML
    private CheckBox chkEmbedded;

    @FXML
    private CheckBox chkShowAll;

    @FXML
    private Label txtSeachSocialNetwork;

    @FXML
    private TextField txtSpecificRecord;

    @FXML
    private WebView wvResult;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        entitySelected = "User";
    }
    
    @FXML
    void btnCreateReportPressed() {

    }

    @FXML
    void btnSNEntityPressed() {

    }

    @FXML
    void btnUserEntityPressed() {

    }

    private void selectEntity(String entity) {
        entitySelected = entity;
        switch (entitySelected) {
            case "User" -> {
                markButton(btnUserEntity);
            }
            case "SN" -> {
                markButton(btnSNEntity);
            }
        }
        resetButtons();
    }
    
    private void markButton(Button btn) {
        
    }
    
    private void resetButtons() {
        
    }
    
}
