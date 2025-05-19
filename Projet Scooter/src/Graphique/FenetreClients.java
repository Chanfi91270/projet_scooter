package src.Graphique;

import javax.swing.*;
import java.awt.*;
import src.Controleur.*;
import src.Modele.*;

public class FenetreClients extends JFrame {

    public Magasin_Scooter magasin;
    public ControleurClients controleur;

    public JTextArea affichage;
    public JButton ajouter, supprimer, modifier, details, retourAccueil;

    public FenetreClients(Magasin_Scooter magasin) {
        this.magasin = magasin;
        this.controleur = new ControleurClients(magasin);

        setTitle("gestion des clients");
        setSize(830, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        affichage = new JTextArea();
        affichage.setEditable(false);
        affichage.setFont(new Font("monospaced", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(affichage);

        ajouter = new JButton("ajouter un client");
        supprimer = new JButton("supprimer un client");
        modifier = new JButton("modifier un client");
        details = new JButton("details clients");
        retourAccueil = new JButton("retour a l'accueil");

        JPanel panelBoutons = new JPanel(new GridLayout(1, 5, 15, 10));
        panelBoutons.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelBoutons.add(ajouter);
        panelBoutons.add(supprimer);
        panelBoutons.add(modifier);
        panelBoutons.add(details);
        panelBoutons.add(retourAccueil);

        add(scroll, BorderLayout.CENTER);
        add(panelBoutons, BorderLayout.SOUTH);

        afficherClients();

        ajouter.addActionListener(e -> controleur.ajouterClient(this));
        supprimer.addActionListener(e -> controleur.supprimerClient(this));
        modifier.addActionListener(e -> controleur.modifierClient(this));
        details.addActionListener(e -> controleur.afficherDetailsClient(this));

        retourAccueil.addActionListener(e -> {
            this.dispose();
            new FenetrePrincipale(magasin);
        });

        setVisible(true);
    }

    public void afficherClients() {
        StringBuilder sb = new StringBuilder();
        for (var c : magasin.getclient()) {
            sb.append(String.format("id: %-5d | %-15s %-15s | tel: %-12s\n",
                    c.getId(), c.getNom(), c.getPrenom(), c.getNumTel()));
        }
        affichage.setText(sb.toString());
    }
}
