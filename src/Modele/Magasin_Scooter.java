package src.Modele;
import java.io.*;
import java.util.*;

/**
 * 
 */
public class Magasin_Scooter {

    public String nom;
    public String adresse;

    Vector<Client> liste_client = new Vector<Client>();
    Vector<Scooter> liste_scooter = new Vector<Scooter>();

    public Magasin_Scooter(String nom, String adresse) {
        this.nom = nom;
        this.adresse = adresse;
    }

    public void setNom(String newnom) {
        this.nom = newnom;
    }

    public void setAdresse(String newadresse) {
        this.adresse = newadresse;
    }

    public void setclient(Vector<Client> clients) {
        this.liste_client = clients;
    }

    public void setscooter(Vector<Scooter> scooters) {
        this.liste_scooter = scooters;
    }

    public void ajouterClient(Client client) {
        liste_client.add(client);
    }

    public void ajouterScooter(Scooter scooter) {
        liste_scooter.add(scooter);
    }

    public void supprimerClient(Client client) {
        liste_client.remove(client);
    }

    public void supprimerScooter(Scooter scooter) {
        liste_scooter.remove(scooter);
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public Vector<Client> getclient() {
        return liste_client;
    }

    public Vector<Scooter> getscooter() {
        return liste_scooter;
    }

    public void infoClient() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez l'ID du client recherché : ");
        int idRecherche = scanner.nextInt();

        boolean trouve = false;

        for (int i = 0; i < liste_client.size(); i++) {
            Client c = liste_client.get(i);

            if (c.getId() == idRecherche) {
                System.out.println("Nom: " + c.getNom());
                System.out.println("Prenom: " + c.getPrenom());
                System.out.println("Numéro de téléphone: " + c.getNumTel());
                trouve = true;
                break;
            }
        }

        if (!trouve) {
            System.out.println("Client non trouvé.");
        }
    }

    public void infoScooter() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez l'ID du scooter recherché : ");
        int idRecherche = scanner.nextInt();

        boolean trouve = false;

        for (int i = 0; i < liste_scooter.size(); i++) {
            Scooter s = liste_scooter.get(i);

            if (s.getId() == idRecherche) {
                // Affichage des informations des attributs du scooter
                System.out.println("ID du Scooter: " + s.getId());
                System.out.println("Marque: " + s.getMarque());
                System.out.println("Kilométrage: " + s.getKm() + " km");
                trouve = true;
                break;
            }
        }

        if (!trouve) {
            System.out.println("Scooter non trouvé.");
        }
    }

    public Client getClientByName(String nomComplet) {
        for (Client client : liste_client) {
            if ((client.getNom() + " " + client.getPrenom()).equals(nomComplet)) {
                return client;
            }
        }
        return null;
    }

    public Scooter getScooterById(int id) {
        for (Scooter scooter : liste_scooter) {
            if (scooter.getId() == id) {
                return scooter;
            }
        }
        return null;
    }

    public void afficherTousLesClients() {
        if (liste_client.isEmpty()) {
            System.out.println("Aucun client enregistré.");
        } else {
            for (Client c : liste_client) {
                System.out.println("Client : " + c.getNom() + " " + c.getPrenom());
            }
        }
    }

    public void afficherTousLesScooters() {
        if (liste_scooter.isEmpty()) {
            System.out.println("Aucun scooter disponible.");
        } else {
            for (Scooter s : liste_scooter) {
                System.out.println("Scooter ID: " + s.getId() + ", Marque: " + s.getMarque() + ", Kilométrage: "
                        + s.getKm() + " km");
            }
        }
    }

    public boolean scooterExiste(int idScooter) {
        for (Scooter s : liste_scooter) {
            if (s.getId() == idScooter) {
                return true;
            }
        }
        return false;
    }

    public boolean clientExiste(String nomComplet) {
        for (Client c : liste_client) {
            String nomClientComplet = c.getNom() + " " + c.getPrenom();
            if (nomClientComplet.equals(nomComplet)) {
                return true;
            }
        }
        return false;
    }

    public Vector<Scooter> list_Scooters_disponibles(Date dateDebut, Date dateFin) {
        Vector<Scooter> scootersDisponibles = new Vector<Scooter>();
        for (Scooter scooter : liste_scooter) {
            if (scooter.estDisponible(dateDebut, dateFin)) {
                scootersDisponibles.add(scooter);
            }
        }
        return scootersDisponibles;
    }
}