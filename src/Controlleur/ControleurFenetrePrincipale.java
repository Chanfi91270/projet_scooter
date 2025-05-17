// src/Controlleur/ControleurFenetrePrincipale.java
package src.Controlleur;

import src.Modele.Magasin_Scooter;
import src.Graphique.FenetrePrincipale;
import src.Graphique.FenetreClients;
import src.Graphique.FenetreLocations;
import src.Graphique.FenetreRetours;
import src.Graphique.FenetreScooters;
import src.Controlleur.FenetreScootersController;

public class ControleurFenetrePrincipale {
    private final FenetrePrincipale vue;
    private final Magasin_Scooter monMagasin;

    public ControleurFenetrePrincipale(FenetrePrincipale vue, Magasin_Scooter monMagasin) {
        this.vue = vue;
        this.monMagasin = monMagasin;
    }

    public void showClients() {
        new FenetreClients(vue).setVisible(true);
        vue.dispose();
    }

    public void showScooters() {
        // Création du contrôleur et de la vue dans le bon ordre
        FenetreScootersController controller = new FenetreScootersController(monMagasin);
        FenetreScooters fenetreScooters = new FenetreScooters(controller);
        controller.initView();              // remplit la liste au lancement
        fenetreScooters.setVisible(true);
        vue.dispose();
    }

    public void showLocations() {
        new FenetreLocations(vue).setVisible(true);
        vue.dispose();
    }

    public void showRetours() {
        new FenetreRetours(vue).setVisible(true);
        vue.dispose();
    }

    public void exitApplication() {
        System.exit(0);
    }
}
