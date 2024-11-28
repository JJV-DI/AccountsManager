package model;

import java.sql.PreparedStatement;
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
    
    @Override
    public void insertSocialNetwork(SocialNetwork socialNetwork) {
        connection = new ConfigProvider().getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement("INSERT INTO red_social (nombreRed, iconoRed) VALUES (?,?)")) {
                ps.setString(1, socialNetwork.getNombreRed());
                if (socialNetwork.getIconoRed() == null) {
                    ps.setNull(2, java.sql.Types.LONGNVARCHAR);
                } else {
                    ps.setString(2, Tools.loadX64FromImage(socialNetwork.getIconoRed()));
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
    public void updateSocialNetwork(SocialNetwork socialNetwork) {
        connection = new ConfigProvider().getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement("UPDATE red_social SET nombreRed = ?, iconoRed = ? WHERE idRed = ?")) {
                ps.setString(1, socialNetwork.getNombreRed());
                if (socialNetwork.getIconoRed() == null) {
                    ps.setNull(2, java.sql.Types.LONGNVARCHAR);
                } else {
                    ps.setString(2, Tools.loadX64FromImage(socialNetwork.getIconoRed()));
                }
                ps.setInt(3, socialNetwork.getIdRed());
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
    public void deleteSocialNetwork(SocialNetwork socialNetwork) {
        connection = new ConfigProvider().getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement("DELETE FROM red_social WHERE idRed = ?")) {
                ps.setInt(1, socialNetwork.getIdRed());
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
    
    public boolean checkRepeatedSocialNetwork(String name) {
        connection = new ConfigProvider().getConnection();
        if (connection != null) {
            try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM red_social WHERE nombreRed = ?");) {
                preparedStatement.setString(1, name);
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
