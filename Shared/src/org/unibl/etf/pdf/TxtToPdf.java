package org.unibl.etf.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import static org.unibl.etf.logger.MyLogger.logger;
import java.io.*;
import java.util.logging.Level;

public class TxtToPdf {
    public static void convertToPDF(String txtFileName, String pdfFileName) {
        String txtFile = txtFileName;  
        String pdfFile = pdfFileName; 

        try (PDDocument document = new PDDocument();
             BufferedReader br = new BufferedReader(new FileReader(txtFile))) {

            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 750);

            String line;
            int yOffset = 0;

            while ((line = br.readLine()) != null) {
                contentStream.showText(line);
                contentStream.newLineAtOffset(0, -15);
                yOffset += 15;

                if (yOffset > 700) {
                    contentStream.endText();
                    contentStream.close();

                    page = new PDPage();
                    document.addPage(page);
                    contentStream = new PDPageContentStream(document, page);
                    contentStream.setFont(PDType1Font.HELVETICA, 12);
                    contentStream.beginText();
                    contentStream.newLineAtOffset(50, 750);
                    yOffset = 0;
                }
            }

            contentStream.endText();
            contentStream.close();

            document.save(pdfFile);
            System.out.println("PDF uspješno kreiran: " + pdfFile);

        } catch (IOException e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "Exception: ", e);
        }
    }
}
