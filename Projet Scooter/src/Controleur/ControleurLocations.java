package src.Controleur;

import src.Modele.*;
import javax.swing.*;
import java.util.*;

public class ControleurLocations {

    public Magasin_Scooter magasin;

    public ControleurLocations(Magasin_Scooter magasin) {
        this.magasin = magasin;
    }

    // Recherche une location par son ID, retourne null si pas trouvée
    public Location rechercherLocationParId(int id) {
        // Comme les locations sont stockées dans les scooters,
        // on parcourt tous les scooters et leurs locations
        for (Scooter scooter : magasin.getscooter()) {
            for (Location loc : scooter.getloc()) {
                if (loc.getid_location() == id) {
                    return loc;
                }
            }
        }
        return null;
    }

    // Ajoute une location : ajoute aussi la location au client et scooter
    public boolean ajouterLocation(Location location) {
        if (location == null) return false;
        // Vérifier si ID existe déjà
        if (rechercherLocationParId(location.getid_location()) != null) {
            return false; // ID déjà utilisé
        }
        // Ajouter la location au client et scooter
        Client client = location.getclient();
        Scooter scooter = location.getscooter();
        if (client == null || scooter == null) return false;

        client.ajouterLocation(location);
        scooter.ajouterLocation(location);
        return true;
    }

    // Supprimer une location (supprime aussi des listes client et scooter)
    public boolean supprimerLocation(Location location) {
        if (location == null) return false;
        Client client = location.getclient();
        Scooter scooter = location.getscooter();
        if (client == null || scooter == null) return false;

        client.supprimerLocation(location);
        scooter.supprimerLocation(location);
        return true;
    }

    // Louer un scooter (demande les infos et crée la location)
    public boolean louerScooter(JFrame parent) {
        try {
            // --- Choix ID Location (unique) ---
            int idLocation;
            while (true) {
                String strIdLocation = JOptionPane.showInputDialog(parent, "Entrez l'ID de la location (doit être unique) :");
                if (strIdLocation == null) return false; // annulation
                try {
                    idLocation = Integer.parseInt(strIdLocation);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(parent, "ID invalide, entrez un nombre entier.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    continue;
                }
                if (rechercherLocationParId(idLocation) == null) break; // ok unique
                else JOptionPane.showMessageDialog(parent, "Cet ID existe déjà, choisissez-en un autre.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }

            // --- Choix ID scooter ---
            Scooter scooter;
            while (true) {
                String strIdScooter = JOptionPane.showInputDialog(parent, "Entrez l'ID du scooter à louer :");
                if (strIdScooter == null) return false; // annulation
                int idScooter = Integer.parseInt(strIdScooter);
                scooter = magasin.getScooterById(idScooter);
                if (scooter == null) {
                    JOptionPane.showMessageDialog(parent, "Scooter non trouvé, réessayez.", "Erreur", JOptionPane.ERROR_MESSAGE);
                } else {
                    break;
                }
            }

            // --- Choix ID client ---
            Client client;
            while (true) {
                String strIdClient = JOptionPane.showInputDialog(parent, "Entrez l'ID du client qui loue :");
                if (strIdClient == null) return false; // annulation
                int idClient = Integer.parseInt(strIdClient);
                client = magasin.getClientById(idClient);
                if (client == null) {
                    JOptionPane.showMessageDialog(parent, "Client non trouvé, réessayez.", "Erreur", JOptionPane.ERROR_MESSAGE);
                } else {
                    break;
                }
            }

            // --- Date début = maintenant ---
            Date dateDebut = new Date();

            // --- Durée en jours ---
            int nbJours;
            while (true) {
                String strNbJours = JOptionPane.showInputDialog(parent, "Combien de jours souhaitez-vous louer ce scooter ?");
                if (strNbJours == null) return false; // annulation
                try {
                    nbJours = Integer.parseInt(strNbJours);
                    if (nbJours < 1) {
                        JOptionPane.showMessageDialog(parent, "Veuillez entrer un nombre supérieur ou égal à 1.", "Erreur", JOptionPane.ERROR_MESSAGE);
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(parent, "Entrée invalide, entrez un nombre entier.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }

            // --- Calcul date fin ---
            Calendar cal = Calendar.getInstance();
            cal.setTime(dateDebut);
            cal.add(Calendar.DAY_OF_MONTH, nbJours);
            Date dateFin = cal.getTime();

            // --- Calcul prix ---
            double prix = new Location(0, dateDebut, dateFin, 0.0).calculerPrixLocation(dateDebut, dateFin);

            // --- Création location ---
            Location nouvelleLocation = new Location(idLocation, dateDebut, dateFin, prix, client, null, scooter);

            // --- Vérifier dispo scooter ---
            if (!scooter.estDisponible(dateDebut, dateFin)) {
                JOptionPane.showMessageDialog(parent, "Le scooter n'est pas disponible sur cette période.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            // --- Ajouter location ---
            if (ajouterLocation(nouvelleLocation)) {
                JOptionPane.showMessageDialog(parent, "Location créée avec succès !\n" +
                        "Client : " + client.nom + "\n" +
                        "Scooter : " + scooter.getMarque() + "\n" +
                        "Durée : " + nbJours + " jours\n" +
                        "Prix : " + prix + " €");
                return true;
            } else {
                JOptionPane.showMessageDialog(parent, "Erreur lors de l'ajout de la location.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return false;
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(parent, "Entrée invalide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

}
