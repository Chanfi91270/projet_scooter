package src.Controleur;

import src.Modele.*;

import javax.swing.*;
import java.util.Vector;

public class ControleurScooter {

    public Magasin_Scooter magasin;

    public ControleurScooter(Magasin_Scooter magasin) {
        this.magasin = magasin;
    }

    public void rechercheScooter(JFrame frame, JTextField[] textFields, JComboBox[] comboBoxes, JTextArea affichage) {
        String idTexte = textFields[0].getText().trim().toLowerCase();
        String marqueTexte = textFields[1].getText().trim().toLowerCase();
        String typeTexte = ((String) comboBoxes[0].getSelectedItem()).trim().toLowerCase();
        String ccTexte = ((String) comboBoxes[1].getSelectedItem()).trim().toLowerCase();
        String permisTexte = ((String) comboBoxes[2].getSelectedItem()).trim().toLowerCase();

        // Vérifie si tous les champs sont vides ou à "tous"
        boolean aucunCritere = idTexte.isEmpty() && marqueTexte.isEmpty()
                && (typeTexte.equals("tous") || typeTexte.isEmpty())
                && (ccTexte.equals("tous") || ccTexte.isEmpty())
                && (permisTexte.equals("tous") || permisTexte.isEmpty());

        if (aucunCritere) {
            JOptionPane.showMessageDialog(frame, "Veuillez sélectionner au moins un critère de recherche.",
                    "Aucun critère", JOptionPane.WARNING_MESSAGE);
            affichage.setText(""); // efface les résultats
            return;
        }

        StringBuilder resultats = new StringBuilder();

        for (Scooter scooter : magasin.getscooter()) {
            boolean correspondanceID = idTexte.isEmpty()
                    || String.valueOf(scooter.getId()).toLowerCase().contains(idTexte);
            boolean correspondanceMarque = marqueTexte.isEmpty()
                    || scooter.getMarque().toLowerCase().contains(marqueTexte);

            boolean correspondanceType = typeTexte.isEmpty() || typeTexte.equals("tous");
            boolean correspondanceCC = ccTexte.isEmpty() || ccTexte.equals("tous");
            boolean correspondancePermis = permisTexte.isEmpty() || permisTexte.equals("tous");

            for (Type_Scooter ts : scooter.gettype()) {
                if (!typeTexte.isEmpty() && !typeTexte.equals("tous")
                        && ts.getTypeScooter().toLowerCase().contains(typeTexte)) {
                    correspondanceType = true;
                }
                if (!ccTexte.isEmpty() && !ccTexte.equals("tous")
                        && String.valueOf(ts.getCc()).toLowerCase().contains(ccTexte)) {
                    correspondanceCC = true;
                }
                if (!permisTexte.isEmpty() && !permisTexte.equals("tous") && ts.getPermis() != null &&
                        ts.getPermis().getTypePermis().toLowerCase().equals(permisTexte)) {
                    correspondancePermis = true;
                }
            }

            if (correspondanceID && correspondanceMarque && correspondanceType && correspondanceCC
                    && correspondancePermis) {
                resultats.append("ID: ").append(scooter.getId())
                        .append(" | Marque: ").append(scooter.getMarque())
                        .append(" | Km: ").append(scooter.getKm())
                        .append(" | Types: ");

                for (Type_Scooter ts : scooter.gettype()) {
                    resultats.append(ts.getTypeScooter()).append(" (")
                            .append(ts.getCc()).append("cc, Permis ");
                    if (ts.getPermis() != null) {
                        resultats.append(ts.getPermis().getTypePermis());
                    } else {
                        resultats.append("N/A");
                    }
                    resultats.append(") ");
                }
                resultats.append("\n");
            }

        }

        affichage.setText(resultats.toString());
    }

    public boolean afficherScootersDisponible(Vector<Scooter> scooters, JTextArea affichage, JButton boutonDisponibles, boolean afficheDisponibles) {
        affichage.setText(""); // Vide la zone d'affichage

        if (afficheDisponibles) {
            // Afficher uniquement les scooters
            for (Scooter s : scooters) {
                if (s.estActuellementDisponible()) {
                    affichage.append(
                            "ID: " + s.getId() + "| Marque: " + s.getMarque() + "| Km: " + s.getKm() + "\n");

                    for (Type_Scooter t : s.gettype()) {
                        affichage.append(
                                "   → Type: " + t.getTypeScooter() + "| Permis: " + t.getPermisString()
                                        + "| Cylindrée: "
                                        + t.getCc() + "cc\n");
                    }
                    affichage.append("\n");
                }
            }
            if (affichage.getText().isEmpty()) {
                affichage.setText("Aucun scooter disponible pour le moment.");
            }
            boutonDisponibles.setText("Afficher tous les scooters");
        } else {
            // Afficher tous les scooters
            for (Scooter s : scooters) {
                affichage.append("ID: " + s.getId() + "| Marque: " + s.getMarque() + "| Km: " + s.getKm());

                if (s.estActuellementDisponible()) {
                    affichage.append(" (Disponible)");
                } else {
                    affichage.append(" (Loué)");
                }

                affichage.append("\n");

                for (Type_Scooter t : s.gettype()) {
                    affichage.append("   → Type: " + t.getTypeScooter() + "| Permis: ");

                    if (t.getPermis() != null) {
                        affichage.append(t.getPermis().getTypePermis());
                    } else {
                        affichage.append("N/A");
                    }

                    affichage.append("| Cylindrée: " + t.getCc() + "cc\n");
                }

                affichage.append("\n");
            }

            boutonDisponibles.setText("Scooters disponibles");
        }

        // Inverse l’état pour le prochain clic
        return !afficheDisponibles;
    }

