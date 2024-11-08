package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class MainAppController implements Initializable{

    @FXML
    private ImageView imgProfile;

    @FXML
    private ImageView imgSearch;

    @FXML
    private ImageView imgSettings;

    @FXML
    private GridPane paneGridBody;

    @FXML
    private ToggleButton toggleProfiles;

    @FXML
    private ToggleButton toggleSearch;

    @FXML
    private ToggleButton toggleSettings;

    @FXML
    private VBox vbBody;

    @FXML
    void toggleProfilesPressed(ActionEvent event) {
        
    }

    @FXML
    void toggleSearchPressed(ActionEvent event) {

    }

    @FXML
    void toggleSettingsPressed(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            vbBody.getChildren().add(FXMLLoader.load(getClass().getResource("/vistas/frmProfileSelect.fxml")));
        } catch (IOException e) {
            System.err.println("Error in " + this.getClass().toString() + " loading fxml file");
        }
        
        toggleProfiles.setSelected(true);
    }
    
    private void inicializarBotonesToolbar(){
        toggleProfiles.setOnAction(e -> {
            if (!toggleProfiles.isSelected()) {
                toggleProfiles.setSelected(true);
            } else {
                toggleProfiles.setStyle();
            }
        });
    }
}
