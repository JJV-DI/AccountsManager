package app;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;
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
            initScene();
            mainStage.setResizable(false);
            mainStage.setTitle("Accounts Manager");
            mainStage.show();
        } catch (Exception e){
            System.err.println("Error in " + this.getClass().toString() + " loading Main App interface");
            System.err.println(e.getMessage());
        }
    }
    
    public static void initScene() {
        overrideIcons();
        Scene mainScene = new Scene(new ViewLoader().loadMainApp());
        mainScene.getStylesheets().add(chargeStylesheet());
        mainStage.setScene(mainScene);
        mainStage.getIcons().clear();
        mainStage.getIcons().add(new Image("/vistas/media/cur_icons/appIcon.png"));
    }
    
    public static void closeApp() {
        mainStage.close();
    }
    
    public static String chargeStylesheet() {
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
    
    public static void overrideIcons() {
        String theme = new ConfigProvider().loadTheme();
        switch (theme) {
            case "Dark theme" -> replaceDirectoryContents("/vistas/media/themes/dark", "/vistas/media/cur_icons");
            case "Light theme" -> replaceDirectoryContents("/vistas/media/themes/light", "/vistas/media/cur_icons");
        }
    }

    private static void replaceDirectoryContents(String sourceDirPath, String targetDirPath) {
        URL sourceDirUrl = MainApp.class.getResource(sourceDirPath);
        Path sourceDir;
        try {
            sourceDir = Paths.get(sourceDirUrl.toURI());
        } catch (URISyntaxException e) {
            System.err.println("Error in MainApp geting icons source directory");
            System.err.println(e.getMessage());
            return;
        }
        
        URL targetDirUrl = MainApp.class.getResource(targetDirPath);
        Path targetDir;
        try {
            targetDir = Paths.get(targetDirUrl.toURI());
        } catch (URISyntaxException e) {
            System.err.println("Error in MainApp geting icons target directory");
            System.err.println(e.getMessage());
            return;
        }

        try {
            if (Files.exists(targetDir)) {
                deleteDirectoryContents(targetDir);
            } else {
                Files.createDirectories(targetDir);
            }
            Files.walk(sourceDir).forEach(source -> {
                Path destination = targetDir.resolve(sourceDir.relativize(source));
                try {
                    if (Files.isDirectory(source)) {
                        Files.createDirectories(destination);
                    } else {
                        Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
                    }
                } catch (IOException e) {
                    System.err.println("Error in MainApp copying to or creating target directory");
                    System.err.println(e.getMessage());
                }
            });
        } catch (IOException e) {
            System.err.println("Error in MainApp replacing contents into target directory");
            System.err.println(e.getMessage());
        }
    }
    
    private static void deleteDirectoryContents(Path directory) throws IOException {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory)) {
            for (Path entry : stream) {
                if (Files.isDirectory(entry)) {
                    deleteDirectoryContents(entry);
                    Files.delete(entry);
                } else {
                    Files.delete(entry);
                }
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
