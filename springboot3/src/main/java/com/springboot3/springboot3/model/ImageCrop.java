package com.springboot3.springboot3.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageCrop {



      static  BufferedImage imageCrop ( BufferedImage image ) throws IOException {


          int imageWidth = image.getWidth();
          int imageHeight = image.getHeight();
           int x = 90;  // starting x coordinate
           int y = 90;  // starting y coordinate
           int width =1300;  // width of the cropped area
           int height = 1900;
          BufferedImage subImage = image.getSubimage(x, y, width, height);

          // Create a new BufferedImage and copy the subimage into it
//          BufferedImage croppedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//          Graphics2D g = croppedImage.createGraphics();
//          g.drawImage(subImage, 0, 0, null);
//          g.dispose();
          File outputFile = new File("cropped_image.jpg");
          ImageIO.write(subImage, "jpg", outputFile);

           return subImage;

       }


}
