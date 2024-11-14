package controllers;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class PS_AddController implements Initializable{
    
    private VBox vboxBody;
    
    @FXML
    public Button btnNewUser;
    
    @FXML
    void btnNewUserPressed() {
        vboxBody.getChildren().clear();
        try {
            FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("/vistas/frmProfileCreator.fxml"));
            vboxBody.getChildren().add(fxmlLoader2.load());
            ProfileCreatorController profileCreatorController = fxmlLoader2.getController();
            profileCreatorController.setVboxBody(vboxBody);
            profileCreatorController.initComponents(false);
        } catch (IOException ex) {
            System.err.println("Error in " + this.getClass().toString() + " loading profile creator fxml file");
            System.err.println(ex.getCause());
        }
    }

    public void setVboxBody(VBox vboxBody) {
        this.vboxBody = vboxBody;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
}
