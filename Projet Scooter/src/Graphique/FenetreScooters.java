package src.Graphique;

import javax.swing.*;
import java.awt.*;

import src.Controleur.*;
import src.Modele.*;

import java.util.Vector;

public class FenetreScooters extends JFrame {
    public JTextArea affichage;                  // zone de texte pour afficher les infos
    public boolean afficheDisponibles = true;    // pour savoir si on affiche les scooters dispos ou tous
    public Magasin_Scooter magasin;               // magasin contenant les scooters
    public ControleurScooter controleurScooter;  // controleur pour gérer les actions

    public FenetreScooters(FenetrePrincipale fenetrePrincipale, Magasin_Scooter magasin, ControleurScooter controleurScooter) {
        this.magasin = magasin;
        this.controleurScooter = controleurScooter;
        setTitle("gestion des scooters");
        setSize(800, 530);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Vector<Scooter> scooters = magasin.getscooter(); // liste des scooters

        JPanel panelPrincipal = new JPanel(new BorderLayout());

        // panneau gauche : recherche et actions
        JPanel panelGauche = new JPanel(null);
        panelGauche.setPreferredSize(new Dimension(240, 500));
        panelGauche.setBackground(Color.WHITE);

        JLabel labelID = new JLabel("id scooter :"); // label pour id
        labelID.setBounds(10, 20, 100, 25);
        panelGauche.add(labelID);

        JTextField champID = new JTextField();       // champ texte pour id
        champID.setBounds(120, 20, 100, 25);
        panelGauche.add(champID);

        JLabel labelMarque = new JLabel("marque :"); // label marque
        labelMarque.setBounds(10, 60, 100, 25);
        panelGauche.add(labelMarque);

        JTextField champMarque = new JTextField();   // champ texte marque
        champMarque.setBounds(120, 60, 100, 25);
        panelGauche.add(champMarque);

        JLabel labelType = new JLabel("type :");     // label type
        labelType.setBounds(10, 100, 100, 25);
        panelGauche.add(labelType);

        JComboBox<String> comboType = new JComboBox<>(new String[]{"", "electrique", "non electrique"}); // combo type
        comboType.setBounds(120, 100, 100, 25);
        panelGauche.add(comboType);

        JLabel labelCC = new JLabel("cylindree :");  // label cylindree (cc)
        labelCC.setBounds(10, 140, 100, 25);
        panelGauche.add(labelCC);

        JComboBox<String> comboCC = new JComboBox<>(new String[]{"", "50", "125", "250"}); // combo cylindree
        comboCC.setBounds(120, 140, 100, 25);
        panelGauche.add(comboCC);

        JLabel labelPermis = new JLabel("permis :");  // label permis
        labelPermis.setBounds(10, 180, 100, 25);
        panelGauche.add(labelPermis);

        JComboBox<String> comboPermis = new JComboBox<>(new String[]{"", "A", "B", "A1"});  // combo permis
        comboPermis.setBounds(120, 180, 100, 25);
        panelGauche.add(comboPermis);

        JButton boutonRecherche = new JButton("rechercher"); // bouton recherche
        boutonRecherche.setBounds(60, 220, 120, 30);
        panelGauche.add(boutonRecherche);

        JButton boutonDisponibles = new JButton("scooters disponibles"); // bouton afficher dispo
        boutonDisponibles.setBounds(30, 270, 180, 30);
        panelGauche.add(boutonDisponibles);

        JButton boutonAjouter = new JButton("ajouter un scooter"); // bouton ajouter
        boutonAjouter.setBounds(30, 310, 180, 30);
        panelGauche.add(boutonAjouter);

        JButton boutonModifier = new JButton("modifier un scooter"); // bouton modifier
        boutonModifier.setBounds(30, 390, 180, 30);
        panelGauche.add(boutonModifier);

        JButton boutonEnlever = new JButton("enlever un scooter"); // bouton supprimer
        boutonEnlever.setBounds(30, 350, 180, 30);
        panelGauche.add(boutonEnlever);

        JButton boutonRetour = new JButton("accueil"); // bouton retour accueil
        boutonRetour.setBounds(70, 450, 100, 30);
        boutonRetour.addActionListener(e -> {
            fenetrePrincipale.setVisible(true);
            dispose();
        });
        panelGauche.add(boutonRetour);

        // panneau droit : affichage des scooters
        JPanel panelDroit = new JPanel(new BorderLayout());

        affichage = new JTextArea();    // zone de texte non editable pour affichage
        affichage.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(affichage);
        scrollPane.setPreferredSize(new Dimension(540, 500));
        panelDroit.add(scrollPane, BorderLayout.CENTER);

        panelPrincipal.add(panelGauche, BorderLayout.WEST);
        panelPrincipal.add(panelDroit, BorderLayout.CENTER);

        setContentPane(panelPrincipal);

        // action bouton rechercher
        boutonRecherche.addActionListener(e -> {
            JTextField[] textAreas = {champID, champMarque};
            JComboBox[] comboBoxes = {comboType, comboCC, comboPermis};
            this.controleurScooter.rechercheScooter(this, textAreas, comboBoxes, affichage);
        });

        // action bouton afficher dispo / tous
        boutonDisponibles.addActionListener(e -> {
            this.afficheDisponibles = this.controleurScooter.afficherScootersDisponible(scooters, affichage, boutonDisponibles, afficheDisponibles);
        });

        // action bouton ajouter scooter
        boutonAjouter.addActionListener(e -> {
            this.controleurScooter.ajouterScooter(this, scooters, affichage);
        });

        // action bouton modifier scooter
        boutonModifier.addActionListener(e -> {
            this.controleurScooter.modifierScooter(this, scooters, affichage);
        });

        // action bouton supprimer scooter
        boutonEnlever.addActionListener(e -> {
            this.controleurScooter.supprimerScooter(this, scooters, affichage);
        });
    }
}
