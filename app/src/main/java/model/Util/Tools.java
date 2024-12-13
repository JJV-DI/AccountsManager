package model.Util;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;


public class Tools {
    public static Image loadImgFromX64(String x64, String type){
        if (x64 != null && !x64.isEmpty()) {
            try {
                String imageData = x64.split(",")[1];
                byte[] imageBytes = Base64.getDecoder().decode(imageData);
                ByteArrayInputStream byteArrayIS = new ByteArrayInputStream(imageBytes);
                BufferedImage bufferedImage = ImageIO.read(byteArrayIS);
                byteArrayIS.close();
                
                return SwingFXUtils.toFXImage(bufferedImage, null);
            } catch (IOException e) {
                System.err.println("Error in Tools.class loading Image from x64");
                System.err.println(e.getMessage());
            }
        } else {
            switch (type) {
                case "user" -> {
                    return new Image("/vistas/media/cur_icons/userDefaultIcon.png");
                }
                case "social_network" -> {
                    return new Image("/vistas/media/cur_icons/social.png");
                }
                case "account" -> {
                    return new Image("/vistas/media/cur_icons/social.png");
                }
            }
        }
        return null;
    }
    
    private static Image loadImgFromX64(String x64) {
        return loadImgFromX64(x64, "");
    }
    
    public static String loadX64FromImage(Image image) {
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
        } catch (IOException e) {
            System.err.println("Error in Tools.class loading x64 from Image");
            System.err.println(e.getMessage());
            return null;
        }
        byte[] imageBytes = byteArrayOutputStream.toByteArray();
        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
        return "data:image/png;base64," + base64Image;
    }

    
    public static Image resizeImageToSquare(javafx.scene.image.Image originalImage) {
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(originalImage, null);
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        int size = Math.min(width, height);
        BufferedImage squareImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = squareImage.createGraphics();
        int x = (width - size) / 2;
        int y = (height - size) / 2;
        g2d.drawImage(bufferedImage.getSubimage(x, y, size, size), 0, 0, null);
        g2d.dispose();
        return SwingFXUtils.toFXImage(squareImage, null);
    }
    
    public static Image clipImageToSquare(Image oldImage){
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(oldImage, null);
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        int size = Math.min(width, height);
        BufferedImage squareImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = squareImage.createGraphics();
        graphics2D.fillRect(0, 0, size, size);
        graphics2D.drawImage(bufferedImage, size, size, null);
        graphics2D.dispose();
        return SwingFXUtils.toFXImage(squareImage, null);
    }
}
