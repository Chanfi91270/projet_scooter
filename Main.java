import Modele.*;
import Graphique.*;
import Controlleur.*;

import javax.swing.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        // 1. Création des scooters
        Scooter scooter1 = new Scooter("Yamaha", 1, 1000);
        Scooter scooter2 = new Scooter("Peugeot", 2, 2000);
        Scooter scooter3 = new Scooter("Honda", 3, 500);

        // 2. Création des types de scooters
        Type_Scooter typeElec = new Type_Scooter("électrique", 50, scooter1, null);
        Type_Scooter typeNonElec125 = new Type_Scooter("non électrique", 125, scooter2, null);
        Type_Scooter typeNonElec250 = new Type_Scooter("non électrique", 250, scooter3, null);

        // 3. Création des objets Permis
        Permis permisA = new Permis("A");
        Permis permisB = new Permis("B");
        Permis permisA1 = new Permis("A1");

        // 4. Association des permis requis aux types de scooter
        typeElec.setPermis(permisB); // Permis "A" pour le scooter électrique
        typeNonElec125.setPermis(permisA); // Permis "A" pour le scooter 125cc
        typeNonElec250.setPermis(permisA1); // Permis "B" pour le scooter 50cc

        // Association des types aux scooters
        scooter1.ajouterTypeScooter(typeElec);
        scooter2.ajouterTypeScooter(typeNonElec125);
        scooter3.ajouterTypeScooter(typeNonElec250);

        // 5. Création du magasin
        Magasin_Scooter magasin = new Magasin_Scooter("ScootShop", "123 Rue du Scooter");

        // Ajout des scooters à la liste
        magasin.ajouterScooter(scooter1);
        magasin.ajouterScooter(scooter2);
        magasin.ajouterScooter(scooter3);

        // 6. Création d’un client
        Client client1 = new Client("Dupont", "Jean", 1, "0601020304");

        // 7. Dates pour la location
        Calendar cal = Calendar.getInstance();
        Date dateDebut = cal.getTime();
        cal.add(Calendar.DAY_OF_MONTH, 3);
        Date dateFin = cal.getTime();

        // 8. Création de la location et réservation du scooter1
        Location location1 = new Location(Location.generateIdLocation(), dateDebut, dateFin, 0.0);
        location1.reserver(client1, scooter1, dateDebut, dateFin);

        // Lancer la fenêtre principale de l'application
        SwingUtilities.invokeLater(() -> {
            new FenetrePrincipale(magasin); // Lancement de la fenêtre principale
        });
    }
}
