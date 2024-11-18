package model;

import javafx.scene.image.Image;

public class Account {
    private String email;
    private String nombreCuenta;
    private String passCuenta;
    private int idRed;
    private String nombreRed;
    private Image iconoRed;

    public Account(String email, String nombreCuenta, String passCuenta, int idRed, String nombreRed, Image iconoRed) {
        this.email = email;
        this.nombreCuenta = nombreCuenta;
        this.passCuenta = passCuenta;
        this.idRed = idRed;
        this.nombreRed = nombreRed;
        this.iconoRed = iconoRed;
    }

    public String getEmail() {
        return email;
    }

    public String getNombreCuenta() {
        return nombreCuenta;
    }

    public String getPassCuenta() {
        return passCuenta;
    }

    public int getIdRed() {
        return idRed;
    }

    public String getNombreRed() {
        return nombreRed;
    }

    public Image getIconoRed() {
        return iconoRed;
    }

    
}
