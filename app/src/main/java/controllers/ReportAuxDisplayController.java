package controllers;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class ReportAuxDisplayController {

    private String reportPath;
    
    private String stylePath;
    
    private Double zoomValue = 0.8;
    
    @FXML
    private Button btnClose;

    @FXML
    private WebView wvAux;

    @FXML
    void btnClosePressed(ActionEvent event) {
        closeWindow();
    }
    
    @FXML
    void btnResetZoomPressed(ActionEvent event) {
        if (zoomValue != 0.8) {
            zoomValue = 0.8;
            updateZoom();
        }
    }
    
    @FXML
    void btnZoomInPressed(ActionEvent event) {
        if (zoomValue < 1.5) {
            zoomValue += 0.1;
            updateZoom();
        }
    }

    @FXML
    void btnZoomOutPressed(ActionEvent event) {
        if (zoomValue > 0.6) {
            zoomValue -= 0.1;
            updateZoom();
        }
    }
    
    private void updateZoom() {
        wvAux.setZoom(zoomValue);
    }
    
    private void closeWindow() {
        Stage stage = (Stage) this.btnClose.getScene().getWindow();
        stage.close();
    }

    public void setReportPath(String reportPath) {
        this.reportPath = reportPath;
        loadReport();
    }

    public void setStylePath(String stylePath) {
        this.stylePath = stylePath;
    }
    
    private void loadReport() {
        updateZoom();
        wvAux.getEngine().setUserStyleSheetLocation(stylePath);
        wvAux.getEngine().load(new File(reportPath).toURI().toString());
    }
}
