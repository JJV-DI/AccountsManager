import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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

public class MainApp extends Application{    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {        
        Parent root = FXMLLoader.load(getClass().getResource("/vistas/frmMainApp.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Accounts Manager");
        stage.getIcons().add(new Image("/vistas/media/app/appIcon_lightMode.png"));
        stage.show();
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
