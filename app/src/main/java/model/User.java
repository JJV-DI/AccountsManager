/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import javafx.scene.image.Image;

/**
 *
 * @author pepej
 */
public class User {
    private String email;
    private String userName;
    private String userPass;
    private boolean privacity;
    private Image imgUser;

    public User(String email, String userName, String userPass, String privacity, Image imgUser) {
        this.email = email;
        this.userName = userName;
        this.privacity = privacity.equals("Y");
        this.userPass = userPass;
        this.imgUser = imgUser;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public boolean isPrivate() {
        return privacity;
    }

    public Image getImgUser() {
        return imgUser;
    }
}
