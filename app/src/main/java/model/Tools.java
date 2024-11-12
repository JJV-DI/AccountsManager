package model;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;


public class Tools {
    public static Image loadImgFromX64(String x64){
        if (x64 != null) {
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
            return loadImgFromX64("data:image/png;base64,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");
        }
        return null;
    }
    
    public static Image clipImageToSquare(Image oldImage){
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(oldImage, null);
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        int size = Math.max(width, height);
        BufferedImage squareImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = squareImage.createGraphics();
        graphics2D.fillRect(0, 0, size, size);
        int x = (size - width) / 2;
        int y = (size - height) / 2;
        graphics2D.drawImage(bufferedImage, x, y, null);
        graphics2D.dispose();
        return SwingFXUtils.toFXImage(squareImage, null);
    }
}