    public void ajouterScooter(JFrame parent, Vector<Scooter> scooters, JTextArea affichage) {
        String nouveauID = JOptionPane.showInputDialog(parent, "Entrez l'ID du scooter :");
        if (nouveauID == null || nouveauID.trim().isEmpty())
            return;

        int idInt;
        try {
            idInt = Integer.parseInt(nouveauID.trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(parent, "L'ID doit être un nombre entier.", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Vérifie que l'ID n'existe pas déjà
        for (Scooter scooter : scooters) {
            if (String.valueOf(scooter.getId()).equalsIgnoreCase(nouveauID.trim())) {
                JOptionPane.showMessageDialog(parent, "Cet ID existe déjà.");
                return;
            }
        }

        String marque = JOptionPane.showInputDialog(parent, "Entrez la marque :");
        if (marque == null || marque.trim().isEmpty())
            return;

        String type = (String) JOptionPane.showInputDialog(parent, "Type de scooter :", "Type",
                JOptionPane.QUESTION_MESSAGE, null, new String[]{"Électrique", "Non électrique"}, "Électrique");
        if (type == null)
            return;

        String cc = (String) JOptionPane.showInputDialog(parent, "Cylindrée :", "CC",
                JOptionPane.QUESTION_MESSAGE, null, new String[]{"50cc", "125cc", "250cc"}, "50cc");
        if (cc == null)
            return;

        // Déterminer le permis en fonction de la cylindrée
        String permis;
        if (cc.equals("50cc"))
            permis = "B";
        else if (cc.equals("125cc"))
            permis = "A";
        else
            permis = "A1";

        // Convertir cc (chaine) vers entier
        int cylindree = 0;
        if (cc.equals("50cc")) {
            cylindree = 50;
        } else if (cc.equals("125cc")) {
            cylindree = 125;
        } else if (cc.equals("250cc")) {
            cylindree = 250;
        }

        // Création du nouvel objet Scooter
        Scooter nouveauScooter = new Scooter(marque, Integer.parseInt(nouveauID), 0);
        Permis Permis = new Permis(permis); // ← Utiliser le bon type ici !
        Type_Scooter typeScooter = new Type_Scooter(type, cylindree, nouveauScooter, Permis);

        // 🔥 Très important : Ajouter le type dans le scooter
        nouveauScooter.gettype().add(typeScooter);

        // Ajouter le scooter à la liste
        scooters.add(nouveauScooter);

        // 🔄 Réafficher les scooters dans l'affichage
        affichage.setText(""); // Effacer
        for (Scooter s : scooters) {
            affichage.append("ID: " + s.getId() + "| Marque: " + s.getMarque() + "| Km: " + s.getKm());

            if (s.estActuellementDisponible()) {
                affichage.append(" (Disponible)");
            } else {
                affichage.append(" (Loué)");
            }

            affichage.append("\n");

            for (Type_Scooter t : s.gettype()) {
                affichage.append("   → Type: " + t.getTypeScooter() + "| Permis: ");

                if (t.getPermis() != null) {
                    affichage.append(t.getPermis().getTypePermis());
                } else {
                    affichage.append("N/A");
                }

                affichage.append("| Cylindrée: " + t.getCc() + "cc\n");
            }

            affichage.append("\n");
        }
    }

    public void modifierScooter(JFrame parent, Vector<Scooter> scooters, JTextArea affichage) {
        String id = JOptionPane.showInputDialog(parent, "Entrez l'ID du scooter à modifier :");
        if (id == null || id.trim().isEmpty())
            return;

        Scooter scooterTrouve = null;
        for (Scooter scooter : scooters) {
            if (String.valueOf(scooter.getId()).equalsIgnoreCase(id.trim())) {
                scooterTrouve = scooter;
                break;
            }
        }

        if (scooterTrouve == null) {
            JOptionPane.showMessageDialog(parent, "Scooter non trouvé !");
            return;
        }

        // 1. Modifier la marque
        String[] optionsMarque = {"Modifier", "Ne pas modifier", "Annuler"};
        int choixMarque = JOptionPane.showOptionDialog(parent, "Modifier la marque ?", "Modification",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, optionsMarque, optionsMarque[0]);

        if (choixMarque == 2)
            return;
        if (choixMarque == 0) {
            String nouvelleMarque = JOptionPane.showInputDialog(parent, "Nouvelle marque :");
            if (nouvelleMarque != null && !nouvelleMarque.trim().isEmpty()) {
                scooterTrouve.setMarque(nouvelleMarque.trim());
            }
        }

        // 2. Modifier le kilométrage
        String[] optionsKm = {"Modifier", "Ne pas modifier", "Annuler"};
        int choixKm = JOptionPane.showOptionDialog(parent, "Modifier le kilométrage ?", "Modification",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, optionsKm, optionsKm[0]);

        if (choixKm == 2)
            return;
        if (choixKm == 0) {
            String nouveauKmStr = JOptionPane.showInputDialog(parent, "Nouveau kilométrage :");
            if (nouveauKmStr != null && !nouveauKmStr.trim().isEmpty()) {
                try {
                    int nouveauKm = Integer.parseInt(nouveauKmStr.trim());
                    if (nouveauKm >= 0) {
                        scooterTrouve.setKm(nouveauKm);
                    } else {
                        JOptionPane.showMessageDialog(parent, "Le kilométrage doit être positif.");
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(parent, "Veuillez entrer un nombre valide pour le kilométrage.");
                    return;
                }
            }
        }

        // 3. Modifier le type de scooter
        String[] optionsType = {"Modifier", "Ne pas modifier", "Annuler"};
        int choixType = JOptionPane.showOptionDialog(parent, "Modifier le type de scooter ?", "Modification",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, optionsType, optionsType[0]);

        String nouveauType = null;
        if (choixType == 2)
            return;
        if (choixType == 0) {
            nouveauType = (String) JOptionPane.showInputDialog(parent, "Type de scooter :", "Type",
                    JOptionPane.QUESTION_MESSAGE, null, new String[]{"Électrique", "Non électrique"},
                    "Électrique");
            if (nouveauType == null)
                return;
        } else {
            if (!scooterTrouve.gettype().isEmpty()) {
                nouveauType = scooterTrouve.gettype().firstElement().getTypeScooter();
            } else {
                nouveauType = "Non défini";
            }
        }

        // 4. Modifier la cylindrée
        String[] optionsCC = {"Modifier", "Ne pas modifier", "Annuler"};
        int choixCC = JOptionPane.showOptionDialog(parent, "Modifier la cylindrée ?", "Modification",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, optionsCC, optionsCC[0]);

        String cc = null;
        if (choixCC == 2)
            return;
        if (choixCC == 0) {
            cc = (String) JOptionPane.showInputDialog(parent, "Nouvelle cylindrée :", "Cylindrée",
                    JOptionPane.QUESTION_MESSAGE, null, new String[]{"50cc", "125cc", "250cc"}, "50cc");
            if (cc == null)
                return;
        } else {
            if (!scooterTrouve.gettype().isEmpty()) {
                cc = String.valueOf(scooterTrouve.gettype().firstElement().getCc()) + "cc";
            } else {
                cc = "50cc";
            }
        }

        // 5. Déterminer le permis automatiquement
        String permisStr;
        if (cc.equals("50cc")) {
            permisStr = "A1";
        } else if (cc.equals("125cc")) {
            permisStr = "A2";
        } else {
            permisStr = "A";
        }

        int cylindree = Integer.parseInt(cc.replace("cc", ""));
        Permis nouveauPermis = new Permis(permisStr);
        Type_Scooter nouveauTypeScooter = new Type_Scooter(nouveauType, cylindree, scooterTrouve, nouveauPermis);

        // Mettre à jour le type
        scooterTrouve.gettype().clear();
        scooterTrouve.gettype().add(nouveauTypeScooter);

        // Actualiser affichage
        affichage.append("ID: " + scooterTrouve.getId() + "| Marque: " + scooterTrouve.getMarque() + "| Km: "
                + scooterTrouve.getKm() + "\n");
        affichage.append("   → Type: " + nouveauType + "| Permis: " + permisStr + "| Cylindrée: "
                + cylindree + "cc\n\n");

        affichage.setCaretPosition(affichage.getDocument().getLength());
    }

    public void supprimerScooter(JFrame parent, Vector<Scooter> scooters, JTextArea affichage) {
        String idASupprimer = JOptionPane.showInputDialog(parent, "Entrez l'ID du scooter à supprimer :");
        if (idASupprimer == null || idASupprimer.trim().isEmpty())
            return;

        boolean supprimé = false;
        for (int i = 0; i < scooters.size(); i++) {
            if (String.valueOf(scooters.get(i).getId()).equalsIgnoreCase(idASupprimer.trim())) {
                scooters.remove(i);
                supprimé = true;
                break;
            }
        }

        if (supprimé) {
            affichage.append("Scooter ID " + idASupprimer + " supprimé.\n");
        } else {
            JOptionPane.showMessageDialog(parent, "Scooter non trouvé !");
        }
    }
}