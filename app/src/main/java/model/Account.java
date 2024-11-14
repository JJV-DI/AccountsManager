/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author pepea
 */
public class Account {
    private String email;
    private int idRed;
    private String nombreCuenta;
    private String passCuenta;

    public Account(String email, int idRed, String nombreCuenta, String passCuenta) {
        this.email = email;
        this.idRed = idRed;
        this.nombreCuenta = nombreCuenta;
        this.passCuenta = passCuenta;
    }

    public String getEmail() {
        return email;
    }

    public int getIdRed() {
        return idRed;
    }

    public String getNombreCuenta() {
        return nombreCuenta;
    }

    public String getPassCuenta() {
        return passCuenta;
    }
    
    
}
