package model;

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
        connection = new ConfigProvider().getConnection();
        if (connection != null) {
            try {
                ResultSet result = connection.createStatement().executeQuery("SELECT * FROM red_social");
                while (result.next()) {                
                    socialNetworks.add(new SocialNetwork(result.getInt("idRed"), result.getString("nombreRed"), Tools.loadImgFromX64(result.getString("iconoRed"), "social_network")));
                }
                connection.close();
            } catch (SQLException ex) {
                System.err.println("Error in " + this.getClass().toString() + " requesting data from data base");
                System.err.println(ex.getMessage());
            }
            return socialNetworks;
        }
        return null;
    }    
}
