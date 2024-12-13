package model.Util;

import model.DAO.SocialNetworkDAO;
import model.DAO.UserDAO;
import java.util.regex.Pattern;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FieldValidator {
    public static boolean emptinessValidation(String string) {
        return !string.isEmpty();
    }
    
    public static boolean commonNameValidation(String name) {
        return Pattern.matches("^[a-zA-Z0-9@._\\- ]+$", name);
    }
    
    public static boolean emailValidation(String email) {
        return Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", email);
    }
    
    public static boolean passwordValidation(String password) {
        return Pattern.matches("^[A-Za-z0-9@$!%*?&._-]*$", password);
    }
    
    public static boolean repeatedUserValidation(String email) {
        return new UserDAO().checkRepeatedUser(email);
    }
    
    public static boolean repeatedSocialNetworkValidation(String name) {
        return new SocialNetworkDAO().checkRepeatedSocialNetwork(name);
    }
    
    public static boolean lengthValidation(int maxLength, String string) {
        if (string != null) {
            return string.length() <= maxLength;
        }
        return true;
    }
    
    public static void toggleTextFieldSecondaryInError(boolean error, TextField textField, String message) {
        if (error) {
            textField.getStyleClass().removeAll("secondary-color");
            textField.getStyleClass().add("fieldError-color");
            FloatingPopup.showTextFieldPopup(textField, message);
        } else {
            textField.getStyleClass().removeAll("fieldError-color");
            textField.getStyleClass().add("secondary-color");
        }
    }
    
    public static void toggleTextFieldPrimaryInError(boolean error, TextField textField, String message) {
        if (error) {
            textField.getStyleClass().removeAll("primary-color");
            textField.getStyleClass().add("fieldError-color");
            FloatingPopup.showTextFieldPopup(textField, message);
        } else {
            textField.getStyleClass().removeAll("fieldError-color");
            textField.getStyleClass().add("primary-color");
        }
    }
    
    public static void toggleHBoxInError(boolean error, HBox hBox) {
        if (error) {
            hBox.getStyleClass().removeAll("secondary-color");
            hBox.getStyleClass().add("fieldError-color");
        } else {
            hBox.getStyleClass().removeAll("fieldError-color");
            hBox.getStyleClass().add("secondary-color");
        }
    }
    
    public static void toggleLabelInError(boolean error, Label label) {
        if (error) {
            label.getStyleClass().removeAll("text-color");
            label.getStyleClass().add("error-text-color");
            //FloatingPopup.showTextFieldPopup(textField, message);
        } else {
            label.getStyleClass().removeAll("error-text-color");
            label.getStyleClass().add("text-color");
        }
    }
}
