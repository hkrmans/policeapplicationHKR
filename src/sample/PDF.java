package sample;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import sample.Models.WantedCriminal;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class PDF {

    public void SaveToPdf(ArrayList<WantedCriminal> wantedCriminals) throws FileNotFoundException, DocumentException {
        Document document = new Document(PageSize.A4,50,50,50,50);

        PdfWriter.getInstance(document, new FileOutputStream("WantedCriminals.pdf"));
        document.open();

        for (int i = 0; i < wantedCriminals.size(); i++) {
            document.add(new Paragraph(wantedCriminals.get(i).getFirstName() + " | " +
                    wantedCriminals.get(i).getLastName() + " | " + wantedCriminals.get(i).getCivicNumber() +
                    " | Rank: " + wantedCriminals.get(i).getRanking() + " | Bounty: " + wantedCriminals.get(i).getBounty() + " | ID: " +
                    wantedCriminals.get(i).getWantedId()));
        }
        document.close();
    }
}
