package src.Modele;
import java.io.*;
import java.util.*;
/**
 * 
 */
public class Client {

    public String nom;
    public String prenom;
    public int id_client;
    public String num_tel;

    Vector<Location> liste_location = new Vector<Location>();
    Vector<Magasin_Scooter> liste_magasin_scooter = new Vector<Magasin_Scooter>();
    Vector<Permis> liste_permis = new Vector<Permis>();

    public Client(String nom, String prenom, int id, String numero) {
        this.nom = nom;
        this.prenom = prenom;
        this.id_client = id;
        this.num_tel = numero;
    }

    public void setNom(String newnom) {
        nom = newnom;
    }

    public void setPrenom(String newprenom) {
        prenom = newprenom;
    }

    public void setId(int newid) {
        id_client = newid;
    }

    public void setNumTel(String newnumtel) {
        num_tel = newnumtel;
    }

    public void setloc(Vector<Location> lst_loc) {
        liste_location = lst_loc;
    }

    public void setmagasin(Vector<Magasin_Scooter> magasin) {
        liste_magasin_scooter = magasin;
    }

    public void setpermis(Vector<Permis> permis) {
        liste_permis = permis;
    }

    public void ajouterLocation(Location loc) {
        liste_location.add(loc);
    }

    public void ajouterMagasin(Magasin_Scooter magasin) {
        liste_magasin_scooter.add(magasin);
    }

    public void ajouterPermis(Permis permis) {
        liste_permis.add(permis);
    }

    public void supprimerLocation(Location loc) {
        liste_location.remove(loc);
    }

    public void supprimerMagasin(Magasin_Scooter magasin) {
        liste_magasin_scooter.remove(magasin);
    }

    public void supprimerPermis(Permis permis) {
        liste_permis.remove(permis);
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getId() {
        return id_client;
    }

    public String getNumTel() {
        return num_tel;
    }

    public Vector<Location> getloc() {
        return liste_location;
    }

    public Vector<Magasin_Scooter> getmagasin() {
        return liste_magasin_scooter;
    }

    public Vector<Permis> getpermis() {
        return liste_permis;
    }

    public void infoLocation() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez l'ID de la location recherchée : ");
        int idRecherche = scanner.nextInt();

        boolean trouve = false;

        for (int i = 0; i < liste_location.size(); i++) {
            Location l = liste_location.get(i); // on récupère la location à l'indice i

            if (l.getid_location() == idRecherche) {
                System.out.println("Date de début: " + l.getdate_debut());
                System.out.println("Date de fin: " + l.getdate_fin());
                System.out.println("Prix: " + l.getprix());
                trouve = true;
                break; // on arrête la boucle dès qu'on trouve
            }
        }

        if (trouve == false) {
            System.out.println("Location non trouvée.");
            scanner.close();
        }
    }

    public void afficherLocations() {
        if (liste_location.isEmpty()) {
            System.out.println("Aucune location trouvée.");
        } else {
            System.out.println("Historique des locations de " + nom + " " + prenom + ":");
            for (Location location : liste_location) {
                System.out.println(
                        "ID Location: " + location.getid_location() + ", Date de début: " + location.getdate_debut() +
                                ", Date de fin: " + location.getdate_fin() + ", Prix: " + location.getprix());
            }
        }
    }

    public void afficherDetails() {
        System.out.println("Nom: " + nom);
        System.out.println("Prénom: " + prenom);
        System.out.println("ID Client: " + id_client);
        System.out.println("Numéro de téléphone: " + num_tel);
        System.out.println("Liste des locations:");
        afficherLocations();
    }

    public void annulerReservation(int idLocation) {
        boolean trouve = false;

        for (int i = 0; i < liste_location.size(); i++) {
            Location location = liste_location.get(i);

            // Vérifier si l'ID de la location correspond à celui passé en paramètre
            if (location.getid_location() == idLocation) {
                // Retirer la location de la liste des locations du client
                liste_location.remove(i);
                System.out.println("Réservation annulée pour l'ID Location: " + idLocation);
                trouve = true;
                break;
            }
        }

        if (!trouve) {
            System.out.println("Aucune réservation trouvée pour l'ID Location: " + idLocation);
        }
    }
}