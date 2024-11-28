package model;

import java.sql.PreparedStatement;
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
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Error in " + this.getClass().toString() + " closing connection");
                    System.err.println(ex.getMessage());
                }
            }
            return users;
        }
        return null;
    }
    
    @Override
    public void insertUser(User user) {
        connection = new ConfigProvider().getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement("INSERT INTO usuario VALUES (?,?,?,?,?)")) {
                ps.setString(1, user.getEmail());
                ps.setString(2, user.getUserName());
                if (user.isPrivate()) {
                    ps.setString(3, user.getUserPass());
                    ps.setString(4, "Y");
                } else {
                    ps.setNull(3, java.sql.Types.VARCHAR);
                    ps.setString(4, "N");
                }
                if (user.getImgUser() == null) {
                    ps.setNull(5, java.sql.Types.LONGNVARCHAR);
                } else {
                    ps.setString(5, Tools.loadX64FromImage(user.getImgUser()));
                }
                ps.executeUpdate();
                connection.close();
            } catch (SQLException ex) {
                System.err.println("Error in " + this.getClass().toString() + " inserting data to data base");
                System.err.println(ex.getMessage());
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Error in " + this.getClass().toString() + " closing connection");
                    System.err.println(ex.getMessage());
                }
            }
        }
    }
    
    @Override
    public void updateUser(User newUser, User oldUser) {
        connection = new ConfigProvider().getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement("UPDATE usuario SET email = ?, nombreUsuario = ?, passUsuario = ?, privacidad = ?, imgUsuario = ? WHERE email = ?")) {
                ps.setString(1, newUser.getEmail());
                ps.setString(2, newUser.getUserName());
                if (newUser.isPrivate()) {
                    ps.setString(3, newUser.getUserPass());
                    ps.setString(4, "Y");
                } else {
                    ps.setNull(3, java.sql.Types.VARCHAR);
                    ps.setString(4, "N");
                }
                if (newUser.getImgUser() == null) {
                    ps.setNull(5, java.sql.Types.LONGNVARCHAR);
                } else {
                    ps.setString(5, Tools.loadX64FromImage(newUser.getImgUser()));
                }
                ps.setString(6, oldUser.getEmail());
                ps.executeUpdate();
                connection.close();
            } catch (SQLException ex) {
                System.err.println("Error in " + this.getClass().toString() + " updating data in data base");
                System.err.println(ex.getMessage());
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Error in " + this.getClass().toString() + " closing connection");
                    System.err.println(ex.getMessage());
                }
            }
        }
    }
    
    @Override
    public void deleteUser(User user) {
        connection = new ConfigProvider().getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement("DELETE FROM usuario WHERE email = ?")) {
                ps.setString(1, user.getEmail());
                ps.executeUpdate();
                connection.close();
            } catch (SQLException ex) {
                System.err.println("Error in " + this.getClass().toString() + " deleting data from data base");
                System.err.println(ex.getMessage());
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Error in " + this.getClass().toString() + " closing connection");
                    System.err.println(ex.getMessage());
                }
            }
        }
    }

    public boolean checkRepeatedUser(String email) {
        connection = new ConfigProvider().getConnection();
        if (connection != null) {
            try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM usuario WHERE email = ?");) {
                preparedStatement.setString(1, email);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    connection.close();
                    resultSet.last();
                    return resultSet.getRow() != 0;
                }
            } catch (SQLException ex) {
                System.err.println("Error in " + this.getClass().toString() + " requesting data from data base for validation");
                System.err.println(ex.getMessage());
                return false;
            }
        }
        return false;
    }
}
