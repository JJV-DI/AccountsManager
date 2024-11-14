/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.mariadb.jdbc.Connection;

/**
 *
 * @author pepea
 */
public class AccountDAO implements A_DAO {

    private Connection connection;
    private ObservableList<Account> accounts = FXCollections.observableArrayList();
    
    @Override
    public ObservableList<Account> loadAccountsFromDB(User user) {
        setConnection();
        if (connection != null) {
            try {
                ResultSet result = connection.createStatement().executeQuery("SELECT * FROM cuenta WHERE email = \"" + user.getEmail() + "\"");
                while (result.next()) {                
                    accounts.add(new Account(result.getString("email"), result.getInt("idRed"), result.getString("nombreCuenta"), result.getString("passCuenta")));
                }
            } catch (SQLException ex) {
                System.err.println("Error in " + this.getClass().toString() + " requesting data from data base");
                System.err.println(ex.getMessage());
            }
            return accounts;
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
