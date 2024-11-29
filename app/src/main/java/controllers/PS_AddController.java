package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import model.Util.ViewManager;

public class PS_AddController {
    
    private ViewManager viewManager;
    
    private VBox vboxBody;
    
    @FXML
    public Button btnNewUser;
    
    @FXML
    void btnNewUserPressed() {
        MainAppController.viewLoader.loadProfileCreator(vboxBody, viewManager);
    }

    public void setVboxBody(VBox vboxBody) {
        this.vboxBody = vboxBody;
    }

    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }
    
}
