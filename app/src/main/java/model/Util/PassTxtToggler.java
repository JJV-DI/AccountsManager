package model.Util;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import model.Util.TogglerBuilder;

public class PassTxtToggler {
    private Button btnToggle;
    private TextField textField;
    private String message;
    private boolean shown;
    private ImageView image;
    private HBox hbWarn;

    public PassTxtToggler(Button btnToggle, TextField textField, boolean shown, ImageView image, HBox hbWarn) {
        this.btnToggle = btnToggle;
        this.textField = textField;
        this.shown = shown;
        this.image = image;
        this.message = textField.getText();
        this.hbWarn = hbWarn;
        toggleAction();
        initBtnAction();
    }

    public String getMessage() {
        if (shown) return textField.getText();
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isShown() {
        return shown;
    }

    public void setShown(boolean shown) {
        this.shown = shown;
    }
    
    private void initBtnAction() {
        btnToggle.setOnAction(event -> {
            toggleAction();
        });
    }
    
    private void toggleAction() {
        shown = !shown;
        if (shown) {
            textField.setText(message);
            textField.setEditable(true);
            hbWarn.setVisible(false);
            image.setImage(new Image("/vistas/media/cur_icons/eyeCrossed.png"));
        } else {
            message = textField.getText();
            textField.setText(TogglerBuilder.passToAsterisk(message));
            textField.setEditable(false);
            hbWarn.setVisible(true);
            image.setImage(new Image("/vistas/media/cur_icons/eye.png"));
        }
    }
}