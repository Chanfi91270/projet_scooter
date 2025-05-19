package src.Modele;

import java.io.*;
import java.util.*;
/**
 * 
 */
public class Scooter {

    public String Marque;
    public int id_scoot;
    public int kilometrage;

    Magasin_Scooter magasin_scooter;
    Vector<Type_Scooter> liste_type_scooter = new Vector<Type_Scooter>();
    Vector<Location> liste_location = new Vector<Location>();

    public Scooter(String marque, int id, int km) {
        Marque = marque;
        id_scoot = id;
        kilometrage = km;
    }

    public Scooter(String marque, int id, int km, Magasin_Scooter magasin) {
        Marque = marque;
        id_scoot = id;
        kilometrage = km;
        magasin_scooter = magasin;
    }

    public void setMarque(String newmarque) {
        Marque = newmarque;
    }

    public void setId(int newid) {
        id_scoot = newid;
    }

    public void setKm(int newkm) {
        kilometrage = newkm;
    }

    public void setmagasin_scooter(Magasin_Scooter magasin) {
        magasin_scooter = magasin;
    }

    public void settype(Vector<Type_Scooter> type_scooter) {
        liste_type_scooter = type_scooter;
    }

    public void setloc(Vector<Location> location) {
        liste_location = location;
    }

    public void ajouterTypeScooter(Type_Scooter type) {
        liste_type_scooter.add(type);
    }

    public void ajouterLocation(Location loc) {
        liste_location.add(loc);
    }

    public void supprimerLocation(Location loc) {
        liste_location.remove(loc);
    }

    public String getMarque() {
        return Marque;
    }

    public int getId() {
        return id_scoot;
    }

    public int getKm() {
        return kilometrage;
    }

    public Magasin_Scooter getMagasin() {
        return magasin_scooter;
    }

    public Vector<Type_Scooter> gettype() {
        return liste_type_scooter;
    }

    public Vector<Location> getloc() {
        return liste_location;
    }

    public boolean estDisponible(Date dateDebut, Date dateFin) {
        for (Location loc : liste_location) {
            if ((dateDebut.before(loc.date_fin) && dateFin.after(loc.date_debut))) {
                return false;
            }
        }
        return true;
    }

    public boolean estActuellementDisponible() {
        Date maintenant = new Date();
        for (Location loc : liste_location) {
            if (!maintenant.before(loc.date_debut) && !maintenant.after(loc.date_fin)) {
                return false;
            }
        }
        return true;
    }

    public void afficherHistoriqueLocations() {
        if (liste_location.isEmpty()) {
            System.out.println("Aucune location enregistrée.");
        } else {
            for (Location location : liste_location) {
                System.out.println("ID Location : " + location.id_location);
                System.out.println("Client : " + location.getclient().nom + " " + location.getclient().prenom);
                System.out.println("Scooter ID : " + location.getscooter().getId());
                System.out.println("Date début : " + location.getdate_debut());
                System.out.println("Date fin : " + location.getdate_fin());
                System.out.println("Prix : " + location.getprix() + " €");
                System.out.println("------------");
            }
        }
    }

}