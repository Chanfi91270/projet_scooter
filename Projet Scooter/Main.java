import src.Graphique.*;
import src.Modele.*;

import javax.swing.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        // creation des scooters
        Scooter scooter1 = new Scooter("Yamaha", 1, 1000);
        Scooter scooter2 = new Scooter("Peugeot", 2, 2000);
        Scooter scooter3 = new Scooter("Honda", 3, 500);
        Scooter scooter4 = new Scooter("Ford", 4, 570);
        Scooter scooter5 = new Scooter("Kawasaki", 5, 1000);
        Scooter scooter6 = new Scooter("Kawasaki", 6, 1800);

        // creation des types de scooters
        Type_Scooter typeElec = new Type_Scooter("electrique", 50, scooter1, null);
        Type_Scooter typeNonElec125 = new Type_Scooter("non electrique", 125, scooter2, null);
        Type_Scooter typeNonElec250 = new Type_Scooter("non electrique", 250, scooter3, null);

        // creation des permis
        Permis permisA = new Permis("A");
        Permis permisB = new Permis("B");
        Permis permisA1 = new Permis("A1");

        // association des permis aux types de scooters
        typeElec.setPermis(permisB);
        typeNonElec125.setPermis(permisA);
        typeNonElec250.setPermis(permisA1);

        // association des types aux scooters
        scooter1.ajouterTypeScooter(typeElec);
        scooter2.ajouterTypeScooter(typeNonElec125);
        scooter3.ajouterTypeScooter(typeNonElec250);
        scooter4.ajouterTypeScooter(typeNonElec125);
        scooter5.ajouterTypeScooter(typeNonElec250);
        scooter6.ajouterTypeScooter(typeNonElec250);

        // creation du magasin
        Magasin_Scooter magasin = new Magasin_Scooter("ScootShop", "123 Rue du Scooter");

        // ajout des scooters au magasin
        magasin.ajouterScooter(scooter1);
        magasin.ajouterScooter(scooter2);
        magasin.ajouterScooter(scooter3);
        magasin.ajouterScooter(scooter4);
        magasin.ajouterScooter(scooter5);
        magasin.ajouterScooter(scooter6);

        // creation des clients
        Client client1 = new Client("Dupont", "Jean", 1, "0601020304");
        Client client2 = new Client("Medja", "Kayne", 2, "0787859324");
        Client client3 = new Client("Moha", "Koumo", 3, "0611223344");
        Client client4 = new Client("Doums", "Dylan", 4, "0721324354");

        // ajout des clients au magasin
        magasin.ajouterClient(client1);
        magasin.ajouterClient(client2);
        magasin.ajouterClient(client3);
        magasin.ajouterClient(client4);

        // preparation des dates pour les locations
        Calendar cal = Calendar.getInstance();
        Date dateDebut = cal.getTime();

        cal.add(Calendar.DAY_OF_MONTH, 3);
        Date dateFin = cal.getTime();

        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.DAY_OF_MONTH, -2);
        Date dateDebut2 = cal2.getTime();

        cal2.add(Calendar.DAY_OF_MONTH, 1);
        Date dateFin2 = cal2.getTime();

        Calendar cal3 = Calendar.getInstance();
        cal3.add(Calendar.DAY_OF_MONTH, -1);
        Date dateDebut3 = cal3.getTime();

        cal3.add(Calendar.DAY_OF_MONTH, 2);
        Date dateFin3 = cal3.getTime();

        Date dateRetour3 = new Date();

        // creation des locations et reservation des scooters
        Location location1 = new Location(Location.generateIdLocation(), dateDebut, dateFin, 0.0);
        location1.reserver(client1, scooter1, dateDebut, dateFin);

        Location location2 = new Location(Location.generateIdLocation(), dateDebut2, dateFin2, 0.0);
        location2.reserver(client2, scooter2, dateDebut2, dateFin2);

        Location location3 = new Location(Location.generateIdLocation(), dateDebut, dateFin, 0.0);
        location3.reserver(client3, scooter3, dateDebut, dateFin);

        // lancement de la fenetre principale
        SwingUtilities.invokeLater(() -> {
            new FenetrePrincipale(magasin);
        });
    }
}
