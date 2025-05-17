package src.Graphique;

import javax.swing.*;
import java.awt.*;
import src.Controlleur.FenetreScootersController;

public class FenetreScooters extends JFrame {
    public JTextArea affichage;
    private JTextField champID, champMarque;
    private JComboBox<String> comboType, comboCC, comboPermis;
    private FenetreScootersController controleur;

    public FenetreScooters(FenetreScootersController controleur) {
        this.controleur = controleur;
        controleur.setView(this);
        setTitle("Gestion des Scooters");
        setSize(800, 530);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panelPrincipal = new JPanel(new BorderLayout());

        JPanel panelGauche = new JPanel(null);
        panelGauche.setPreferredSize(new Dimension(240, 500));
        panelGauche.setBackground(Color.WHITE);

        JLabel labelID = new JLabel("🔎 ID Scooter :");
        labelID.setBounds(10, 20, 100, 25);
        panelGauche.add(labelID);

        champID = new JTextField();
        champID.setBounds(120, 20, 100, 25);
        panelGauche.add(champID);

        JLabel labelMarque = new JLabel("Marque :");
        labelMarque.setBounds(10, 60, 100, 25);
        panelGauche.add(labelMarque);

        champMarque = new JTextField();
        champMarque.setBounds(120, 60, 100, 25);
        panelGauche.add(champMarque);

        JLabel labelType = new JLabel("Type :");
        labelType.setBounds(10, 100, 100, 25);
        panelGauche.add(labelType);

        comboType = new JComboBox<>(new String[] { "", "Électrique", "Non électrique" });
        comboType.setBounds(120, 100, 100, 25);
        panelGauche.add(comboType);

        JLabel labelCC = new JLabel("Cylindrée :");
        labelCC.setBounds(10, 140, 100, 25);
        panelGauche.add(labelCC);

        comboCC = new JComboBox<>(new String[] { "", "50", "125", "250" });
        comboCC.setBounds(120, 140, 100, 25);
        panelGauche.add(comboCC);

        JLabel labelPermis = new JLabel("Permis :");
        labelPermis.setBounds(10, 180, 100, 25);
        panelGauche.add(labelPermis);

        comboPermis = new JComboBox<>(new String[] { "", "A", "B", "A1" });
        comboPermis.setBounds(120, 180, 100, 25);
        panelGauche.add(comboPermis);

        JButton boutonRecherche = new JButton("Rechercher");
        boutonRecherche.setBounds(60, 220, 120, 30);
        boutonRecherche.addActionListener(e -> controleur.onRecherche());
        panelGauche.add(boutonRecherche);

        JButton boutonDisponibles = new JButton("Scooters disponibles");
        boutonDisponibles.setBounds(30, 270, 180, 30);
        boutonDisponibles.addActionListener(e -> controleur.onToggleDisponibles());
        panelGauche.add(boutonDisponibles);

        JButton boutonAjouter = new JButton("Ajouter un scooter");
        boutonAjouter.setBounds(30, 310, 180, 30);
        boutonAjouter.addActionListener(e -> controleur.onAjouter());
        panelGauche.add(boutonAjouter);

        JButton boutonModifier = new JButton("Modifier un scooter");
        boutonModifier.setBounds(30, 390, 180, 30);
        boutonModifier.addActionListener(e -> controleur.onModifier());
        panelGauche.add(boutonModifier);

        JButton boutonEnlever = new JButton("Enlever un scooter");
        boutonEnlever.setBounds(30, 350, 180, 30);
        boutonEnlever.addActionListener(e -> controleur.onEnlever());
        panelGauche.add(boutonEnlever);

        JButton boutonRetour = new JButton("⬅ Accueil");
        boutonRetour.setBounds(70, 450, 100, 30);
        boutonRetour.addActionListener(e -> dispose());
        panelGauche.add(boutonRetour);

        JPanel panelDroit = new JPanel(new BorderLayout());

        affichage = new JTextArea();
        affichage.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(affichage);
        scrollPane.setPreferredSize(new Dimension(540, 500));
        panelDroit.add(scrollPane, BorderLayout.CENTER);

        panelPrincipal.add(panelGauche, BorderLayout.WEST);
        panelPrincipal.add(panelDroit, BorderLayout.CENTER);

        setContentPane(panelPrincipal);
    }

    public String getIdText() {
        return champID.getText().trim();
    }

    public String getMarqueText() {
        return champMarque.getText().trim();
    }

    public String getTypeText() {
        return (String) comboType.getSelectedItem();
    }

    public String getCcText() {
        return (String) comboCC.getSelectedItem();
    }

    public String getPermisText() {
        return (String) comboPermis.getSelectedItem();
    }

    public void setAffichage(String text) {
        affichage.setText(text);
    }
}