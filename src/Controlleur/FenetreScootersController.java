package src.Controlleur;

import src.Graphique.FenetreScooters;
import src.Modele.Magasin_Scooter;
import src.Modele.Scooter;
import src.Modele.Type_Scooter;
import javax.swing.*;
import java.util.List;

public class FenetreScootersController {
    private FenetreScooters view;
    private final Magasin_Scooter magasin;
    private boolean afficheDisponibles = true;

    public FenetreScootersController(Magasin_Scooter magasin) {
        this.magasin = magasin;
    }

    public void setView(FenetreScooters view) {
        this.view = view;
    }

    public void initView() {
        miseAJourAffichage(magasin.getscooter());
    }

    public void onRecherche() {
        String idTexte = view.getIdText().toLowerCase();
        String marqueTexte = view.getMarqueText().toLowerCase();
        String typeTexte = view.getTypeText().toLowerCase();
        String ccTexte = view.getCcText().toLowerCase();
        String permisTexte = view.getPermisText().toLowerCase();

        boolean aucunCritere = idTexte.isEmpty() && marqueTexte.isEmpty()
                && (typeTexte.isEmpty() || typeTexte.equals("tous"))
                && (ccTexte.isEmpty() || ccTexte.equals("tous"))
                && (permisTexte.isEmpty() || permisTexte.equals("tous"));

        if (aucunCritere) {
            JOptionPane.showMessageDialog(null,
                    "Veuillez sélectionner au moins un critère de recherche.",
                    "Aucun critère", JOptionPane.WARNING_MESSAGE);
            view.setAffichage("");
            return;
        }

        List<Scooter> liste = magasin.getscooter();
        StringBuilder resultats = new StringBuilder();

        for (Scooter scooter : liste) {
            if (afficheDisponibles && !scooter.estActuellementDisponible()) {
                continue;
            }

            boolean matchID = idTexte.isEmpty() || String.valueOf(scooter.getId()).toLowerCase().contains(idTexte);
            boolean matchMarque = marqueTexte.isEmpty() || scooter.getMarque().toLowerCase().contains(marqueTexte);
            boolean matchType = typeTexte.isEmpty() || typeTexte.equals("tous");
            boolean matchCC = ccTexte.isEmpty() || ccTexte.equals("tous");
            boolean matchPermis = permisTexte.isEmpty() || permisTexte.equals("tous");

            for (Type_Scooter ts : scooter.gettype()) {
                String tsType = ts.getTypeScooter().toLowerCase();
                String tsCc = String.valueOf(ts.getCc()).toLowerCase();
                String tsPermis = ts.getPermis() != null ? ts.getPermis().getTypePermis().toLowerCase() : "";

                if (!typeTexte.isEmpty() && !typeTexte.equals("tous") && tsType.contains(typeTexte)) {
                    matchType = true;
                }
                if (!ccTexte.isEmpty() && !ccTexte.equals("tous") && tsCc.contains(ccTexte)) {
                    matchCC = true;
                }
                if (!permisTexte.isEmpty() && !permisTexte.equals("tous") && tsPermis.equals(permisTexte)) {
                    matchPermis = true;
                }
            }

            if (matchID && matchMarque && matchType && matchCC && matchPermis) {
                resultats.append("ID: ").append(scooter.getId())
                        .append(" | Marque: ").append(scooter.getMarque())
                        .append(" | Km: ").append(scooter.getKm())
                        .append(" | Types: ");
                for (Type_Scooter ts : scooter.gettype()) {
                    resultats.append(ts.getTypeScooter())
                            .append(" (").append(ts.getCc()).append("cc, Permis ")
                            .append(ts.getPermis() != null ? ts.getPermis().getTypePermis() : "N/A")
                            .append(") ");
                }
                resultats.append("\n");
            }
        }

        view.setAffichage(resultats.toString());
    }

    public void onToggleDisponibles() {
        afficheDisponibles = !afficheDisponibles;
        initView();
    }

    public void onAjouter() {
        // TODO: dialogue pour ajout de scooter
    }

    public void onModifier() {
        // TODO: dialogue pour modification
    }

    public void onEnlever() {
        // TODO: suppression du scooter sélectionné
    }

    private void miseAJourAffichage(List<Scooter> liste) {
        StringBuilder resultats = new StringBuilder();
        for (Scooter s : liste) {
            if (afficheDisponibles && !s.estActuellementDisponible()) {
                continue;
            }
            resultats.append("ID: ").append(s.getId())
                    .append(" | Marque: ").append(s.getMarque())
                    .append(" | Km: ").append(s.getKm())
                    .append(" | Types: ");
            for (Type_Scooter ts : s.gettype()) {
                resultats.append(ts.getTypeScooter())
                        .append(" (").append(ts.getCc()).append("cc, Permis ")
                        .append(ts.getPermis() != null ? ts.getPermis().getTypePermis() : "N/A")
                        .append(") ");
            }
            resultats.append("\n");
        }
        view.setAffichage(resultats.toString());
    }
}