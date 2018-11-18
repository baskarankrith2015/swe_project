package com.pancakes;

import javax.imageio.ImageIO;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * Created by krithikabaskaran on 11/15/18.
 */
@Path("/images")
public class ImageRendering {
    private HtmlFileReader htmlFileReader = new HtmlFileReader();
    @Path("/chocolate-delicious-dessert-574111.jpg")
    @GET
    @Produces("image/jpg")
    public byte[] serveHomePage() {
        try {
            return getImage("chocolate-delicious-dessert-574111.jpg");


        } catch (Exception e) {
            System.out.println("Something went wrong" + e.getMessage());
        }
        return null;
    }

    public byte[] getImage(String relativePath) throws IOException {
        File file = new File("src/resource/html/images/"+relativePath);
        BufferedImage image = ImageIO.read(file);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        byte[] imageData = baos.toByteArray();

        // uncomment line below to send non-streamed
        return imageData;
    }
}
