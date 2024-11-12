package model;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.mariadb.jdbc.Connection;

public class SocialNetworkDAO implements SN_DAO {
    
    private Connection connection;
    private ObservableList<SocialNetwork> socialNetworks = FXCollections.observableArrayList();
    
    @Override
    public ObservableList<SocialNetwork> loadSocialNetworks() {
         setConnection();
        if (connection != null) {
            try {
                ResultSet result = connection.createStatement().executeQuery("SELECT * FROM red_social");
                while (result.next()) {                
                    socialNetworks.add(new SocialNetwork(result.getInt("idRed"), result.getString("nombreRed"), Tools.clipImageToSquare(Tools.loadImgFromX64(result.getString("iconoRed")))));
                }
            } catch (SQLException ex) {
                System.err.println("Error in " + this.getClass().toString() + " requesting data from data base");
                System.err.println(ex.getMessage());
            }
            return socialNetworks;
        }
        return null;
    }
    
    private void setConnection(){
        try {
            connection = (Connection) DriverManager.getConnection("jdbc:mariadb://localhost:3306/account_manager", "root", "root");
        } catch (SQLException ex) {
            System.err.println("Error in " + this.getClass().toString() + " connecting to data base");
            System.err.println(ex.getMessage());
        }
    }
    
    
}
