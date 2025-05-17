package Controlleur;
import Graphique.*;
import Modele.*;
import java.util.List;

public class ControleurFenetrePrincipale {
    private final FenetrePrincipale vue;
    public Magasin_Scooter monMagasin;

    public ControleurFenetrePrincipale(FenetrePrincipale vue, Magasin_Scooter monMagasin) {
        this.vue = vue;
        this.monMagasin = monMagasin;
    }

    public void showClients() {
        new FenetreClients(vue).setVisible(true);
        vue.dispose();
    }

    public void showScooters() {
        new FenetreScooters(vue, monMagasin).setVisible(true);
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
