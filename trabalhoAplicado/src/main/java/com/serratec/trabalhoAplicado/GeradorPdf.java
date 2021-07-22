
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class GeradorPdf {

    public static void main(String[] args) {
           
          Document document = new Document();
          try {

              PdfWriter.getInstance(document, new FileOutputStream("C:\\PDF_Trabalho.pdf"));
              document.open();

              
              document.add(new Paragraph("Gerando o PDF - Java"));
    }
          catch(DocumentException de) {
              System.err.println(de.getMessage());
          }
          catch(IOException ioe) {
              System.err.println(ioe.getMessage());
          }
          document.close();
      }
    }