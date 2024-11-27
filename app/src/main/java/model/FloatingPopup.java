package model;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Popup;

public class FloatingPopup {
    public static void showPopup(TextField textField, String message){
        Popup popup = new Popup();
        Label warningLabel = new Label(message);
        warningLabel.setStyle("-fx-background-color: #D16D6A; -fx-padding: 5; -fx-border-color: #F3F3F3; -fx-border-width: 1px; -fx-border-style: solid; -fx-font-size: 10px; -fx-text-fill: #F3F3F3;");
        popup.getContent().add(warningLabel);
        double x = textField.localToScreen(0, 0).getX();
        double y = textField.localToScreen(0, 0).getY();
        popup.setAutoHide(true);
        popup.setAnchorX(x);
        popup.setAnchorY(y - textField.getHeight());
        popup.show(textField.getScene().getWindow());
    }
    
    public static Popup createPopupInstance(TextField textField, String message) {
        Popup popup = new Popup();
        Label warningLabel = new Label(message);
        warningLabel.setStyle("-fx-background-color: #D16D6A; -fx-padding: 5; -fx-border-color: #F3F3F3; -fx-border-width: 1px; -fx-border-style: solid; -fx-font-size: 10px; -fx-text-fill: #F3F3F3;");
        popup.getContent().add(warningLabel);
        double x = textField.localToScreen(0, 0).getX();
        double y = textField.localToScreen(0, 0).getY();
        popup.setAutoHide(false);
        popup.setAnchorX(x);
        popup.setAnchorY(y - textField.getHeight());
        return popup;
    }
}
