package model;

import java.util.regex.Pattern;

public class FieldValidator {
    public static boolean emptinessValidation(String string) {
        return !string.isEmpty();
    }
    
    public static boolean commonNameValidation(String name) {
        return Pattern.matches("^[a-zA-Z0-9@._-]+$", name);
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
    
    public static boolean lengthValidation(int maxLength, String string) {
        if (string != null) {
            return string.length() <= maxLength;
        }
        return true;
    }
}
