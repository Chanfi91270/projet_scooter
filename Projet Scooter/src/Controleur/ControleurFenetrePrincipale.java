package src.Controleur;

import src.Modele.*;
import src.Graphique.*;

public class ControleurFenetrePrincipale {
    public FenetrePrincipale vue; // tu peux passer en public si tu veux
    public Magasin_Scooter monMagasin;
    public ControleurScooter controleurScooter;

    public ControleurFenetrePrincipale(FenetrePrincipale vue, Magasin_Scooter monMagasin) {
        this.vue = vue;
        this.monMagasin = monMagasin;
        this.controleurScooter = new ControleurScooter(monMagasin);
    }

    public void showClients() {
        new FenetreClients(monMagasin).setVisible(true);
        vue.dispose();
    }

    public void showScooters() {
        new FenetreScooters(vue, monMagasin, controleurScooter).setVisible(true);
        vue.dispose();
    }

    public void showLocations() {
        new FenetreLocations(vue, monMagasin).setVisible(true);
        vue.dispose();
    }

    public void showRetours() {
        new FenetreRetours(vue, monMagasin).setVisible(true);
        vue.dispose();
    }

    public void exitApplication() {
        System.exit(0);
    }
}
