package src.Controleur;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import src.Modele.*;

public class ControleurRetours {

    public Magasin_Scooter magasin;

    public ControleurRetours(Magasin_Scooter magasin) {
        this.magasin = magasin;
    }

    // Cherche une location par son ID dans tous les scooters du magasin
    public Location trouverLocationParId(int idLocation) {
        for (Scooter scooter : magasin.getscooter()) {
            for (Location loc : scooter.getloc()) {
                if (loc.getid_location() == idLocation) {
                    return loc;
                }
            }
        }
        return null;
    }

    // Supprime la location du scooter qui la contient
    public boolean supprimerLocationDuMagasin(Location locationASupprimer) {
        for (Scooter scooter : magasin.getscooter()) {
            if (scooter.getloc().contains(locationASupprimer)) {
                scooter.getloc().remove(locationASupprimer);
                return true;
            }
        }
        return false;
    }

    // Méthode principale pour effectuer un retour
    public void effectuerRetour(JFrame parent) {
        try {
            // 1) Demande ID Location
            String idStr = JOptionPane.showInputDialog(parent, "Entrez l'ID de la location :", "Retour location", JOptionPane.QUESTION_MESSAGE);
            if (idStr == null) return; // annulation

            int idLocation = Integer.parseInt(idStr);

            // Recherche location
            Location location = trouverLocationParId(idLocation);
            if (location == null) {
                JOptionPane.showMessageDialog(parent, "Location introuvable pour l'ID " + idLocation, "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 2) Choix état scooter
            String[] etats = {"bon", "peu endommagé", "très endommagé"};
            String etat = (String) JOptionPane.showInputDialog(parent, "État du scooter :", "Retour location",
                    JOptionPane.QUESTION_MESSAGE, null, etats, etats[0]);
            if (etat == null) return; // annulation

            // 3) Date de retour effective (format français jj/MM/aaaa)
            String dateStr = JOptionPane.showInputDialog(parent, "Date de retour (format jj/MM/aaaa) :", "Retour location", JOptionPane.QUESTION_MESSAGE);
            if (dateStr == null) return; // annulation

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dateRetour;
            try {
                dateRetour = sdf.parse(dateStr);
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(parent, "Format de date invalide. Utilisez jj/MM/aaaa.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 4) Kilométrage parcouru pendant la location
            String kmParcourusStr = JOptionPane.showInputDialog(parent, "Kilométrage parcouru pendant la location :", "Retour location", JOptionPane.QUESTION_MESSAGE);
            if (kmParcourusStr == null) return; // annulation

            int kmParcourus;
            try {
                kmParcourus = Integer.parseInt(kmParcourusStr);
                if (kmParcourus < 0) {
                    JOptionPane.showMessageDialog(parent, "Le kilométrage parcouru ne peut pas être négatif.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(parent, "Kilométrage invalide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Calcul du kilométrage final = kilométrage de départ + kilométrage parcouru
            int kmDepart = location.getscooter().getKm(); // récupérer le km de départ du scooter
            int kmFinal = kmDepart + kmParcourus;

            // Met à jour le kilométrage du scooter
            location.getscooter().setKm(kmFinal);

            // 5) Calcul frais supplémentaires et création du retour
            Retour retour = new Retour(etat, 0.0, kmFinal, dateRetour, location);
            double fraisSup = retour.calculerFraisSup(location.getdate_fin(), dateRetour, etat);
            retour.setfrais_retour(fraisSup);

            // 6) Met à jour la date de fin de la location au retour réel
            retour.mettreAJourDateFinLocation(location);

            // 7) Associe le retour à la location
            location.setretour(retour);

            // 8) Supprime la location du scooter (libère le scooter)
            boolean supprime = supprimerLocationDuMagasin(location);
            if (!supprime) {
                JOptionPane.showMessageDialog(parent, "Erreur lors de la suppression de la location.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 9) Message récapitulatif
            JOptionPane.showMessageDialog(parent,
                    String.format("Retour effectué avec succès !\nFrais supplémentaires : %.2f €", fraisSup),
                    "Retour confirmé",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(parent, "L'ID de location doit être un nombre entier.", "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parent, "Erreur inattendue : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
