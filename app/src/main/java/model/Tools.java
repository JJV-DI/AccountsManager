package model;


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
                    return loadImgFromX64("data:image/png;base64,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");
                }
                case "social_network" -> {
                    return loadImgFromX64("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAGAAAABgCAYAAADimHc4AAAAAXNSR0IArs4c6QAAB0xJREFUeF7tnWtu2zoQhSXYf20gK2i6kpuspM1K0qyk7UqarqS5Kwhg/5WhanzJgFVFnjN8iL4ODRQNHImP+WbODOmY6rv2qmqBvmrvrfOuAajsBA1AA1DZApW7bxHQAFS2QOXuWwQ0AH4LvL6+3m6329txHG/7vv8gV5qf7Xvy/4u83/f9i/2567qf9r3dbvdc2cbB7i8uAozRP3Vdd2f+JdlPoAicqS0B8fPSgFwEgNxGDxEzQL5dCoyqAI7H4904jl/7vr9NcvPImy2M3W73FNlE8m1VABiP/5pDYpIt8F9eEZl6qCFPqwIQw282m8dJkz/nMFzuNqYE/+10Oj3d3NycE/sar1UAOBr/ZY1JpfRhZWkYhu9rgCgOwBj/V4pRatwrIE6n031pCEUBSJLtuu5HQQPaGl/6yf5aI0kXA3A8Hh8ni5SWnPthGF622y2MMGPM2GrrS6lKqQiA4/EoXq/2SrOSfer73i7EvF4tCXO/3z/IBYfDQUpZlNglWp7GcfxEXLvU7/Nut7vPHWZZAcSUl/Okx8rW5JFvY1fkmXspNWOLghJ5ISsAree7Xmw9i/HmcRwf9vu9rGbfXofD4fO0XyRrCypq5KIYEAJhv99/zBUJ2QBojB9a+Ey5YwSTW5QCJvp8/Qq8aT0m6xMqRyw5TiyQLACUCderpYz3T3s4ZxlZmjAjRT7jRURDlsScDIDVbGOw4KCR9zOehyAiCVE6UzKEJAAa4y/ptuvF5MS93m/bYqJAyuNQWUmOJcseUioApNdnuyDjm1LyF9BgugxE+QhFgYxHAyFlxRwNAIW69UjG+GbCQZhsOwYmrIhCucSOXQHhbU2iTcZRAMgwl7FQGsmUkNPm2Ed2X4YcHzU2BkLKdnYUABTixgtySobaw4gxUuNjylvtfN0oUQNQJF6YMJ1QzyY/TpvBjUAmDygTu1xOz9m2HQOA2eehwpvVa3fbgdVYUoZogzEyqYEaBYAZhPz1gWbTCiVzpvb3QUFta8dKyJo6ClQRcDgcUKnIOujVXqeNAhqAQvuv1riKidHSRgMg5Ucxxqu+lM6BGgBNfkif0cgQDQBtlJFje0+XUTJEAWjyE+U3lAxRAMjyK2qUV3wTVY6zAKhdzys2pnpqbB6AAJjyM2alimQtZQE2txaxoUbJxbxdYl0E8wAEUMpQaJWq2X5G7lkKAJJmZg4QQKnBrwwA/YUepdclIgsCKGUoFL6a/X8iAkoBQO1CaUsGELMFa3ZBgwu7nADQziibMBciAAGAkQUBIJ2LNdSaEVAKAGqX2W2FAEoZCq2sYyornxQhQ8VGQI52IYBShirVrg9Cqf5C7TJgGwBDLDbiUsFCANcgQWLjVEMtRdZaEhT8DLgl4eCXQ9KrILQOaGWo/ytYzHYKlCBiJQz3O5bCF5W3sWA9fSXX60vt5timyQEArvZqA8hhKM8cgt+Dy7IXhAbPLDZiBj/dEwW2lKd62g1+Ny0LALQdzdS6FwAg2VAeAOhzcijPUIJQqYU2wt7z75kKEQIwNTTz54jv2dZ/zZ2pgOQmCgCRB5rxZxZg9J8G0GRI71+M/NAAmgzpALDyowWwxtkPuple6NWs/KgANBniabPyowLQZIgDoF0XUVWQ7Rotysx1cAfQnQraE9Lo6dxEaCNR2zYaq+kfLr7ccaoAKKKA3kZAULUe5U4OfZah0WpiU1K6VjmfWoLkBiYXaL62ybQXszPKrF3YT8GQkzjQVd4fBYCNAo3nEqGt9izUpkZ+UFtiE017SRLERoFmUMhbYw5KQh9BsvJDSk+nqXySAUgDKME5ncB8QMoQbMf2iYDKdYzBmHY0jrZUR6mTsG3EHML6gzzkCBoPhblG0lDyZZKlwvhJJ2hFA9BIkYEWhEBOGCY5MmHmGIv1RTim0AoiCYCRIuZkEjsG78QZGWISHSONoeqHdIQsxo+uguZE2URl7nsehuFh6eQTxnihkpTxfnBkmeZAcSirIc+3v0uOACfxMWd3ni/3nUhLRoFXc1Ee8SVfBtzMmOqy2AcjGwDFsS7uWP7yIiYKch5bqT3NXVMMrBoBNilvNhu2MvojGuxp5WwUzI8JQ3W/MUbywa05zwzNlgNc0hHHP77dLvrc9/13OcMTHX2sPbrYPhtA6/HO3LLJjmuvbBKUmJiZaF26xp7lXPKEduk3S8JdmkAxABElqhqCSebytAv1QeGKzpLqfNRPUQCxeQENeo3fa3Z0U8ZTHICFMJ3xL0fSl36eQIotgtVZrobn7awCwHaakqBLGWDWrneRWKr/VQHMQGhWnaXmf253LblZPQkjq5l9F/i0DNRO7O/tEzvmzyKIbS/mvioRMB+oI01Znh8ZMsT8iR0xRst5z0UAcCdkPme4Y54jwxrCt/fE3l/yuosDMIdhH2dr3v9H/pcPgZzH2v7xOFvz5FTR9X/l5xqPJ9QAu2gAmon8X69tACqTawAagMoWqNx9i4AGoLIFKnffIqABqGyByt3/Bufh5awQDFP7AAAAAElFTkSuQmCC");
                }
                case "account" -> {
                    return loadImgFromX64("data:image/png;base64,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");
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
