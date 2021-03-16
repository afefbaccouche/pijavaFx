/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Consommation;

/**
 *
 * @author soumaya ch
 */
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;

public class PDF {
    public static void main(String...args){
        Document document = new Document();
        try{
            PdfWriter.getInstance(document, new FileOutputStream("HelloWorld.pdf"));
            document.open();
            Paragraph p1 = new Paragraph("Hello World!");
            Paragraph p2 = new Paragraph("Examsmyantra.com is committed to provide you valuable information and tutorials on various technologies.");
	    document.add(p1);
            document.add(p2);
        }
        catch(Exception e){
            System.out.println(e);
        }
        document.close();
    }
}
