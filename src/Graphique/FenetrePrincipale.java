package Graphique;

import Controlleur.ControleurFenetrePrincipale;
import Modele.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class FenetrePrincipale extends JFrame {
    public ControleurFenetrePrincipale controleur;
    public Magasin_Scooter monMagasin;

    public FenetrePrincipale(Magasin_Scooter monMagasin) {
        this.monMagasin = monMagasin;
        setTitle("ScootTIME");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        // Logo
        JLabel logo = new JLabel();
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        logo.setPreferredSize(new Dimension(500, 70));

        // Chargement et redimensionnement de l'image
        ImageIcon originalIcon = new ImageIcon("src/Graphique/LOGO.jpg");
        Image image = originalIcon.getImage().getScaledInstance(170, 90, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(image);

        logo.setIcon(resizedIcon);
        panel.add(logo, BorderLayout.NORTH);

        // Titre + sous-titre
        JPanel titrePanel = new JPanel(new GridLayout(2, 1));
        titrePanel.setBackground(Color.WHITE);

        JLabel titre = new JLabel("Bienvenue sur SCOOTIME", SwingConstants.CENTER);
        titre.setFont(new Font("Arial", Font.BOLD, 20));
        titre.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        titrePanel.add(titre);

        JLabel sousTitre = new JLabel("Quelle section voulez-vous gérer ?", SwingConstants.CENTER);
        sousTitre.setFont(new Font("Arial", Font.PLAIN, 16));
        sousTitre.setBorder(BorderFactory.createEmptyBorder(5, 0, 45, 0));
        titrePanel.add(sousTitre);

        panel.add(titrePanel, BorderLayout.CENTER);

        // Boutons
        JPanel boutons = new JPanel(new GridLayout(2, 2, 20, 20));
        boutons.setBorder(BorderFactory.createEmptyBorder(10, 40, 30, 40));
        boutons.setBackground(Color.WHITE);

        // Bouton Scooters
        JButton boutonScooters = new JButton("Scooters");
        ImageIcon iconScooters = new ImageIcon("src/Graphique/scooter.jpg");
        Image imgScooters = iconScooters.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        boutonScooters.setIcon(new ImageIcon(imgScooters));
        boutonScooters.setHorizontalAlignment(SwingConstants.LEFT);
        boutonScooters.setIconTextGap(10);
        boutonScooters.setBackground(new Color(59, 89, 182));
        boutonScooters.setForeground(Color.WHITE);
        boutonScooters.setFont(new Font("Tahoma", Font.BOLD, 14));
        boutonScooters.setFocusPainted(false);
        boutonScooters.addActionListener(e -> controleur.showScooters());
        boutons.add(boutonScooters);

        // Bouton Locations
        JButton boutonLocations = new JButton("Locations");
        ImageIcon iconLocations = new ImageIcon("src/Graphique/images.png");
        Image imgLocations = iconLocations.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        boutonLocations.setIcon(new ImageIcon(imgLocations));
        boutonLocations.setHorizontalAlignment(SwingConstants.LEFT);
        boutonLocations.setIconTextGap(10);
        boutonLocations.setBackground(new Color(59, 89, 182));
        boutonLocations.setForeground(Color.WHITE);
        boutonLocations.setFont(new Font("Tahoma", Font.BOLD, 14));
        boutonLocations.setFocusPainted(false);
        boutonLocations.addActionListener(e -> controleur.showLocations());
        boutons.add(boutonLocations);

        // Bouton Clients
        JButton boutonClients = new JButton("Clients");
        ImageIcon iconClients = new ImageIcon("src/Graphique/6009864.png");
        Image imgClients = iconClients.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        boutonClients.setIcon(new ImageIcon(imgClients));
        boutonClients.setHorizontalAlignment(SwingConstants.LEFT);
        boutonClients.setIconTextGap(10);
        boutonClients.setBackground(new Color(59, 89, 182));
        boutonClients.setForeground(Color.WHITE);
        boutonClients.setFont(new Font("Tahoma", Font.BOLD, 14));
        boutonClients.setFocusPainted(false);
        boutonClients.addActionListener(e -> controleur.showClients());
        boutons.add(boutonClients);

        // Bouton Retours
        JButton boutonRetours = new JButton("Retours");
        ImageIcon iconRetours = new ImageIcon("src/Graphique/32161.png");
        Image imgRetours = iconRetours.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        boutonRetours.setIcon(new ImageIcon(imgRetours));
        boutonRetours.setHorizontalAlignment(SwingConstants.LEFT);
        boutonRetours.setIconTextGap(10);
        boutonRetours.setBackground(new Color(59, 89, 182));
        boutonRetours.setForeground(Color.WHITE);
        boutonRetours.setFont(new Font("Tahoma", Font.BOLD, 14));
        boutonRetours.setFocusPainted(false);
        boutonRetours.addActionListener(e -> controleur.showRetours());
        boutons.add(boutonRetours);

        // Ajouter les boutons au panel principal
        panel.add(boutons, BorderLayout.SOUTH);
        add(panel);

        // Initialisation du contrôleur
        controleur = new ControleurFenetrePrincipale(this, monMagasin);

        setVisible(true);
    }
}
