package src.Modele;
import java.io.*;
import java.util.*;


/**
 * 
 */
public class Location {

    public int id_location;
    public Date date_debut;
    public Date date_fin;
    public Double prix;

    Client client;
    Retour retour;
    Scooter scooter;

    public Location(int idlocation, Date datedebut, Date datefin, Double prix) {
        this.id_location = idlocation;
        this.date_debut = datedebut;
        this.date_fin = datefin;
        this.prix = prix;
    }

    public Location(int idlocation, Date datedebut, Date datefin, Double prix, Client client, Retour retour,
            Scooter scooter) {
        this.id_location = idlocation;
        this.date_debut = datedebut;
        this.date_fin = datefin;
        this.prix = prix;
        this.client = client;
        this.retour = retour;
        this.scooter = scooter;
    }

    public void setid_location(int newidlocation) {
        id_location = newidlocation;
    }

    public void setdate_debut(Date newdatedebut) {
        date_debut = newdatedebut;
    }

    public void setdate_fin(Date newdatefin) {
        date_fin = newdatefin;
    }

    public void setprix(Double newprix) {
        prix = newprix;
    }

    public void setclient(Client newclient) {
        client = newclient;
    }

    public void setretour(Retour newretour) {
        retour = newretour;
    }

    public void setscooter(Scooter newscooter) {
        scooter = newscooter;
    }

    public int getid_location() {
        return id_location;
    }

    public Date getdate_debut() {
        return date_debut;
    }

    public Date getdate_fin() {
        return date_fin;
    }

    public Double getprix() {
        return prix;
    }

    public Client getclient() {
        return client;
    }

    public Retour getretour() {
        return retour;
    }

    public Scooter getscooter() {
        return scooter;
    }

    public int calculerDuree() {
        long differenceMillis = date_fin.getTime() - date_debut.getTime();
        long jours = differenceMillis / (1000 * 60 * 60 * 24);
        return (int) jours;
    }

    public void afficherDetails() {
        System.out.println("ID Location : " + id_location);
        System.out.println("Client : " + client.nom + " " + client.prenom);
        System.out.println("Scooter ID : " + scooter.getId());
        System.out.println("Date début : " + date_debut);
        System.out.println("Date fin : " + date_fin);
        System.out.println("Prix : " + prix + " €");
        if (retour != null) {
            System.out.println("Retour : " + retour.etat);
            System.out.println("Frais supplémentaires : " + retour.frais_retour + " €");
            System.out.println("Kilométrage au retour : " + retour.kilometre_final + " km");
            System.out.println("Date de retour : " + retour.date_retour);
        }
    }

    public boolean estLocationTerminee() {
        Date currentDate = new Date();
        return currentDate.after(date_fin);
    }

    public double calculerPrixLocation(Date dateDebut, Date dateFin) {
        // Calculer la durée en jours
        long differenceMillis = dateFin.getTime() - dateDebut.getTime();
        long jours = differenceMillis / (1000 * 60 * 60 * 24);

        // Si la durée est inférieure à 1 jour, on considère 1 jour minimum
        if (jours < 1) {
            jours = 1;
        }

        // Tarifs par jour
        double prixParJour = 30.0; // Exemple de prix par jour

        // Calculer le prix total
        double prixTotal = jours * prixParJour;

        return prixTotal;
    }

    public static int compteurLocation = 1;

    public static int generateIdLocation() {
        return compteurLocation++;
    }

    public void reserver(Client client, Scooter scooter, Date dateDebut, Date dateFin) {
        // Vérification si le scooter est disponible pour la période
        if (scooter.estDisponible(dateDebut, dateFin)) {
            // Créer une nouvelle location avec l'ID généré automatiquement
            Location location = new Location(generateIdLocation(), dateDebut, dateFin, 0.0);

            // Calcul du prix basé sur la durée
            double prixTotal = location.calculerPrixLocation(dateDebut, dateFin);
            location.setprix(prixTotal);

            // Associer les objets Client et Scooter à la Location
            location.setclient(client);
            location.setscooter(scooter);

            // Ajouter la location à la liste des locations du client et du scooter
            client.ajouterLocation(location);
            scooter.ajouterLocation(location);

            // Afficher la confirmation de la réservation
            System.out.println("Réservation réussie pour le scooter " + scooter.getMarque() +
                    " du " + dateDebut + " au " + dateFin +
                    ". Prix total : " + location.getprix() + " €");

        } else {
            System.out.println("Désolé, ce scooter n'est pas disponible aux dates demandées.");
        }
    }


}
