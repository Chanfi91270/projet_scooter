package Modele;
import java.io.*;
import java.util.*;
/**
 * 
 */
public class Retour {
    public String etat;
    public Double frais_retour;
    public int kilometre_final;
    public Date date_retour;

    Location location;

    public Retour(String etat, Double frais_retour, int km_final, Date date_retour) {
        this.etat = etat;
        this.frais_retour = frais_retour;
        this.kilometre_final = km_final;
        this.date_retour = date_retour;
    }

    public Retour(String etat, Double frais_retour, int km_final, Date date_retour, Location location) {
        this.etat = etat;
        this.frais_retour = frais_retour;
        this.kilometre_final = km_final;
        this.date_retour = date_retour;
        this.location = location;
    }

    public void setetat(String newetat) {
        this.etat = newetat;
    }

    public void setfrais_retour(Double newfrais_retour) {
        this.frais_retour = newfrais_retour;
    }

    public void setkilometre_final(int newkm_final) {
        this.kilometre_final = newkm_final;
    }

    public void setdate_retour(Date newdate_retour) {
        this.date_retour = newdate_retour;
    }

    public void setlocation(Location newlocation) {
        this.location = newlocation;
    }

    public String getetat() {
        return etat;
    }

    public Double getfrais_retour() {
        return frais_retour;
    }

    public int getkilometre_final() {
        return kilometre_final;
    }

    public Date getdate_retour() {
        return date_retour;
    }

    public Location getlocation() {
        return location;
    }

    public double calculerFraisretard(Date datePrevueRetour, String etatScooter) {
        long ecartMillis = date_retour.getTime() - datePrevueRetour.getTime();
        long joursRetard = ecartMillis / (1000 * 60 * 60 * 24);
        double frais = 0;

        if (joursRetard > 0) {
            frais += joursRetard * 10; // 10 € par jour de retard
        }

        if (!etatScooter.equals("bon")) {
            frais += 20; // 20 € si le scooter est abîmé
        }

        return frais;
    }

    public void retournerScooter(Location location, String etat, double frais, int km, Date dateRetour) {
        Retour retour = new Retour(etat, frais, km, dateRetour);
        retour.setlocation(location);
        location.setretour(retour);

        Scooter scooter = location.getscooter();
        int nouveauKm = scooter.getKm() + km;
        scooter.setKm(nouveauKm);
    }

    public double calculerFraisSupp(Date datePrevueRetour, String etatScooter) {
        Scanner scanner = new Scanner(System.in);
        String etat;
        double frais = 0;

        // Demande à l'utilisateur de saisir l'état du scooter
        while (true) {
            System.out.println("Entrez l'état du scooter (bon, peu endommagé, très endommagé) : ");
            etat = scanner.nextLine(); // Convertir la saisie en minuscules pour éviter les erreurs de casse

            if (etat.equals("bon") || etat.equals("peu endommagé") || etat.equals("très endommagé")) {
                break; // Si l'état est valide, on sort de la boucle
            } else {
                System.out.println("État invalide. Veuillez entrer 'bon', 'peu endommagé' ou 'très endommagé'.");
            }
        }

        // Calcul des frais supplémentaires en fonction de l'état
        if (etat.equals("bon")) {
            frais = 0; // Aucun frais si l'état est bon
        } else if (etat.equals("peu endommagé")) {
            frais = 20; // 20 € pour un scooter peu endommagé
        } else if (etat.equals("très endommagé")) {
            frais = 50; // 50 € pour un scooter très endommagé
        }

        // Retourner les frais calculés
        return frais;
    }

}