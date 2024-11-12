package model;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import javax.imageio.ImageIO;

public class Tools {
    public static BufferedImage loadImgFromX64(String x64){
        if (x64 != null) {
            try {
                String imageData = x64.split(",")[1];
                byte[] imageBytes = Base64.getDecoder().decode(imageData);
                ByteArrayInputStream byteArrayIS = new ByteArrayInputStream(imageBytes);
                BufferedImage image = ImageIO.read(byteArrayIS);
                byteArrayIS.close();
                return image;
            } catch (IOException e) {
                System.err.println("Error in Tools.class loading Image from x64");
            }
        } else {
            try{
                return ImageIO.read(new File("/vistas/media/addUser_darkMode.png"));
            } catch (IOException ex) {
                System.err.println("Error in Tools.class loading default Image");
            }
        }
        return null;
    }
}
