package model.Util;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;


public class TogglerBuilder {
    public static PassTxtToggler buildTxtToggler(Button btnToggle, TextField textField, boolean shown, ImageView image, HBox hbWarn) {
        return new PassTxtToggler(btnToggle, textField, shown, image, hbWarn);
    }
    
    public static PassLblToggler buildLblToggler(Button btnToggle, Label label, boolean shown, ImageView image) {
        return new PassLblToggler(btnToggle, label, shown, image);
    }
    
    public static String passToAsterisk(String pass) {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < pass.length(); i++) sb.append("⁕");
        return sb.toString();
    }
}
