package sample.Models;

import com.sun.javafx.font.FontFactory;

import javax.swing.text.Document;
import java.io.FileOutputStream;

public class pdf {

    public void saveToPdf(String output){
        Document document = new Document();
        PdfWriter.getInstance(document,new FileOutputStream("save.pdf"));
        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER,16,BaseColor.BLACK);
        Chunk chunk = new Chunk(output,font);
        document.add(chunk);
        document.close();
    }
}
