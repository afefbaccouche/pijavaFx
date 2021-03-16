/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chapter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;
import controller.login.LoginController;
import entity.Centre;
import entity.ReservationCentre;
import entity.User;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author esprit
 */
public class PDFUtils {

    public static void createPdf(Image qrView) throws BadElementException, IOException {
        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("src/pdf/HelloWorld.pdf"));
            document.open();
            document.add(new Paragraph("A Hello World PDF document."));

            //Add Image
            Image image1 = Image.getInstance("src/Admin/images/back.png");
            //Fixed Positioning
            image1.setAbsolutePosition(100f, 550f);
            //Scale to new height and new width of image
            image1.scaleAbsolute(200, 200);
            //Add to document
            document.add(image1);

            //Add ordered list
            List orderedList = new List(List.ORDERED);
            orderedList.add(new ListItem("Item 1"));
            orderedList.add(new ListItem("Item 2"));
            orderedList.add(new ListItem("Item 3"));
            document.add(orderedList);

            qrView.scaleAbsolute(100, 100);
            document.add(qrView);

            document.close();
            writer.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void runPdf(String pdfPath) {
        /*
        RUN PDF FILE
         */
        try {
            File file = new File(pdfPath);
            Desktop.getDesktop().open(file);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {

            if ((new File(pdfPath)).exists()) {

                Process p = Runtime
                        .getRuntime()
                        .exec("rundll32 url.dll,FileProtocolHandler " + pdfPath);
                p.waitFor();

            } else {

                System.out.println("File is not exists");

            }

            System.out.println("Done");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void createStyledPDF(Centre centre, User client, ReservationCentre reservation, Image qrCode, String idDocument) {
        Font blueFont = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, new CMYKColor(255, 0, 0, 0));
        Font redFont = FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD, new CMYKColor(0, 255, 0, 0));
        Font yellowFont = FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD, new CMYKColor(0, 0, 255, 0));
        Document document = new Document();

        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("src/pdf/" + idDocument));
            document.open();

            //Create chapter and sections
            Paragraph chapterTitle = new Paragraph("Reservation du centre: " + centre.getNom(), yellowFont);
            Chapter chapter1 = new Chapter(chapterTitle, 1);
            document.add(chapter1);

            Paragraph paragraphUser = new Paragraph("Nom Client: ", redFont);
            document.add(paragraphUser);
            document.add(new Paragraph(client.getNomUser() + " " + client.getPrenomUser()));

            Paragraph paragraphOne = new Paragraph("Date Debut: ", redFont);
            document.add(paragraphOne);
            document.add(new Paragraph(reservation.getDateD().toString()));

            Paragraph paragraphTwo = new Paragraph("Nombre du jour: ", redFont);
            document.add(paragraphTwo);
            document.add(new Paragraph(Integer.toString(reservation.getNombreJ())));

            Paragraph paragraphThree = new Paragraph("Prix: ", redFont);
            document.add(paragraphThree);
            document.add(new Paragraph(Float.toString(reservation.getNombreJ() * centre.getPrix()) + " DT"));

            Paragraph paragraphAddress = new Paragraph("Adresse Centre: ", redFont);
            document.add(paragraphAddress);
            document.add(new Paragraph(centre.getAddress()));

            Paragraph paragraphTel = new Paragraph("Telephone Centre: ", redFont);
            document.add(paragraphTel);
            document.add(new Paragraph(Integer.toString(centre.getTelephone())));

            qrCode.scaleAbsolute(100, 100);
            document.add(qrCode);

            document.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
