package model;

import javafx.scene.image.Image;

public class SocialNetwork {
    private int idRed;
    private String nombreRed;
    private Image iconoRed;

    public SocialNetwork(int idRed, String nombreRed, Image iconoRed) {
        this.idRed = idRed;
        this.nombreRed = nombreRed;
        this.iconoRed = iconoRed;
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
