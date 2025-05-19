package src.Modele;
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

public double calculerFraisSup(Date datePrevueRetour, Date dateRetour, String etatScooter) {
    long ecartMillis = dateRetour.getTime() - datePrevueRetour.getTime();
    long joursRetard = ecartMillis / (1000 * 60 * 60 * 24);
    double frais = 0;

    if (joursRetard > 0) {
        frais += joursRetard * 10; // 10 € par jour de retard
    }

    if ("peu endommagé".equalsIgnoreCase(etatScooter)) {
        frais += 150; // 150 € si peu endommagé
    } else if ("très endommagé".equalsIgnoreCase(etatScooter)) {
        frais += 250; // 250 € si très endommagé
    }

    return frais;
}


public void mettreAJourDateFinLocation(Location location) {
    if (location != null && this.date_retour != null) {
        location.setdate_fin(this.date_retour);
    }
}



}