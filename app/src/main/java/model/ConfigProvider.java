package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.mariadb.jdbc.Connection;

public class ConfigProvider {
    
    public String loadDBUrl() {
        try (FileInputStream fileIS = new FileInputStream("config.properties")) { 
            Properties properties = new Properties();
            properties.load(fileIS);
            return properties.getProperty("url");
        } catch (IOException e) { 
            System.err.println("Error in " + this.getClass().toString() + " loading url from config.properties");
            System.err.println(e.getCause());
            return "";
        } 
    }
    
    public String loadDBUsername() {
        try (FileInputStream fileIS = new FileInputStream("config.properties")) { 
            Properties properties = new Properties();
            properties.load(fileIS);
            return properties.getProperty("username");
        } catch (IOException e) { 
            System.err.println("Error in " + this.getClass().toString() + " loading username from config.properties");
            System.err.println(e.getCause());
            return "";
        } 
    }
    
    public String loadDBPassword() {
        try (FileInputStream fileIS = new FileInputStream("config.properties")) { 
            Properties properties = new Properties();
            properties.load(fileIS);
            return properties.getProperty("password");
        } catch (IOException e) { 
            System.err.println("Error in " + this.getClass().toString() + " loading password from config.properties");
            System.err.println(e.getCause());
            return "";
        } 
    }
    
    public String loadAdminPass() {
        try (FileInputStream fileIS = new FileInputStream("config.properties")) { 
            Properties properties = new Properties();
            properties.load(fileIS);
            return properties.getProperty("adminPass");
        } catch (IOException e) { 
            System.err.println("Error in " + this.getClass().toString() + " loading administrator password from config.properties");
            System.err.println(e.getCause());
            return "";
        }
    }
    
    public void saveAdminPass(String adminPass) {
        createConfigProperties(loadDBUsername(), loadDBPassword(), adminPass);
    }
    
    public Connection getConnection(){
        try {
            return (Connection) DriverManager.getConnection(loadDBUrl(), loadDBUsername(), loadDBPassword());
        } catch (SQLException ex) {
            System.err.println("Error in " + this.getClass().toString() + " connecting to data base");
            System.err.println(ex.getMessage());
            return null;
        }
    }
    
    /*CREAR ARCHIVO CONFIG (USO EXCLUSIVO DE DEBUG)*/
    public void createConfigProperties(String user, String pass, String adminPass) {
        Properties properties = new Properties();
        properties.setProperty("url", "jdbc:mariadb://localhost:3306/account_manager"); 
        properties.setProperty("username", user); 
        properties.setProperty("password", pass);
        properties.setProperty("adminPass", adminPass);
        try (FileOutputStream fileOS = new FileOutputStream("config.properties")) { 
            properties.store(fileOS, "DB credentials:");
        } catch (IOException e) { 
            System.err.println("Error in " + this.getClass().toString() + " creating config.properties file");
            System.err.println(e.getCause());
        }
    }
}
