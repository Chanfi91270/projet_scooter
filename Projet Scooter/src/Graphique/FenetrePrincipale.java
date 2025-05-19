package src.Graphique;

import src.Modele.*;
import src.Controleur.*;

import javax.swing.*;
import java.awt.*;

public class FenetrePrincipale extends JFrame {

    // attributs
    public ControleurFenetrePrincipale controleur;
    public Magasin_Scooter monMagasin;
    public FenetrePrincipale fenClients;

    // constructeur
    public FenetrePrincipale(Magasin_Scooter monMagasin) {
        this.monMagasin = monMagasin;

        // parametres fenetre
        setTitle("scoottime");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        // creation panel principal
        JPanel panel = new JPanel(new BorderLayout());

        // creation logo en haut
        JLabel logo = new JLabel();
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        logo.setPreferredSize(new Dimension(500, 70));

        ImageIcon originalIcon = new ImageIcon("src/Graphique/LOGO.jpg");
        Image image = originalIcon.getImage().getScaledInstance(170, 90, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(image);

        logo.setIcon(resizedIcon);
        panel.add(logo, BorderLayout.NORTH);

        // creation titre + sous titre
        JPanel titrePanel = new JPanel(new GridLayout(2, 1));
        titrePanel.setBackground(Color.WHITE);

        JLabel titre = new JLabel("bienvenue sur scootime", SwingConstants.CENTER);
        titre.setFont(new Font("Arial", Font.BOLD, 20));
        titre.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        titrePanel.add(titre);

        JLabel sousTitre = new JLabel("quelle section voulez-vous gerer ?", SwingConstants.CENTER);
        sousTitre.setFont(new Font("Arial", Font.PLAIN, 16));
        sousTitre.setBorder(BorderFactory.createEmptyBorder(5, 0, 45, 0));
        titrePanel.add(sousTitre);

        panel.add(titrePanel, BorderLayout.CENTER);

        // creation panel boutons
        JPanel boutons = new JPanel(new GridLayout(2, 2, 20, 20));
        boutons.setBorder(BorderFactory.createEmptyBorder(10, 40, 30, 40));
        boutons.setBackground(Color.WHITE);

        // bouton scooters
        JButton boutonScooters = new JButton("scooters");
        ImageIcon iconScooters = new ImageIcon("src/Graphique/scooter.jpg");
        Image imgScooters = iconScooters.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        boutonScooters.setIcon(new ImageIcon(imgScooters));
        boutonScooters.setHorizontalAlignment(SwingConstants.LEFT);
        boutonScooters.setIconTextGap(10);
        boutonScooters.setFont(new Font("Tahoma", Font.BOLD, 14));
        boutonScooters.setFocusPainted(false);
        boutonScooters.addActionListener(e -> controleur.showScooters());
        boutons.add(boutonScooters);

        // bouton locations
        JButton boutonLocations = new JButton("locations");
        ImageIcon iconLocations = new ImageIcon("src/Graphique/images.png");
        Image imgLocations = iconLocations.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        boutonLocations.setIcon(new ImageIcon(imgLocations));
        boutonLocations.setHorizontalAlignment(SwingConstants.LEFT);
        boutonLocations.setIconTextGap(10);
        boutonLocations.setFont(new Font("Tahoma", Font.BOLD, 14));
        boutonLocations.setFocusPainted(false);
        boutonLocations.addActionListener(e -> controleur.showLocations());
        boutons.add(boutonLocations);

        // bouton clients
        JButton boutonClients = new JButton("clients");
        ImageIcon iconClients = new ImageIcon("src/Graphique/6009864.png");
        Image imgClients = iconClients.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        boutonClients.setIcon(new ImageIcon(imgClients));
        boutonClients.setHorizontalAlignment(SwingConstants.LEFT);
        boutonClients.setIconTextGap(10);
        boutonClients.setFont(new Font("Tahoma", Font.BOLD, 14));
        boutonClients.setFocusPainted(false);
        boutonClients.addActionListener(e -> controleur.showClients());
        boutons.add(boutonClients);

        // bouton retours
        JButton boutonRetours = new JButton("retours");
        ImageIcon iconRetours = new ImageIcon("src/Graphique/32161.png");
        Image imgRetours = iconRetours.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        boutonRetours.setIcon(new ImageIcon(imgRetours));
        boutonRetours.setHorizontalAlignment(SwingConstants.LEFT);
        boutonRetours.setIconTextGap(10);
        boutonRetours.setFont(new Font("Tahoma", Font.BOLD, 14));
        boutonRetours.setFocusPainted(false);
        boutonRetours.addActionListener(e -> controleur.showRetours());
        boutons.add(boutonRetours);

        panel.add(boutons, BorderLayout.SOUTH);

        // ajout panel fenetre
        add(panel);

        // creation controleur
        controleur = new ControleurFenetrePrincipale(this, monMagasin);

        // affichage fenetre
        setVisible(true);
    }
}
