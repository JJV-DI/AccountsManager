/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.ConfigProvider;
import model.ViewLoader;

public class MainApp extends Application{    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        /*CREACIÓN DEL ARCHIVO config.properties USO EXCLUSIVO DE DEBUG*/
        //new ConfigProvider().createConfigProperties("root", "root", "");
        
        
        Parent root = FXMLLoader.load(getClass().getResource("/vistas/frmMainApp.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Accounts Manager");
        stage.getIcons().add(new Image("/vistas/media/app/appIcon_lightMode.png"));
        stage.show();
    }
}
