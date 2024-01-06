package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ViewStart extends JFrame {

    private CardLayout cardLayout = new CardLayout();
    private JPanel pnl_card = new JPanel(cardLayout);   // Conteneur pour les cartes
    JPanel pnl_main = new JPanel(new GridBagLayout());     // Déclaré en dehors du constructeur pour pouvoir sauvegarder les informations et revenir dessus plus tard si l'utilisateur change de fenêtre
    JPanel pnl_moreOptions = new JPanel(new GridBagLayout());
    JButton buttonMoreOptions = new JButton("More options");

    public ViewStart() {
        super("Game Start");
        setSize(600, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Pour centrer la fenêtre sur l'écran

        configuration_card_panel();

        configuration_top_panel();

        configuration_main_panel();

        configuration_bottom_panel();

        configuration_more_options_panel();


        // Affichage de la fenêtre
        setVisible(true);
    }

    public void configuration_card_panel() {
        // Ajout des cartes au conteneur
        pnl_card.add(pnl_main, "main");
        pnl_card.add(pnl_moreOptions, "moreOptions");

        // Ajout du conteneur au contentPane de la fenêtre
        this.add(pnl_card, BorderLayout.CENTER);
    }

    public void configuration_main_panel()
    {
        // Je veux un arrière plan de cette couleur #003366
        pnl_main.setBackground(new Color(0, 51, 102));
        //pnl_main.setBackground(Color.YELLOW);

        /*
        // Simuler l'image par un label (remplacer par une vraie image plus tard)
        JLabel labelImage = new JLabel("Belle image du mot mastermind");
        labelImage.setOpaque(true);
        labelImage.setHorizontalAlignment(JLabel.CENTER);
        labelImage.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        labelImage.setPreferredSize(new Dimension(300, 150));
        labelImage.setBackground(Color.WHITE);
        */

        // Création du JLabel pour le logo
        ImageIcon logoIcon = new ImageIcon("src/view/images/game_logo2_sf.png");
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setPreferredSize(new Dimension(500, 350));
        logoLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));


        // Création du champ de texte pour le nom du joueur
        JLabel labelPlayerName = new JLabel("Player name");
        JTextField textField = new JTextField(10);
        textField.setPreferredSize(new Dimension(150, 24));
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        textField.setFont(new Font("Arial", Font.BOLD, 15));
        textField.setForeground(Color.BLACK);
        textField.setBackground(Color.WHITE);
        textField.setText("Player name");
        // Ajout d'un évènement sur le champ de texte focusAdapter pour que le texte par défaut est "Player name" et qu'il disparaisse quand on clique dessus
        textField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                textField.setText("");
            }

            public void focusLost(FocusEvent evt) {
                if(textField.getText().equals(""))
                {
                    textField.setText("Player name");
                }
            }
        });



        GridBagConstraints c = new GridBagConstraints();


        // Image ou label
        c.gridx = 0;
        c.gridy = 0;
        //c.gridwidth = 2;
        c.anchor = GridBagConstraints.NORTH;
        c.weighty = 1; // Donner un poids à au moins 1 composant pour occuper l'espace vertical pour que le logo se place réellement en haut (avant quand je mettais top à 0, le logo était centré verticalement)
        c.insets = new Insets(50, 0, 0, 0); // Marge pour l'image
        pnl_main.add(logoLabel, c);

        // Composant invisdible pour espacer le logo et le champ de texte
        /*JLabel lblInvisible1 = new JLabel();
        c.gridx = 0;
        c.gridy = 1;
        c.weighty = 1.0; // Donner un poids pour occuper l'espace vertical
        c.insets = new Insets(0, 0, 0, 0); // Marge pour le champ de texte
        pnl_main.add(lblInvisible1, c);*/

        // Champ de texte pour le nom du joueur
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(-20, 0, 0, 0); // Marge pour le champ de texte
        pnl_main.add(textField, c);
    }

    public void configuration_more_options_panel()
    {
        pnl_moreOptions.setBackground(Color.YELLOW);
        pnl_moreOptions.setOpaque(true);

        JLabel lblGameOptions = new JLabel("Game options");
        lblGameOptions.setFont(new Font("Arial", Font.BOLD, 20));
        lblGameOptions.setHorizontalAlignment(JLabel.CENTER);
        lblGameOptions.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        lblGameOptions.setPreferredSize(new Dimension(300, 50));

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;    // Pour donner + de largeur au label afin qu'il puisse être centré par rapport à la fenêtre
        c.anchor = GridBagConstraints.NORTH;
        c.weighty = 1; // Donner un poids à au moins 1 composant pour occuper l'espace vertical pour que le label se place réellement en haut (avant quand je mettais top à 0, le label était centré verticalement)
        c.insets = new Insets(50, 0, 0, 0); // Marge extérieur
        pnl_moreOptions.add(lblGameOptions, c);


        // --------------------- Mode de jeu -------------------------------
        JLabel lblGameMode = new JLabel("Game mode :");
        lblGameMode.setFont(new Font("Arial", Font.BOLD, 15));
        lblGameMode.setHorizontalAlignment(JLabel.CENTER);
        lblGameMode.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        lblGameMode.setPreferredSize(new Dimension(150, 25));

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.weightx = 1; // Pour que l'élément et les suivants utilisent tout l'espace disponible et se place donc par défaut à gauche
        c.anchor = GridBagConstraints.LINE_START; // Aligner à gauche
        //c.weightx = 1; // Donner un poids à au moins 1 composant pour occuper l'espace horizontal pour que le label se place réellement en haut
        c.insets = new Insets(-200, 0, 0, 0); // Marge extérieur
        pnl_moreOptions.add(lblGameMode, c);


        // Placement de la combobox pour le mode de jeu
        String[] gameMode = {"Easy", "Classic", "Numerical"};
        JComboBox comboBoxGameMode = new JComboBox(gameMode);
        comboBoxGameMode.setPreferredSize(new Dimension(150, 25));

        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(-200, -175, 0, 0); // Marge extérieur
        pnl_moreOptions.add(comboBoxGameMode, c);

        // ----------------------------------------------------------------


        // --------------------- Nombre de manches ------------------------

        // Label
        JLabel lblNbRounds = new JLabel("Number of rounds :");
        lblNbRounds.setFont(new Font("Arial", Font.BOLD, 15));
        lblNbRounds.setHorizontalAlignment(JLabel.CENTER);
        lblNbRounds.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        lblNbRounds.setPreferredSize(new Dimension(150, 25));

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(-300,0,0,0);
        pnl_moreOptions.add(lblNbRounds, c);

        // JSpinner allant de 6 à 12
        JSpinner spinnerNbRounds = new JSpinner(new SpinnerNumberModel(6, 6, 12, 1));
        spinnerNbRounds.setPreferredSize(new Dimension(150, 25));

        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        c.insets = new Insets(-300, -175, 0, 0);
        pnl_moreOptions.add(spinnerNbRounds, c);

        // ----------------------------------------------------------------


        // --------------------- Nombre de pions ------------------------

        // Label
        JLabel lbl_numberOfPawns = new JLabel("Number of pawns :");
        lbl_numberOfPawns.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        lbl_numberOfPawns.setFont(new Font("Arial", Font.BOLD, 15));
        lbl_numberOfPawns.setHorizontalAlignment(JLabel.CENTER);
        lbl_numberOfPawns.setPreferredSize(new Dimension(150, 25));

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        c.insets = new Insets(-400,0,0,0);
        pnl_moreOptions.add(lbl_numberOfPawns, c);

        // JSlider (personnalisé) 8 par défaut, min 4, max 8
        JSlider sliderNbPawns = new JSlider(4, 8, 8);
        sliderNbPawns.setMajorTickSpacing(1);   // Pour afficher les valeurs 4, 5, 6, 7, 8
        sliderNbPawns.setPaintLabels(true); // Pour afficher les valeurs 4, 5, 6, 7, 8
//        sliderNbPawns.setPreferredSize(new Dimension(150, 50));

        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 1;
        c.insets = new Insets(-400, -175, 0, 0);
        pnl_moreOptions.add(sliderNbPawns, c);


        // ----------------------------------------------------------------


        // --------------------- Nombre de pions dans une combinaison ------------------------

        // Label
        JLabel lbl_numberOfPawnsInCombination = new JLabel("Number of pawns in a combination :");
        lbl_numberOfPawnsInCombination.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        lbl_numberOfPawnsInCombination.setFont(new Font("Arial", Font.BOLD, 15));
        lbl_numberOfPawnsInCombination.setHorizontalAlignment(JLabel.CENTER);
        //lbl_numberOfPawnsInCombination.setPreferredSize(new Dimension(150, 25));

        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        c.insets = new Insets(-500,0,0,0);
        pnl_moreOptions.add(lbl_numberOfPawnsInCombination, c);

        // JSlider (personnalisé) 4 par défaut, min , max 6
        JSlider sliderNbPawnsInCombination = new JSlider(4, 6, 4);
        sliderNbPawnsInCombination.setMajorTickSpacing(1);   // Pour afficher les valeurs 4, 5, 6
        sliderNbPawnsInCombination.setPaintLabels(true); // Pour afficher les valeurs 4, 5, 6
//        sliderNbPawnsInCombination.setPreferredSize(new Dimension(150, 50));

        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 1;
        c.insets = new Insets(-500, -100, 0, 0);
        pnl_moreOptions.add(sliderNbPawnsInCombination, c);

        // -----------------------------------------------------------------------------------



        // --------------------- Nombre tentatives ------------------------

        // Label
        JLabel lbl_numberOfAttempts = new JLabel("Number of attempts :");
        lbl_numberOfAttempts.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        lbl_numberOfAttempts.setFont(new Font("Arial", Font.BOLD, 15));
        lbl_numberOfAttempts.setHorizontalAlignment(JLabel.CENTER);
        //lbl_numberOfAttempts.setPreferredSize(new Dimension(150, 25));

        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 1;
        c.insets = new Insets(-600,0,0,0);
        pnl_moreOptions.add(lbl_numberOfAttempts, c);

        // JSlider (personnalisé) 10 par défaut, min 2, max 12
        JSlider sliderNbAttempts = new JSlider(2, 12, 10);
        sliderNbAttempts.setMajorTickSpacing(1);   // Pour afficher les valeurs 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12
        sliderNbAttempts.setPaintLabels(true); // Pour afficher les valeurs 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12
        //sliderNbAttempts.setPreferredSize(new Dimension(150, 50));

        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 1;
        c.insets = new Insets(-600, -175, 0, 0);
        pnl_moreOptions.add(sliderNbAttempts, c);

        // -----------------------------------------------------------------------------------

    }

    public void configuration_top_panel()
    {
        // Configuration du bouton "More options"
        buttonMoreOptions.setPreferredSize(new Dimension(120, 30));
        // Ajout d'un évènement sur le bouton pour changer de carte
        buttonMoreOptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Passage à la carte suivante (pas besoin de vérifier sur quelle carte je suis car je n'en possède que 2 donc la suivante sera forcément la bonne)
                cardLayout.next(pnl_card);
            }
        });

        // Création du topPanel pour le bouton "More options" et positionnement en haut à droite
        JPanel topPanel = new JPanel(new BorderLayout());
        //topPanel.setBackground(Color.BLUE);
        topPanel.add(buttonMoreOptions, BorderLayout.EAST);
        topPanel.setOpaque(true);


        // Ajout du pnl_main avec le bouton "More options" au contentPane du JFrame
        this.add(topPanel, BorderLayout.NORTH);
    }

    private void configuration_bottom_panel()
    {
        // Création et positionnement du bouton "Start" en bas à droite
        JButton buttonStart = new JButton("Start");
        buttonStart.setPreferredSize(new Dimension(100, 40));
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(buttonStart, BorderLayout.EAST);

        // Ajout du pnl_main inférieur au contentPane de la fenêtre
        this.add(bottomPanel, BorderLayout.PAGE_END);
    }
}
