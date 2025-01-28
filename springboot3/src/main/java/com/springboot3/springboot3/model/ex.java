package com.springboot3.springboot3.model;

import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ex {

    public static void main(String[] args) {
        //794794540768_438399
        String pdfFilePath = "C:\\Users\\BZF26\\Downloads\\794794540768_438399.pdf";
        String zplOutputPath = "C:\\Users\\BZF26\\Downloads\\zpl_output.txt";

        try (PDDocument document = PDDocument.load(new File(pdfFilePath))) {

            Splitter splitter = new Splitter();
           List<PDDocument> pagesss =  splitter.split(document);
            // Process each page of the PDF
            List<String> zplCommands = new ArrayList<>();
            for (int page = 0; page < document.getNumberOfPages(); page++) {
                PDFRenderer pdfRenderer = new PDFRenderer(pagesss.get(page));
                BufferedImage image = pdfRenderer.renderImageWithDPI(0, 300); // Render at 300 DPI

                // Convert image to monochrome
                BufferedImage monochromeImage = convertToMonochrome(image);
                ImageIO.write(monochromeImage, "PNG", new File("output-page-" + page + ".png"));
                // Convert monochrome image to ZPL
                String zpl = convertImageToZpl(monochromeImage, page);

                zplCommands.add(zpl);
                pagesss.get(0).close();

            }

            // Save ZPL commands to a file
            try (FileWriter writer = new FileWriter(zplOutputPath)) {
                writer.write(zplCommands.toString());
                System.out.println("ZPL output saved to: " + zplOutputPath);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Converts an image to monochrome (black-and-white).
     */
    private static BufferedImage convertToMonochrome(BufferedImage image) {
        BufferedImage monochromeImage = new BufferedImage(
                image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_BINARY);

        Graphics2D graphics = monochromeImage.createGraphics();
        graphics.drawImage(image, 0, 0, Color.WHITE, null);
        graphics.dispose();
        for (int x = 0; x < monochromeImage.getWidth(); x++) {
            for (int y = 0; y < monochromeImage.getHeight(); y++) {
                int rgb = monochromeImage.getRGB(x, y);
                int gray = (rgb & 0xFF); // Extract grayscale value
                monochromeImage.setRGB(x, y, gray < 128 ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
            }
        }
        return monochromeImage;
    }

    /**
     * Converts a monochrome image to a ZPL command.
     */
    private static String convertImageToZpl(BufferedImage image, int page) {
        StringBuilder zpl = new StringBuilder();
        zpl.append("^XA\n"); // Start ZPL
        zpl.append("^FO0,0\n"); // Position
        zpl.append("^GFA,"); // ZPL Image Command

        int widthBytes = (image.getWidth() + 7) / 8;
        int totalBytes = widthBytes * image.getHeight();
        zpl.append(totalBytes).append(",").append(totalBytes).append(",").append(widthBytes).append(",");

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < widthBytes * 8; x += 8) {
                int byteValue = 0;
                for (int bit = 0; bit < 8; bit++) {
                    int pixelX = x + bit;
                    if (pixelX < image.getWidth() && image.getRGB(pixelX, y) == -1) {
                        byteValue |= (1 << (7 - bit)); // White pixel
                    }
                }
                zpl.append(String.format("%02X", byteValue));
            }
            zpl.append("\n");
        }

        zpl.append("^XZ"); // End ZPL
        return zpl.toString();
    }
}