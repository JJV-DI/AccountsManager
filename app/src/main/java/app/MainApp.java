package app;

import java.nio.file.StandardOpenOption;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
            createDefaultProperties();
            this.mainStage = stage;
            initScene();
            mainStage.setResizable(false);
            mainStage.setTitle("Accounts Manager");
            mainStage.show();
        } catch (Exception e){
            System.err.println("Error in " + this.getClass().toString() + " loading Main App interface");
            System.err.println(e.getMessage());
            System.err.println(e.fillInStackTrace());
            System.err.println(e.getCause());
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
        if (sourceDirUrl == null) {
            System.err.println("Source directory not found: " + sourceDirPath);
            return;
        }

        Path targetDir = Paths.get(targetDirPath);
        try {
            if (Files.exists(targetDir)) {
                deleteDirectoryContents(targetDir);
            } else {
                Files.createDirectories(targetDir);
            }

            // Listar los archivos en el directorio de origen
            try (InputStream inputStream = sourceDirUrl.openStream()) {
                // Usar un JarFile para acceder a los archivos dentro del JAR
                try (JarInputStream jarInputStream = new JarInputStream(inputStream)) {
                    JarEntry entry;
                    while ((entry = jarInputStream.getNextJarEntry()) != null) {
                        if (entry.getName().startsWith(sourceDirPath.substring(1))) { // Ignorar el prefijo "/"
                            Path destination = targetDir.resolve(Paths.get(entry.getName()).getFileName());
                            if (entry.isDirectory()) {
                                Files.createDirectories(destination);
                            } else {
                                try (OutputStream out = Files.newOutputStream(destination, StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
                                    byte[] buffer = new byte[1024];
                                    int bytesRead;
                                    while ((bytesRead = jarInputStream.read(buffer)) != -1) {
                                        out.write(buffer, 0, bytesRead);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error in MainApp replacing contents into target directory");
            System.err.println(e.getMessage());
        }
    }
    
    
    /*
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
    */
    
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
    
    private static void createDefaultProperties() {
        if (!new File("config.properties").exists()) {
            new ConfigProvider().createConfigProperties("jdbc:mariadb://54.156.254.121:3306/account_manager", "admin", "9qs5xmpeqr8P", "", "Dark theme");
        }
    }
    
    
    //CREACIÓN MASIVA DE USUARIOS ¡USO EXCLUSIVO DE DEBUG!
    private void createMassUsers(){
        for (int i = 0; i < 10000; i++) {
            new UserDAO().insertUser(new User("email"+i+"@email.com", "autoUser"+i, null, "N", null));
        }
    }
    
    //CREACIÓN MASIVA DE USUARIOS ¡USO EXCLUSIVO DE DEBUG!
    private void createMassSocialNetworks(){
        for (int i = 0; i < 10000; i++) {
            new SocialNetworkDAO().insertSocialNetwork(new SocialNetwork(-1, "sn"+i, null));
        }
    }
    
    //CREACIÓN MASIVA DE USUARIOS ¡USO EXCLUSIVO DE DEBUG!
    private void createMassAccounts(){
        User user = new User("emailExample@email.com", "exampleUser", null, "N", null);
        new UserDAO().insertUser(user);
        for (int i = 1; i < 10000; i++) {
            new AccountDAO().insertAccount(new Account("emailExample@email.com", "account"+i, "1234", i, null, null), user);
        }
    }
}
