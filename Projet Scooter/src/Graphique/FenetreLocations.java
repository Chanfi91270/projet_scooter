package src.Graphique;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import src.Controleur.*;
import src.Modele.*;

public class FenetreLocations extends JFrame {

    // attributs
    public ControleurLocations controleur;
    public JTable tableLocations;
    public DefaultTableModel modeleTable;

    // constructeur
    public FenetreLocations(FenetrePrincipale fenetrePrincipale, Magasin_Scooter magasin) {
        // param fenetre
        setTitle("gestion des locations");
        setSize(600, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        controleur = new ControleurLocations(magasin);

        // creation panel principal
        JPanel panel = new JPanel(new BorderLayout());

        // titre en haut
        JLabel titre = new JLabel("gestion des locations", SwingConstants.CENTER);
        titre.setFont(new Font("arial", Font.BOLD, 18));
        titre.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        panel.add(titre, BorderLayout.NORTH);

        // creation tableau locations
        String[] colonnes = {"id location", "client", "scooter", "date debut", "date fin", "prix"};
        modeleTable = new DefaultTableModel(colonnes, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        tableLocations = new JTable(modeleTable);
        JScrollPane scrollPane = new JScrollPane(tableLocations);
        panel.add(scrollPane, BorderLayout.CENTER);

        // charger les locations dans tableau
        chargerLocations(magasin);

        // creation panel centre pour boutons
        JPanel centre = new JPanel();
        centre.setLayout(new BoxLayout(centre, BoxLayout.Y_AXIS));
        centre.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));

        // panel boutons louer + supprimer
        JPanel boutons = new JPanel(new GridLayout(1, 2, 25, 0));

        // bouton louer
        JButton boutonLouer = new JButton("louer un scooter");
        boutonLouer.setFont(new Font("tahoma", Font.PLAIN, 14));
        boutonLouer.setFocusPainted(false);
        boutonLouer.setMaximumSize(new Dimension(200, 40));
        boutonLouer.setPreferredSize(new Dimension(200, 40));
        boutonLouer.addActionListener(e -> {
            if (controleur.louerScooter(this)) {
                chargerLocations(magasin);
            }
        });
        boutons.add(boutonLouer);

        // bouton supprimer
        JButton boutonSupprimer = new JButton("supprimer une location");
        boutonSupprimer.setFont(new Font("tahoma", Font.PLAIN, 14));
        boutonSupprimer.setFocusPainted(false);
        boutonSupprimer.setMaximumSize(new Dimension(200, 40));
        boutonSupprimer.setPreferredSize(new Dimension(200, 40));
        boutonSupprimer.addActionListener(e -> {
            int ligneSelectionnee = tableLocations.getSelectedRow();
            if (ligneSelectionnee >= 0) {
                int idLocation = (int) modeleTable.getValueAt(ligneSelectionnee, 0);
                Location loc = controleur.rechercherLocationParId(idLocation);
                if (loc != null) {
                    int confirm = JOptionPane.showConfirmDialog(this,
                            "supprimer la location id " + idLocation + " ?",
                            "confirmer", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        if (controleur.supprimerLocation(loc)) {
                            JOptionPane.showMessageDialog(this, "location supprimee.");
                            chargerLocations(magasin);
                        } else {
                            JOptionPane.showMessageDialog(this,
                                    "erreur lors de la suppression",
                                    "erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "veuillez selectionner une location a supprimer.");
            }
        });
        boutons.add(boutonSupprimer);

        centre.add(boutons);

        // bouton accueil
        JButton boutonAccueil = new JButton("accueil");
        boutonAccueil.setFont(new Font("tahoma", Font.PLAIN, 14));
        boutonAccueil.setFocusPainted(false);
        boutonAccueil.setAlignmentX(Component.CENTER_ALIGNMENT);
        boutonAccueil.setMaximumSize(new Dimension(150, 35));
        boutonAccueil.setPreferredSize(new Dimension(150, 35));
        boutonAccueil.addActionListener(e -> {
            fenetrePrincipale.setVisible(true);
            this.dispose();
        });

        centre.add(Box.createRigidArea(new Dimension(0, 10)));
        centre.add(boutonAccueil);

        panel.add(centre, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }

    // methode charger locations
    private void chargerLocations(Magasin_Scooter magasin) {
        modeleTable.setRowCount(0);
        for (Scooter s : magasin.getscooter()) {
            for (Location loc : s.getloc()) {
                Object[] ligne = new Object[] {
                        loc.getid_location(),
                        loc.getclient().getNom() + " " + loc.getclient().getPrenom(),
                        s.getMarque(),
                        loc.getdate_debut(),
                        loc.getdate_fin(),
                        loc.getprix()
                };
                modeleTable.addRow(ligne);
            }
        }
    }
}
