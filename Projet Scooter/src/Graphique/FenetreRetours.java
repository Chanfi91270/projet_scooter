package src.Graphique;

import javax.swing.*;
import java.awt.*;

import src.Controleur.*;
import src.Modele.*;

public class FenetreRetours extends JFrame {

    // attribut magasin
    public Magasin_Scooter magasin;
    // attribut fenetre principale
    public FenetrePrincipale fenetrePrincipale;

    // constructeur
    public FenetreRetours(FenetrePrincipale fenetrePrincipale, Magasin_Scooter magasin) {
        this.fenetrePrincipale = fenetrePrincipale;
        this.magasin = magasin;

        // parametres fenetre
        setTitle("gestion des retours");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // creation panel principal avec BorderLayout
        JPanel panel = new JPanel(new BorderLayout());

        // creation label titre (JLabel)
        JLabel label = new JLabel("gestion retours", SwingConstants.CENTER);
        label.setFont(new Font("arial", Font.BOLD, 20));
        label.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        panel.add(label, BorderLayout.NORTH);

        // creation bouton effectuer un retour (JButton)
        JButton boutonRetour = new JButton("effectuer un retour");
        boutonRetour.setFont(new Font("tahoma", Font.PLAIN, 14));
        boutonRetour.setFocusPainted(false);
        boutonRetour.setPreferredSize(new Dimension(200, 35));
        boutonRetour.addActionListener(e -> {
            ControleurRetours controleur = new ControleurRetours(magasin);
            controleur.effectuerRetour(this);
        });

        // panel centre pour bouton retour
        JPanel centrePanel = new JPanel();
        centrePanel.add(boutonRetour);
        panel.add(centrePanel, BorderLayout.CENTER);

        // creation bouton accueil (JButton)
        JButton boutonAccueil = new JButton("accueil");
        boutonAccueil.setFont(new Font("tahoma", Font.PLAIN, 13));
        boutonAccueil.setFocusPainted(false);
        boutonAccueil.setPreferredSize(new Dimension(120, 30));
        boutonAccueil.addActionListener(e -> {
            fenetrePrincipale.setVisible(true);
            this.dispose();
        });

        // panel bas pour bouton accueil
        JPanel basPanel = new JPanel();
        basPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 0));
        basPanel.add(boutonAccueil);
        panel.add(basPanel, BorderLayout.SOUTH);

        // ajout panel principal a la fenetre
        add(panel);

        // rendre fenetre visible
        setVisible(true);
    }
}
