package src.Controleur;

import javax.swing.*;
import src.Modele.*;
import src.Graphique.*;

public class ControleurClients {

    public Magasin_Scooter magasin;

    public ControleurClients(Magasin_Scooter magasin) {
        this.magasin = magasin;
    }

    // Méthode pour vérifier si un ID client existe déjà
    public boolean idClientExiste(int id) {
        for (Client c : magasin.getclient()) {
            if (c.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public void ajouterClient(JFrame parent) {
        try {
            String nom = JOptionPane.showInputDialog(parent, "Nom :");
            String prenom = JOptionPane.showInputDialog(parent, "Prénom :");
            String tel = JOptionPane.showInputDialog(parent, "Numéro de téléphone :");
            String idStr = JOptionPane.showInputDialog(parent, "ID du client :");

            if (nom == null || prenom == null || tel == null || idStr == null) return;

            int id = Integer.parseInt(idStr);

            if (idClientExiste(id)) {
                JOptionPane.showMessageDialog(parent, "Erreur : cet ID client existe déjà.", "Erreur ID", JOptionPane.ERROR_MESSAGE);
                return; // stop ajout
            }

            Client nouveau = new Client(nom, prenom, id, tel);
            magasin.ajouterClient(nouveau);
            JOptionPane.showMessageDialog(parent, "Client ajouté !");
            ((FenetreClients) parent).afficherClients();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parent, "Erreur lors de l'ajout du client.");
        }
    }

    public void supprimerClient(JFrame parent) {
        try {
            String idStr = JOptionPane.showInputDialog(parent, "ID du client à supprimer :");
            if (idStr == null) return;
            int id = Integer.parseInt(idStr);

            Client clientASupprimer = null;
            for (Client c : magasin.getclient()) {
                if (c.getId() == id) {
                    clientASupprimer = c;
                    break;
                }
            }

            if (clientASupprimer != null) {
                magasin.supprimerClient(clientASupprimer);
                JOptionPane.showMessageDialog(parent, "Client supprimé !");
                ((FenetreClients) parent).afficherClients();
            } else {
                JOptionPane.showMessageDialog(parent, "Client introuvable.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parent, "Erreur lors de la suppression.");
        }
    }

    public void modifierClient(JFrame parent) {
        try {
            String idStr = JOptionPane.showInputDialog(parent, "ID du client à modifier :");
            if (idStr == null) return;
            int id = Integer.parseInt(idStr);

            Client client = null;
            for (Client c : magasin.getclient()) {
                if (c.getId() == id) {
                    client = c;
                    break;
                }
            }

            if (client != null) {
                String nouveauNom = JOptionPane.showInputDialog(parent, "Nouveau nom :", client.getNom());
                String nouveauPrenom = JOptionPane.showInputDialog(parent, "Nouveau prénom :", client.getPrenom());
                String nouveauTel = JOptionPane.showInputDialog(parent, "Nouveau téléphone :", client.getNumTel());

                if (nouveauNom != null) client.setNom(nouveauNom);
                if (nouveauPrenom != null) client.setPrenom(nouveauPrenom);
                if (nouveauTel != null) client.setNumTel(nouveauTel);

                JOptionPane.showMessageDialog(parent, "Client modifié !");
                ((FenetreClients) parent).afficherClients();
            } else {
                JOptionPane.showMessageDialog(parent, "Client introuvable.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(parent, "Erreur lors de la modification.");
        }
    }

    public void afficherDetailsClient(JFrame parent) {
        try {
            String idStr = JOptionPane.showInputDialog(parent, "ID du client à afficher :");
            if (idStr == null) return;
            int id = Integer.parseInt(idStr);

            Client client = null;
            for (Client c : magasin.getclient()) {
                if (c.getId() == id) {
                    client = c;
                    break;
                }
            }

            if (client != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Nom : ").append(client.getNom()).append("\n")
                        .append("Prénom : ").append(client.getPrenom()).append("\n")
                        .append("Téléphone : ").append(client.getNumTel()).append("\n")
                        .append("Locations :\n");

                for (Location loc : client.getloc()) {
                    sb.append("  ID: ").append(loc.getid_location())
                            .append(" - Du ").append(loc.getdate_debut())
                            .append(" au ").append(loc.getdate_fin())
                            .append(" - Prix: ").append(loc.getprix()).append("€\n");
                }

                JOptionPane.showMessageDialog(parent, sb.toString(), "Détails Client", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(parent, "Client introuvable.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parent, "Erreur lors de l'affichage des détails.");
        }
    }
}
