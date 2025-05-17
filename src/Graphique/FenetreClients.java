package src.Graphique;

import javax.swing.*;
import java.awt.*;


public class FenetreClients extends JFrame {
    public FenetreClients(FenetrePrincipale fenetrePrincipale) {
        setTitle("Gestion des Clients");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        JLabel label = new JLabel("🧍 Fenêtre Clients", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(label, BorderLayout.CENTER);

        JButton boutonAccueil = new JButton("🏠 Accueil");
        boutonAccueil.setFont(new Font("Tahoma", Font.BOLD, 14));
        boutonAccueil.setBackground(new Color(59, 89, 182));
        boutonAccueil.setForeground(Color.WHITE);
        boutonAccueil.addActionListener(e -> {
            fenetrePrincipale.setVisible(true);
            this.dispose();
        });

        panel.add(boutonAccueil, BorderLayout.SOUTH);
        add(panel);
    }
}
