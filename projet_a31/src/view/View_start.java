package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View_start extends JFrame
{
    //- _playerName : String
    //    - _gameMode : displayMode
    //    - _numberOfRounds : Integer
    //    - _pawnNumber : Integer
    //    - _combinaisonNumber : Integer
    //    - _tryNumber : Integer

    private JTextField playerNameField;
    private JComboBox<String> gameModeComboBox;
    private JTextField numberOfRoundsField;
    private JTextField pawnNumberField;
    private JTextField combinaisonNumberField;
    private JTextField tryNumberField;

    public View_start() {
        setTitle("Game Start");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Player Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("Player Name:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        playerNameField = new JTextField(15);
        mainPanel.add(playerNameField, gbc);

        // Game Mode
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(new JLabel("Game Mode:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        String[] gameModes = {"Mode 1", "Mode 2", "Mode 3"}; // Replace with your game modes
        gameModeComboBox = new JComboBox<>(gameModes);
        mainPanel.add(gameModeComboBox, gbc);

        // Number of Rounds
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(new JLabel("Number of Rounds:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        numberOfRoundsField = new JTextField(5);
        mainPanel.add(numberOfRoundsField, gbc);

        // Pawn Number
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(new JLabel("Pawn Number:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        pawnNumberField = new JTextField(5);
        mainPanel.add(pawnNumberField, gbc);

        // Combinaison Number
        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(new JLabel("Combinaison Number:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        combinaisonNumberField = new JTextField(5);
        mainPanel.add(combinaisonNumberField, gbc);

        // Try Number
        gbc.gridx = 0;
        gbc.gridy = 5;
        mainPanel.add(new JLabel("Try Number:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        tryNumberField = new JTextField(5);
        mainPanel.add(tryNumberField, gbc);

        // Start Game Button
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();

            }
        });
        mainPanel.add(startButton, gbc);

        add(mainPanel);

        setVisible(true);
    }
    public void startGame() {

        String playerName = playerNameField.getText();
        String selectedGameMode = (String) gameModeComboBox.getSelectedItem();
        int numberOfRounds = Integer.parseInt(numberOfRoundsField.getText());
        int pawnNumber = Integer.parseInt(pawnNumberField.getText());
        int combinaisonNumber = Integer.parseInt(combinaisonNumberField.getText());
        int tryNumber = Integer.parseInt(tryNumberField.getText());

        System.out.println(playerName);


        // For example:  game = new Game(playerName, selectedGameMode, numberOfRounds, pawnNumber, combinaisonNumber, tryNumber);
        // game.start();
    }

}
