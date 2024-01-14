package view;

import model.MastermindGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndView extends JFrame {
    private JLabel scoreLabel;
    private JLabel resultLabel;
    private JButton replayButton;
    private JButton exitButton;
    private JPanel mainPanel; // Panneau principal
    private MastermindGame mastermindGame;

    public EndView(MastermindGame mastermindGame)
    {
        // Configuration de la fenêtre
        setTitle("Fin de Partie");
        setSize(600, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Pour centrer la fenêtre sur l'écran
        getContentPane().setBackground(Color.BLACK); // Couleur de fond

        this.mastermindGame = mastermindGame;
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(0, 51, 102)); // Couleur de fond du panneau
        GridBagConstraints c = new GridBagConstraints();

        // Configuration du scoreLabel
        scoreLabel = createLabel("Votre score : " + mastermindGame.getScore(), Color.WHITE);
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 0, 10, 0);
        mainPanel.add(scoreLabel, c);

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
                // Quitter l'application
                System.exit(0);
            }
        });
        replayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Je vais relancer une partie (avec les mêmes paramètres)");

                // Création du modèle MastermindGame
                MastermindGame newMastermindGame = new MastermindGame(  mastermindGame.getPlayerName() , mastermindGame.getGameMode(), mastermindGame.getRoundNumber(), mastermindGame.getPawnNumber(), mastermindGame.getCombinationNumber(), mastermindGame.getTryNumber() );
                dispose();  // Fermeture de la fenêtre actuelle
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
}
