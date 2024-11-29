package model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Account;
import model.Util.ConfigProvider;
import model.Util.Tools;
import model.User;
import org.mariadb.jdbc.Connection;

public class AccountDAO implements A_DAO {

    private Connection connection;
    private ObservableList<Account> accounts = FXCollections.observableArrayList();
    
    @Override
    public ObservableList<Account> loadAccountsFromDB(User user) {
        connection = new ConfigProvider().getConnection();
        if (connection != null) {
            try {
                ResultSet result = connection.createStatement().executeQuery(
                        "SELECT c.email AS email, c.nombreCuenta AS nombreCuenta, c.passCuenta AS passCuenta, c.idRed AS idRed, rs.nombreRed AS nombreRed, rs.iconoRed AS iconoRed FROM cuenta AS c JOIN red_social AS rs USING (idRed) WHERE email = \"" + user.getEmail() + "\" ORDER BY nombreCuenta"
                );
                while (result.next()) {                
                    accounts.add(new Account(result.getString("email"), result.getString("nombreCuenta"), result.getString("passCuenta"), result.getInt("idRed"), result.getString("nombreRed"), Tools.loadImgFromX64(result.getString("iconoRed"), "account")));
                }
                connection.close();
            } catch (SQLException ex) {
                System.err.println("Error in " + this.getClass().toString() + " requesting data from data base");
                System.err.println(ex.getMessage());
            }
            return accounts;
        }
        return null;
    }

    @Override
    public void insertAccount(Account account, User user) {
        connection = new ConfigProvider().getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement("INSERT INTO cuenta VALUES (?,?,?,?)")) {
                ps.setString(1, user.getEmail());
                ps.setInt(2, account.getIdRed());
                ps.setString(3, account.getNombreCuenta());
                ps.setString(4, account.getPassCuenta());
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
    public void updateAccount(Account newAccount, Account oldAccount, User user) {
        connection = new ConfigProvider().getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement("UPDATE cuenta SET idRed = ?, nombreCuenta = ?, passCuenta = ? WHERE email = ? AND idRed = ?")) {
                ps.setInt(1, newAccount.getIdRed());
                ps.setString(2, newAccount.getNombreCuenta());
                ps.setString(3, newAccount.getPassCuenta());
                ps.setString(4, user.getEmail());
                ps.setInt(5, oldAccount.getIdRed());
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
    public void deleteAccount(Account account, User user) {
        connection = new ConfigProvider().getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement("DELETE FROM cuenta WHERE email = ? AND idRed = ?")) {
                ps.setString(1, user.getEmail());
                ps.setInt(2, account.getIdRed());
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
    
    public boolean checkRepeatedSNInAccount(int sn_id, User user) {
        connection = new ConfigProvider().getConnection();
        if (connection != null) {
            try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM cuenta WHERE email = ? AND idRed = ?");) {
                preparedStatement.setInt(1, sn_id);
                preparedStatement.setString(2, user.getEmail());
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
