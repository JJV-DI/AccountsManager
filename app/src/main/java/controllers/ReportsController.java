package controllers;

import java.net.URL;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;
import model.Util.FieldValidator;

public class ReportsController implements Initializable{

    private String entitySelected;
    
    private List<Button> entityButtons;
    
    @FXML
    private Button btnCreateReport;

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
    private TextField txtSpecificRecord;

    @FXML
    private WebView wvResult;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        entitySelected = "User";
        entityButtons = new ArrayList<>();
        entityButtons.add(btnUserEntity);
        entityButtons.add(btnSNEntity);
    }
    
    @FXML
    void chkShowAllPressed() {
        txtSpecificRecord.setText("");
        txtSpecificRecord.setDisable(chkShowAll.isSelected());
    }
    
    @FXML
    void chkDetailedPressed(ActionEvent event) {
        chkAnalytics.setSelected(false);
        chkAnalytics.setDisable(!chkDetailed.isSelected());
        chkShowAll.setSelected(false);
        chkShowAll.setDisable(!chkDetailed.isSelected()&&!chkShowAll.isSelected());
        txtSpecificRecord.setText("");
        txtSpecificRecord.setDisable(!chkDetailed.isSelected());
    }

    @FXML
    void btnSNEntityPressed() {
        selectEntity("SN");
    }

    @FXML
    void btnUserEntityPressed() {
        selectEntity("User");
    }

    private void selectEntity(String entity) {
        entitySelected = entity;
        btnCreateReport.setDisable(false);
        switch (entitySelected) {
            case "User" -> {
                markButton(btnUserEntity);
            }
            case "SN" -> {
                markButton(btnSNEntity);
            }
        }
    }
    
    private void markButton(Button button) {
        resetButtons();
        button.getStyleClass().add("external-border");
    }
    
    private void resetButtons() {
        for(Button button : entityButtons) {
            button.getStyleClass().remove("external-border");
        }
    }
    
    @FXML
    void btnCreateReportPressed() {
        if (validateFields()) {
            System.out.println("hola");
        }
    }
    
    private boolean validateFields() {
        if (chkDetailed.isSelected()&&!chkShowAll.isSelected()) {
            boolean emptinessVal = FieldValidator.emptinessValidation(txtSpecificRecord.getText());
            FieldValidator.toggleTextFieldSecondaryInError(!emptinessVal, txtSpecificRecord, "A record must be specified.");
            return emptinessVal;
        }
        return true;
    }
    
}
