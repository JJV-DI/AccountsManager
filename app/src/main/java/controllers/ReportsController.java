package controllers;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Util.ConfigProvider;
import model.Util.FieldValidator;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.mariadb.jdbc.Connection;

public class ReportsController implements Initializable{

    private String entitySelected;
    
    private List<Button> entityButtons;
    
    private Double zoomValue;
    
    private Double defaultZoom = 0.9;
    
    private boolean reportLoaded = false;
    
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
    private Label txtResult;
    
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
        chkEmbedded.setSelected(true);
        wvResult.getEngine().setUserStyleSheetLocation(getWebStyle());
        wvResult.getEngine().load(new File(getDefaultHTML()).toURI().toString());
        zoomValue = defaultZoom;
        updateZoom();
    }
    
    @FXML
    void btnResetZoomPressed(ActionEvent event) {
        if (reportLoaded && zoomValue != defaultZoom) {
            zoomValue = defaultZoom;
            updateZoom();
        }
    }
    
    @FXML
    void btnZoomInPressed(ActionEvent event) {
        if (reportLoaded && zoomValue < 1.5) {
            zoomValue += 0.1;
            updateZoom();
        }
    }

    @FXML
    void btnZoomOutPressed(ActionEvent event) {
        if (reportLoaded && zoomValue > 0.7) {
            zoomValue -= 0.1;
            updateZoom();
        }
    }
    
    private void updateZoom() {
        wvResult.setZoom(zoomValue);
    }
    
    @FXML
    void chkShowAllPressed() {
        resetField();
        txtSpecificRecord.setDisable(chkShowAll.isSelected());
    }
    
    @FXML
    void chkDetailedPressed(ActionEvent event) {
        chkAnalytics.setSelected(false);
        chkAnalytics.setDisable(!chkDetailed.isSelected());
        chkShowAll.setSelected(false);
        chkShowAll.setDisable(!chkDetailed.isSelected()&&!chkShowAll.isSelected());
        txtSpecificRecord.setDisable(!chkDetailed.isSelected());
        resetField();
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
    
    private void resetField() {
        txtSpecificRecord.setText("");
        txtSpecificRecord.getStyleClass().removeAll("fieldError-color");
        txtSpecificRecord.getStyleClass().add("secondary-color");
    }
    
    @FXML
    void btnCreateReportPressed() {
        if (validateFields()) {
            deleteLastReportFiles(new File("currentHTMLReport.html_files"));
            createReport();
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
    
    private void createReport() {
        try (Connection connection = new ConfigProvider().getConnection()) {
            JasperReport jpReport = (JasperReport) JRLoader.loadObject(getClass().getResourceAsStream(getReportPath()));
            if (connection != null) {
                JasperPrint jpPrint = JasperFillManager.fillReport(jpReport, getParameters(), connection);
                if (jpPrint.getPages().isEmpty()) {
                    txtResult.setText("No result for that query");
                } else {
                    txtResult.setText("");
                    String reportPath = "currentHTMLReport.html";
                    JasperExportManager.exportReportToHtmlFile(jpPrint, reportPath);
                    showReport(reportPath);
                }
            }
        } catch (JRException e) {
            System.err.println("Error in " + this.getClass().toString() + " creating report file");
            System.err.println(e.getCause());
        } catch (SQLException ex) {
            System.err.println("Error in " + this.getClass().toString() + " creating connection with DB");
            System.err.println(ex.getCause());
        }
        
    }
    
    private String getReportPath() {
        switch (entitySelected) {
            case "User" -> {
                if (chkDetailed.isSelected()) {
                    return "/reports/detailed_user_report.jasper";
                } else {
                    return "/reports/simple_user_report.jasper";
                }
            }
            case "SN" -> {
                if (chkDetailed.isSelected()) {
                    return "/reports/detailed_socialnetwork_report.jasper";
                } else {
                    return "/reports/simple_socialnetwork_report.jasper";
                }
            }
            default -> {
                return null;
            }
        }
    }
    
    private Map<String, Object> getParameters() {
        Map<String, Object> parameters = new HashMap<>();
        if (chkDetailed.isSelected()) {
            parameters.put("p_showAll", chkShowAll.isSelected());
            parameters.put("Analytics", chkAnalytics.isSelected());
            if (chkShowAll.isSelected()) {
                parameters.put("p_filter", null);
            } else {
                parameters.put("p_filter", txtSpecificRecord.getText());
            }
        }
        return parameters;
    }
    
    private void showReport(String reportPath) {
        if (chkEmbedded.isSelected()) {
            reportLoaded = true;
            wvResult.getEngine().load(new File(reportPath).toURI().toString());
        } else {
            MainAppController.viewLoader.loadReportAuxDisplay(reportPath, getWebStyle());
        }
    }
    
    private String getWebStyle() {
        switch (new ConfigProvider().loadTheme()) {
            case "Dark theme" -> {
                return getClass().getResource("/vistas/styles/web/webdarktheme.css").toString();
            }
            case "Light theme" -> {
                return getClass().getResource("/vistas/styles/web/weblighttheme.css").toString();
            }
            default -> {
                return null;
            }
        }
    }
    
    private String getDefaultHTML() {
        switch (new ConfigProvider().loadTheme()) {
            case "Dark theme" -> {
                return getClass().getResource("/vistas/styles/web/auxhtml/darkhtml.html").toString();
            }
            case "Light theme" -> {
                return getClass().getResource("/vistas/styles/web/auxhtml/lighthtml.html").toString();
            }
            default -> {
                return null;
            }
        }
    }
    
    private boolean deleteLastReportFiles(File directory) {
    if (directory.exists() && directory.isDirectory()) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) deleteLastReportFiles(file);
                else file.delete();
            }
        }
        return directory.delete();
    }
    return false;
}
}
