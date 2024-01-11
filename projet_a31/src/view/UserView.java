import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserView extends JFrame {
    private JLabel scoreLabel;
    private JLabel resultLabel;
    private JButton replayButton;
    private JButton exitButton;
    private JPanel mainPanel; // Panneau principal

    public UserView(int score, boolean hasWon) {
        // Configuration de la fenêtre
        setTitle("Fin de Partie");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.BLACK); // Couleur de fond

        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(0, 51, 102)); // Couleur de fond du panneau
        GridBagConstraints c = new GridBagConstraints();

        // Configuration du scoreLabel
        scoreLabel = createLabel("Votre score : " + score, Color.WHITE);
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 0, 10, 0);
        mainPanel.add(scoreLabel, c);

        // Configuration du resultLabel
        String resultText = hasWon ? "Vous avez gagné !" : "Vous avez perdu.";
        resultLabel = createLabel(resultText, hasWon ? Color.GREEN : Color.RED);
        c.gridy = 1;
        mainPanel.add(resultLabel, c);

        // Configuration des boutons
        replayButton = createButton("Rejouer");
        exitButton = createButton("Quitter");
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(new Color(0, 51, 102));
        buttonPanel.add(replayButton);
        buttonPanel.add(exitButton);
        c.gridy = 2;
        mainPanel.add(buttonPanel, c);

        // Ajout de l'action pour le bouton "Quitter"
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logique pour quitter l'application
                System.exit(0);
            }
        });
        replayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logique pour le bouton (à définir selon les besoins)
            }
        });

        add(mainPanel);
        setVisible(true);
    }

    private JLabel createLabel(String text, Color color) {
        JLabel label = new JLabel(text);
        label.setForeground(color);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setHorizontalAlignment(JLabel.CENTER);
        return label;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        styleButton(button);
        return button;
    }

    private void styleButton(JButton button) {
        button.setForeground(Color.BLACK); // Couleur du texte en blanc
        button.setBackground(new Color(0, 102, 204)); // Fond bleu foncé
        button.setFont(new Font("Arial", Font.BOLD, 14)); // Police Arial, gras, taille 14
        button.setPreferredSize(new Dimension(100, 40)); // Taille préférée (largeur: 100, hauteur: 40)
        button.setFocusPainted(false); // Désactiver la mise en évidence du focus


    }



    public static void main(String[] args) {
        new UserView(100, false); // Exemple d'utilisation
    }
}
