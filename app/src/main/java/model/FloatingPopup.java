package model;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Popup;

public class FloatingPopup {
    public static void showPopup(TextField textField, String message){
        Popup popup = new Popup();
        Label warningLabel = new Label(message);
        warningLabel.setStyle("-fx-background-color: #D16D6A; -fx-padding: 5;");
        popup.getContent().add(warningLabel);
        double x = textField.localToScreen(0, 0).getX();
        double y = textField.localToScreen(0, 0).getY();
        popup.setAutoHide(true);
        popup.setAnchorX(x);
        popup.setAnchorY(y - textField.getHeight());
        popup.show(textField.getScene().getWindow());
    }
}
