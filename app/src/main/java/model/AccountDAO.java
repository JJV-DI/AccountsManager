package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
                        "SELECT c.email AS email, c.nombreCuenta AS nombreCuenta, c.passCuenta AS passCuenta, c.idRed AS idRed, rs.nombreRed AS nombreRed, rs.iconoRed AS iconoRed FROM cuenta AS c JOIN red_social AS rs USING (idRed) WHERE email = \"" + user.getEmail() + "\""
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
}
