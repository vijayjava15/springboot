//package com.springboot3.springboot3.model;
//
//import com.itextpdf.kernel.geom.Rectangle;
//import com.itextpdf.kernel.pdf.PdfDocument;
//import com.itextpdf.kernel.pdf.PdfReader;
//import com.itextpdf.kernel.pdf.PdfWriter;
//import com.itextpdf.kernel.pdf.canvas.parser.PdfCanvasProcessor;
//import com.itextpdf.kernel.pdf.canvas.parser.listener.ITextExtractionStrategy;
//import com.itextpdf.kernel.pdf.canvas.parser.listener.SimpleTextExtractionStrategy;
//import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
//import com.itextpdf.layout.Document;
//import com.itextpdf.layout.element.Image;
//import com.itextpdf.layout.properties.HorizontalAlignment;
//
//import java.io.File;
//import java.io.IOException;
//
//public class dd {
//
//    public static void main(String[] args) {
//        String src = "C:\\Users\\BZF26\\Downloads\\794794540768_438399.pdf";
//        String dest = "C:\\Users\\BZF26\\Downloads\\794794540768_438399ss.pdf";
//
//        try (PdfReader reader = new PdfReader(src);
//             PdfWriter writer = new PdfWriter(dest);
//             PdfDocument pdfDoc = new PdfDocument(reader, writer)) {
//
//            int numOfPages = pdfDoc.getNumberOfPages();
//
//            // Process each page
//            for (int i = 1; i <= numOfPages; i++) {
//                Rectangle leftSideRegion = new Rectangle(0, 0, 200, pdfDoc.getPage(i).getPageSize().getHeight());
//
//                PdfFormXObject pageCopy = pdfDoc.getPage(i).copyAsFormXObject(pdfDoc);
//                Document document = new Document(pdfDoc);
//
//                Image image = new Image(pageCopy);
//                image.setFixedPosition(leftSideRegion.getX(), leftSideRegion.getY());
//                image.setHorizontalAlignment(HorizontalAlignment.LEFT);
//
//                // Crop the content
//                image.setAutoScale(true);
//                document.add(image);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
