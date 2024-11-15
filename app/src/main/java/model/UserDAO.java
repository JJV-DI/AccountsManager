package model;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.mariadb.jdbc.Connection;

public class UserDAO implements U_DAO{
    
    private Connection connection;
    private ObservableList<User> users = FXCollections.observableArrayList();
    
    @Override
    public ObservableList<User> loadUsersFromDB() {
        connection = new ConfigProvider().getConnection();
        if (connection != null) {
            try {
                ResultSet result = connection.createStatement().executeQuery("SELECT * FROM usuario");
                while (result.next()) {                
                    users.add(new User(result.getString("email"), result.getString("nombreUsuario"), result.getString("passUsuario"), result.getString("privacidad"), Tools.loadImgFromX64(result.getString("imgUsuario"), "user")));
                }
                connection.close();
            } catch (SQLException ex) {
                System.err.println("Error in " + this.getClass().toString() + " requesting data from data base");
                System.err.println(ex.getMessage());
            }
            return users;
        }
        return null;
    }    
}
