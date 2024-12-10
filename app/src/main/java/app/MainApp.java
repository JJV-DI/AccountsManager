package app;

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
        mainStage.setScene(mainScene);
        mainStage.setResizable(false);
        mainStage.setTitle("Accounts Manager");
        mainStage.getIcons().add(new Image("/vistas/media/app/appIcon_lightMode.png"));
        mainStage.show();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    
    public static void restartApp() {
        mainStage.setScene(new Scene(new ViewLoader().loadMainApp()));
    }
    
    
    //CREACIÓN DEL ARCHIVO config.properties ¡USO EXCLUSIVO DE DEBUG!
    private void createConfigFile() {
        new ConfigProvider().createConfigProperties("root", "root", "");
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
