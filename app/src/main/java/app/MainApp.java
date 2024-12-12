package app;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Account;
import model.DAO.AccountDAO;
import model.DAO.SocialNetworkDAO;
import model.DAO.UserDAO;
import model.SocialNetwork;
import model.User;
import model.Util.ConfigProvider;
import model.Util.ViewLoader;

public class MainApp extends Application{
    
    private static Stage mainStage;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        
        try {
            this.mainStage = stage;
            Scene mainScene = new Scene(new ViewLoader().loadMainApp());
            mainScene.getStylesheets().add(chargeTheme());
            mainStage.setScene(mainScene);
            mainStage.setResizable(false);
            mainStage.setTitle("Accounts Manager");
            mainStage.getIcons().add(new Image("/vistas/media/cur_icons/appIcon.png"));
            mainStage.show();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    
    public static void reloadTheme() {
        mainStage.getScene().getStylesheets().clear();
        mainStage.getScene().getStylesheets().add(chargeTheme());
    }
    
    public static String chargeTheme() {
        String theme = new ConfigProvider().loadTheme();
        switch (theme) {
            case "Dark theme" -> {
                return "/vistas/styles/darkMode.css";
            }
            case "Light theme" -> {
                return "/vistas/styles/lightMode.css";
            }
            default -> {
                return "/vistas/styles/darkMode.css";
            }
        }
    }
    
    
    //CREACIÓN DEL ARCHIVO config.properties ¡USO EXCLUSIVO DE DEBUG!
    private void createConfigFile() {
        new ConfigProvider().createConfigProperties("root", "root", "", "Dark theme");
    }
    
    
    //CREACIÓN MASIVA DE USUARIOS ¡USO EXCLUSIVO DE DEBUG!
    private void createMassUsers(){
        for (int i = 0; i < 100; i++) {
            new UserDAO().insertUser(new User("email"+i+"@email.com", "autoUser"+i, null, "N", null));
        }
    }
    
    
    //CREACIÓN MASIVA DE USUARIOS ¡USO EXCLUSIVO DE DEBUG!
    private void createMassSocialNetworks(){
        for (int i = 0; i < 20; i++) {
            new SocialNetworkDAO().insertSocialNetwork(new SocialNetwork(-1, "sn"+i, null));
        }
    }
    
    //CREACIÓN MASIVA DE USUARIOS ¡USO EXCLUSIVO DE DEBUG!
    private void createMassAccounts(){
        User user = new User("emailExample@email.com", "exampleUser", null, "N", null);
        new UserDAO().insertUser(user);
        for (int i = 1; i < 20; i++) {
            new AccountDAO().insertAccount(new Account("emailExample@email.com", "account"+i, "1234", i, null, null), user);
        }
    }
}
