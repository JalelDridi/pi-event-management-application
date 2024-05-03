package tn.esprit.adminmicroservice.ServiceImp;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.adminmicroservice.Entities.StatusRessources;
import tn.esprit.adminmicroservice.Repository.RepositoryRessource;
import tn.esprit.adminmicroservice.Service.ServiceRessource;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
@Service
public class ServiceRessourceImpl implements ServiceRessource {

    @Autowired
    RepositoryRessource repositoryRessource;

    @Override
    public List<StatusRessources> findAllRessource(){
       return (repositoryRessource.findAll());
    }

    @Override
    public double calculateAvailabilityPercentage() {
        List<StatusRessources> allRessources = findAllRessource();
        int totalQuantity = 0;
        int totalReservations = 0;
        for (StatusRessources ressource : allRessources) {
            totalQuantity += ressource.getQuntite();
            totalReservations += ressource.getNbrReservation();
        }
        double availabilityPercentage = ((double)(totalQuantity - totalReservations) / totalQuantity) * 100;
        return availabilityPercentage;
    }
//fonction telechargement pdf


   /* public void generatePdf() {
        try {
            String filePath ="C:\\Users\\nadin\\Downloads";
            List<StatusRessources> ressourcesList= findAllRessource();
            PdfWriter writer = new PdfWriter(new File(filePath));
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            Table table = new Table(4); // Nombre de colonnes

            // Ajouter les en-têtes de colonne
            table.addCell(new Cell().add("ID Ressource").setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell().add("Type").setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell().add("Quantité").setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell().add("Nombre de Réservations").setTextAlignment(TextAlignment.CENTER));

            // Ajouter les données de la liste
            for (StatusRessources status : ressourcesList) {
                table.addCell(new Cell().add(String.valueOf(status.getId_Ressource())).setTextAlignment(TextAlignment.CENTER));
                table.addCell(new Cell().add(status.getType()).setTextAlignment(TextAlignment.CENTER));
                table.addCell(new Cell().add(String.valueOf(status.getQuntite())).setTextAlignment(TextAlignment.CENTER)); // Utiliser getQuntite() au lieu de getQuantite()
                table.addCell(new Cell().add(String.valueOf(status.getNbrReservation())).setTextAlignment(TextAlignment.CENTER));
            }

            document.add(table);
            document.close();

            System.out.println("Le fichier PDF a été généré avec succès !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/


}
