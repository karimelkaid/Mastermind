package view;

import controller.RoundController;
import model.MastermindGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class ViewStart extends JFrame {

    String playerName;
    String gameMode;
    int nbRounds;
    int nbPawns;
    int nbPawnsInCombination;
    int nbAttempts;


    private CardLayout cardLayout = new CardLayout();
    private JPanel pnlCard = new JPanel(cardLayout);   // Conteneur pour les cartes
    JPanel pnlMain = new JPanel(new GridBagLayout());  // Déclaré en dehors du constructeur pour afin de pouvoir y accéder à n'importe quel moment (méthode getOptionsGame() + configuration_card_panel())
    JPanel pnlMoreOptions = new JPanel(new GridBagLayout());
    JButton btnOptions = new JButton("More options");   // Déclaré en dehors du constructeur pour pouvoir modifier son nom à n'importe quel moment

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

    public void getOptionsGame()
    {
        boolean getOtherOptions = true; // Pour savoir si on doit continuer à récupérer les options de jeu ou non
        // Récupération du nom du joueur
        // Parcours des composants du panel principal jusqu'à trouver la TextField grâce à son nom
        for( Component c : pnlMain.getComponents() )
        {
            if( c.getName() != null && c.getName().equals("txtPlayerName") )
            {
                if( ((JTextField)c).getText().equals("") || ((JTextField)c).getText().equals("Player name" ))
                {
                    JOptionPane.showMessageDialog(null, "Please enter a name.");
                    getOtherOptions = false;    // Le nom n'est pas valide, donc ça cela ne sert à rien de continuer à récupérer les options de jeu
                }
                else
                {
                    playerName = ((JTextField)c).getText();
                }
            }
        }

        if( getOtherOptions )
        {
            for( Component c : pnlMoreOptions.getComponents() )
            {
                if( c.getName() != null )   // Nous vérifions que le nom du composant n'est pas null pour éviter une NullPointerException
                {
                    if( c.getName().equals("cboGameMode") )
                    {
                        gameMode = ((JComboBox)c).getSelectedItem().toString();
                    }
                    else if( c.getName().equals("spnNumberOfRounds") )
                    {
                        nbRounds = (int)((JSpinner)c).getValue();
                    }
                    else if( c.getName().equals("sldNumberOfPawns") )
                    {
                        nbPawns = ((JSlider)c).getValue();
                    }
                    else if( c.getName().equals("sldNumberOfPawnsInCombination") )
                    {
                        nbPawnsInCombination = ((JSlider)c).getValue();
                    }
                    else if( c.getName().equals("sldNumberOfAttempts") )
                    {
                        nbAttempts = ((JSlider)c).getValue();
                    }
                }
            }
        }
    }
    public void startGame()
    {
        getOptionsGame();
        JOptionPane.showMessageDialog(this, "player name = "+this.playerName);
        JOptionPane.showMessageDialog(this, "game mode = "+this.gameMode);
        JOptionPane.showMessageDialog(this, "nb rounds = "+this.nbRounds);
        JOptionPane.showMessageDialog(this, "nb pawns = "+this.nbPawns);
        JOptionPane.showMessageDialog(this, "nb pawns in combination = "+this.nbPawnsInCombination);
        JOptionPane.showMessageDialog(this, "nb attempts = "+this.nbAttempts);

        // Nous lançons la partie uniquement si le nom du joueur n'est pas vide
        if( !playerName.equals("") && !playerName.equals("Player name"))
        {
            // Code pour lancer la partie en passant les options de jeu en paramètre

            // Création du modèle MastermindGame
            MastermindGame mastermindGame = new MastermindGame( playerName, gameMode, nbRounds, nbPawns, nbPawnsInCombination, nbAttempts );

            // Création de la vue MastermindGameDisplay en lui donnant le modèle MastermindGame
            //MastermindGameDisplay mastermindGameDisplay = new MastermindGameDisplay(mastermindGame);

        }
    }

    public void configuration_card_panel() {
        // Ajout des cartes au conteneur
        pnlCard.add(pnlMain, "main");
        pnlCard.add(pnlMoreOptions, "moreOptions");

        // Ajout du conteneur au contentPane de la fenêtre
        this.add(pnlCard, BorderLayout.CENTER);
    }

    public void configuration_main_panel()
    {
        pnlMain.setBackground(new Color(0, 51, 102));
        pnlMain.setOpaque(true);


        //pnlMain.setBackground(Color.YELLOW);

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
        JLabel lblLogo = new JLabel(logoIcon);
        lblLogo.setPreferredSize(new Dimension(500, 350));
        //lblLogo.setBorder(BorderFactory.createLineBorder(Color.BLACK));


        // Création du champ de texte pour le nom du joueur
        JTextField txtPlayerName = new JTextField(10);
        txtPlayerName.setName("txtPlayerName");
        txtPlayerName.setBackground( new Color( 0,91,153 ) );
        // Texte : Un blanc cassé ou une teinte très claire de bleu comme #f0f0f0 pour rester doux pour les yeux tout en conservant la lisibilité.
        txtPlayerName.setFont(new Font("Arial", Font.BOLD, 20));
        txtPlayerName.setForeground(Color.WHITE);
        txtPlayerName.setPreferredSize(new Dimension(400, 48));
        txtPlayerName.setHorizontalAlignment(JTextField.CENTER);
        txtPlayerName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtPlayerName.setText("Player name");
        // Ajout d'un évènement sur le champ de texte focusAdapter pour que le texte par défaut est "Player name" et qu'il disparaisse quand on clique dessus
        txtPlayerName.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                txtPlayerName.setText("");
            }

            public void focusLost(FocusEvent evt) {
                if(txtPlayerName.getText().equals(""))
                {
                    txtPlayerName.setText("Player name");
                }
            }
        });

        // Ajout d'un évènement sur le champ de texte pour que quand on le survole, le fond change de couleur (feedback visuel)
        txtPlayerName.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                txtPlayerName.setBackground( new Color( 0,122,204 ) );
            }

            public void mouseExited(MouseEvent evt) {
                txtPlayerName.setBackground(new Color( 0,91,153 ));
            }
        });


        // Ajout d'un évènement sur le champ de texte keyAdapter pour que quand on appuie sur entrée, ça lance la partie
        txtPlayerName.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                // Si on appuie sur entrée, nous appelons la méthode chargée de lancer une partie (qui fait également les vérifications)
                if(evt.getKeyCode() == KeyEvent.VK_ENTER )
                {
                    startGame();
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
        pnlMain.add(lblLogo, c);

        // Composant invisdible pour espacer le logo et le champ de texte
        /*JLabel lblInvisible1 = new JLabel();
        c.gridx = 0;
        c.gridy = 1;
        c.weighty = 1.0; // Donner un poids pour occuper l'espace vertical
        c.insets = new Insets(0, 0, 0, 0); // Marge pour le champ de texte
        pnlMain.add(lblInvisible1, c);*/

        // Champ de texte pour le nom du joueur
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(-20, 0, 0, 0); // Marge pour le champ de texte
        pnlMain.add(txtPlayerName, c);
    }

    public void configuration_more_options_panel()
    {
        pnlMoreOptions.setBackground( new Color( 255, 250, 205 ) );
        pnlMoreOptions.setOpaque(true);

        JLabel lblGameOptions = new JLabel("Game options");
        lblGameOptions.setFont(new Font("Arial", Font.BOLD, 20));
        lblGameOptions.setForeground(new Color(51, 51, 51));    // Couleur du texte
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
        pnlMoreOptions.add(lblGameOptions, c);


        // --------------------- Mode de jeu -------------------------------
        JLabel lblGameMode = new JLabel("Game mode :");
        lblGameMode.setFont(new Font("Arial", Font.BOLD, 15));
        lblGameMode.setForeground(new Color(51, 51, 51));    // Couleur du texte
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
        pnlMoreOptions.add(lblGameMode, c);


        // Placement de la combobox pour le mode de jeu
        String[] gameMode = {"Easy", "Classic", "Numerical"};
        JComboBox comboBoxGameMode = new JComboBox(gameMode);
        comboBoxGameMode.setName("cboGameMode");
        comboBoxGameMode.setPreferredSize(new Dimension(150, 25));

        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(-200, -175, 0, 0); // Marge extérieur
        pnlMoreOptions.add(comboBoxGameMode, c);

        // ----------------------------------------------------------------


        // --------------------- Nombre de manches ------------------------

        // Label
        JLabel lblNbRounds = new JLabel("Number of rounds :");
        lblNbRounds.setFont(new Font("Arial", Font.BOLD, 15));
        lblNbRounds.setForeground(new Color(51, 51, 51));    // Couleur du texte
        lblNbRounds.setHorizontalAlignment(JLabel.CENTER);
        lblNbRounds.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        lblNbRounds.setPreferredSize(new Dimension(150, 25));

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(-300,0,0,0);
        pnlMoreOptions.add(lblNbRounds, c);

        // JSpinner allant de 6 à 12
        JSpinner spinnerNbRounds = new JSpinner(new SpinnerNumberModel(6, 6, 12, 1));
        spinnerNbRounds.setName("spnNumberOfRounds");
        spinnerNbRounds.setPreferredSize(new Dimension(150, 25));

        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        c.insets = new Insets(-300, -175, 0, 0);
        pnlMoreOptions.add(spinnerNbRounds, c);

        // ----------------------------------------------------------------


        // --------------------- Nombre de pions ------------------------

        // Label
        JLabel lbl_numberOfPawns = new JLabel("Number of pawns :");
        lbl_numberOfPawns.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        lbl_numberOfPawns.setFont(new Font("Arial", Font.BOLD, 15));
        lbl_numberOfPawns.setForeground(new Color(51, 51, 51));    // Couleur du texte
        lbl_numberOfPawns.setHorizontalAlignment(JLabel.CENTER);
        lbl_numberOfPawns.setPreferredSize(new Dimension(150, 25));

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        c.insets = new Insets(-400,0,0,0);
        pnlMoreOptions.add(lbl_numberOfPawns, c);

        // JSlider (personnalisé) 8 par défaut, min 4, max 8
        JSlider sliderNbPawns = new JSlider(4, 8, 8);
        sliderNbPawns.setName("sldNumberOfPawns");
        sliderNbPawns.setMajorTickSpacing(1);   // Pour afficher les valeurs 4, 5, 6, 7, 8
        sliderNbPawns.setPaintLabels(true); // Pour afficher les valeurs 4, 5, 6, 7, 8
//        sliderNbPawns.setPreferredSize(new Dimension(150, 50));

        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 1;
        c.insets = new Insets(-400, -175, 0, 0);
        pnlMoreOptions.add(sliderNbPawns, c);


        // ----------------------------------------------------------------


        // --------------------- Nombre de pions dans une combinaison ------------------------

        // Label
        JLabel lbl_numberOfPawnsInCombination = new JLabel("Number of pawns in a combination :");
        lbl_numberOfPawnsInCombination.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        lbl_numberOfPawnsInCombination.setFont(new Font("Arial", Font.BOLD, 15));
        lbl_numberOfPawnsInCombination.setForeground(new Color(51, 51, 51));    // Couleur du texte
        lbl_numberOfPawnsInCombination.setHorizontalAlignment(JLabel.CENTER);
        //lbl_numberOfPawnsInCombination.setPreferredSize(new Dimension(150, 25));

        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        c.insets = new Insets(-500,0,0,0);
        pnlMoreOptions.add(lbl_numberOfPawnsInCombination, c);

        // JSlider (personnalisé) 4 par défaut, min , max 6
        JSlider sliderNbPawnsInCombination = new JSlider(4, 6, 4);
        sliderNbPawnsInCombination.setName("sldNumberOfPawnsInCombination");
        sliderNbPawnsInCombination.setMajorTickSpacing(1);   // Pour afficher les valeurs 4, 5, 6
        sliderNbPawnsInCombination.setPaintLabels(true); // Pour afficher les valeurs 4, 5, 6
//        sliderNbPawnsInCombination.setPreferredSize(new Dimension(150, 50));

        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 1;
        c.insets = new Insets(-500, -100, 0, 0);
        pnlMoreOptions.add(sliderNbPawnsInCombination, c);

        // -----------------------------------------------------------------------------------



        // --------------------- Nombre tentatives ------------------------

        // Label
        JLabel lbl_numberOfAttempts = new JLabel("Number of attempts :");
        lbl_numberOfAttempts.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        lbl_numberOfAttempts.setFont(new Font("Arial", Font.BOLD, 15));
        lbl_numberOfAttempts.setForeground(new Color(51, 51, 51));    // Couleur du texte
        lbl_numberOfAttempts.setHorizontalAlignment(JLabel.CENTER);
        //lbl_numberOfAttempts.setPreferredSize(new Dimension(150, 25));

        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 1;
        c.insets = new Insets(-600,0,0,0);
        pnlMoreOptions.add(lbl_numberOfAttempts, c);

        // JSlider (personnalisé) 10 par défaut, min 2, max 12
        JSlider sliderNbAttempts = new JSlider(2, 12, 10);
        sliderNbAttempts.setName("sldNumberOfAttempts");
        sliderNbAttempts.setMajorTickSpacing(1);   // Pour afficher les valeurs 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12
        sliderNbAttempts.setPaintLabels(true); // Pour afficher les valeurs 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12
        //sliderNbAttempts.setPreferredSize(new Dimension(150, 50));

        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 1;
        c.insets = new Insets(-600, -175, 0, 0);
        pnlMoreOptions.add(sliderNbAttempts, c);

        // -----------------------------------------------------------------------------------

    }

    public void configuration_top_panel()
    {
        // Configuration du bouton "More options"
        btnOptions.setPreferredSize(new Dimension(120, 30));
        // Couleur de fond = #007acc
        btnOptions.setBackground(new Color(0, 122, 204));
        btnOptions.setForeground(Color.WHITE);
        btnOptions.setFont(new Font("Arial", Font.BOLD, 15));
        btnOptions.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btnOptions.setFocusPainted(false); // Pour enlever le contour bleu autour du texte du bouton quand on clique dessus
        btnOptions.setOpaque(true); // Pour que la couleur de fond soit appliquée



        // Ajout d'un évènement sur le bouton pour changer de carte
        btnOptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Passage à la carte suivante
                cardLayout.next(pnlCard);
                if( btnOptions.getText().equals("More options") )
                {
                    btnOptions.setText("Less options");
                }
                else
                {
                    btnOptions.setText("More options");
                }
            }
        });

        // Création du topPanel pour le bouton "More options" et positionnement en haut à droite
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground( new Color(0, 51, 102) );
        topPanel.add(btnOptions, BorderLayout.EAST);
        topPanel.setOpaque(true);

        // Changement de couleur du bouton "More options" lors du survol, du clic et du relâchement
        // Mises en évidence et hover : Pour le survol des boutons, vous pouvez assombrir légèrement la couleur ou utiliser une couleur complémentaire comme un orange doux #f78c40 pour attirer l'attention sans agresser visuellement.
        btnOptions.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                btnOptions.setBackground( new Color(247,140,64) ); // Couleur lors du survol
            }

            public void mouseExited(MouseEvent evt) {
                btnOptions.setBackground(new Color( 0,122,204 )); // Couleur par défaut
            }

            public void mousePressed(MouseEvent evt) {
                btnOptions.setBackground(Color.DARK_GRAY); // Couleur lors du clic
            }

            public void mouseReleased(MouseEvent evt) {
                btnOptions.setBackground(UIManager.getColor("control")); // Retour à la couleur par défaut après relâchement
            }
        });


        // Ajout du pnlMain avec le bouton "More options" au contentPane du JFrame
        this.add(topPanel, BorderLayout.NORTH);
    }

    private void configuration_bottom_panel()
    {
        // Création et positionnement du bouton "Start" en bas à droite
        ImageIcon imageIcon = new ImageIcon("src/view/images/imgPlay2.png");
        JButton btnStart = new JButton(imageIcon);
        //btnStart.setBackground(new Color(0, 122, 204));
        btnStart.setBackground(new Color(0, 51, 102));
        btnStart.setForeground(Color.WHITE);
        btnStart.setFont(new Font("Arial", Font.BOLD, 15));
        btnStart.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102)));
        btnStart.setFocusPainted(false); // Pour enlever le contour bleu autour du texte du bouton quand on clique dessus
        //btnStart.setOpaque(true); // Pour que la couleur de fond soit appliquée
        //btnStart.setPreferredSize(new Dimension(100, 40));
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground( new Color(0, 51, 102) );
        bottomPanel.add(btnStart, BorderLayout.EAST);

        // Changement de couleur du bouton "Start" lors du survol, du clic et du relâchement
        btnStart.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                btnStart.setBackground( new Color(247,140,64) ); // Couleur lors du survol
            }

            public void mouseExited(MouseEvent evt) {
                btnStart.setBackground(new Color(0, 51, 102)); // Couleur par défaut
            }

            public void mousePressed(MouseEvent evt) {
                btnStart.setBackground(Color.DARK_GRAY); // Couleur lors du clic
            }

            public void mouseReleased(MouseEvent evt) {
                btnStart.setBackground(new Color(0, 122, 204)); // Retour à la couleur par défaut après relâchement
            }
        });

        // Ajout d'un évènement sur le bouton "Start" pour lancer la partie
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        // Ajout du pnlMain inférieur au contentPane de la fenêtre
        this.add(bottomPanel, BorderLayout.PAGE_END);
    }
}
