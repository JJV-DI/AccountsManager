package model.Util;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Util.TogglerBuilder;

public class PassLblToggler {
    private Button btnToggle;
    private Label label;
    private String message;
    private boolean isHide;
    private ImageView image;

    public PassLblToggler(Button btnToggle, Label label, boolean isHide, ImageView image) {
        this.btnToggle = btnToggle;
        this.label = label;
        this.message = label.getText();
        this.isHide = isHide;
        this.image = image;
        toggleAction();
        initBtnAction();
    }
    
    public String getMessage() {
        if (isHide) return label.getText();
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isHide() {
        return isHide;
    }

    public void setShown(boolean shown) {
        this.isHide = shown;
    }
    
    private void initBtnAction() {
        btnToggle.setOnAction(event -> {
            toggleAction();
        });
    }
    
    private void toggleAction() {
        if (isHide) {
            message = label.getText();
            label.setText(TogglerBuilder.passToAsterisk(message));
            image.setImage(new Image("/vistas/media/cur_icons/eye.png"));
        } else {
            label.setText(message);
            image.setImage(new Image("/vistas/media/cur_icons/eyeCrossed.png"));
        }
        isHide = !isHide;
    }
}