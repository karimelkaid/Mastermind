package view;

import controller.MastermindController;
import controller.RoundController;
import model.Combination;
import model.MastermindGame;
import model.PawnColor;
import model.Round;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MastermindGameDisplay extends JFrame implements RoundObserver, MastermindGameObserver{
    private final MastermindController mastermindController;
    MastermindGame mastermindGame;
    RoundController roundController;

    CardLayout cardLayout = new CardLayout();
    JPanel pnlRounds = new JPanel(cardLayout);  // Panel contenant les différents rounds
    JPanel pnlInformations;
    JPanel pnlRound;
    JPanel pnlTextClue;     // Vérifier s'il doit être créé

    public MastermindGameDisplay(MastermindGame mastermindGame, MastermindController mastermindController) {
        super("Mastermind");
        setSize(600, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Pour centrer la fenêtre sur l'écran

        this.mastermindGame = mastermindGame;

        // Instancier le panel pnlInformations avec un BorderLayout
        pnlInformations = new JPanel(new BorderLayout());
        pnlInformations.setBackground(Color.RED);

        // Placer un JLabel dans le panel pnlInformations à l'Est pour le score
        JLabel lblScore = new JLabel("Score : 0");
        pnlInformations.add(lblScore, BorderLayout.EAST);
        // Placer un autre JLabel dans le panel pnlInformations au Centre pour le nom du joueur
        JLabel lblPlayerName = new JLabel("Player name");
        pnlInformations.add(lblPlayerName, BorderLayout.CENTER);
        // Ajouter le panel pnlInformations à la JFrame au Nord
        this.add(pnlInformations, BorderLayout.NORTH);

        configureRoundsPanel();

        int PawnNumber = 3;
        int AttemptNumber = 5;
        int CombinationNumber = 6;

        this.mastermindController = mastermindController;


        // Affichage de la fenêtre
        setVisible(true);
    }

    public void configureRoundsPanel()
    {
        pnlRound = new JPanel();
        pnlRound.setBackground(Color.BLUE);
        pnlRound.setLayout(new GridLayout(mastermindGame.getAttemptsNumber(), mastermindGame.getCombinationNumber()+1));  // +1 pour les indices
        pnlRound.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnlRound.setPreferredSize(new Dimension(600, 600));

        // Ajout d'un JButton pour chaque grille
        for(int i = mastermindGame.getAttemptsNumber() - 1; i >= 0; i--)    // On commence par la dernière ligne (la + haute) pour que les boutons possèdent un nom cohérent
        {
            for(int j = 0; j < mastermindGame.getCombinationNumber() +1; j++)    // pas -1 pour mastermindGame.getCombinationNumber() pour mettre aussi la colonne représentant les indices
            {
                // Si c'est la dernière colonne, on crée les indices
                if( j == mastermindGame.getCombinationNumber() )
                {
                    /*
                    JPanel cluePanel = new JPanel() {
                        @Override
                        protected void paintComponent(Graphics g) {
                            super.paintComponent(g);
                            // Dessiner ici les cercles pour les indices
                            // Exemple : dessiner 4 cercles pour représenter les indices
                            int radius = 10; // Rayon des cercles
                            int padding = 5; // Espacement entre les cercles
                            for (int k = 0; k < mastermindGame.getCombinationNumber(); k++) {
                                // Vous pouvez utiliser une logique pour définir la couleur en fonction des indices
                                g.setColor(Color.BLACK); // Couleur par défaut, vous la changerez plus tard
                                // Calculer la position x et y pour chaque cercle
                                int x = (k % 2) * (2 * radius + padding) + padding;
                                int y = (k / 2) * (2 * radius + padding) + padding;
                                g.fillOval(x, y, 2 * radius, 2 * radius);
                            }
                        }
                    };
                    cluePanel.setPreferredSize(new Dimension(50, 50));
                    cluePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    pnlRound.add(cluePanel); // Ajouter le JPanel des indices à la grille*/

                    // Création du JPanel des indices
                    JPanel pnlClue = new JPanel(new FlowLayout());
                    pnlClue.setName("cluePanel"+i);
                    pnlClue.setPreferredSize(new Dimension(50, 50));
                    pnlClue.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    pnlRound.add(pnlClue); // Ajouter le JPanel des indices à la grille
                }
                else
                {
                    JComboBox<PawnColor> comboColor = new JComboBox<>(PawnColor.values());
                    comboColor.setName("comboColor" + i + j);
                    // Configure le rendu pour afficher les couleurs dans le JComboBox
                    comboColor.setRenderer(new DefaultListCellRenderer() {
                        @Override
                        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                                      boolean isSelected, boolean cellHasFocus) {
                            Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                            if (value instanceof PawnColor) {
                                PawnColor color = (PawnColor) value;
                                renderer.setBackground(getColorFromPawn(color));
                            }
                            return renderer;
                        }
                    });

                    // Configure le JComboBox pour changer la couleur de fond lorsque un élément est sélectionné
                    comboColor.setBackground(getColorFromPawn((PawnColor) comboColor.getSelectedItem()));
                    comboColor.setPreferredSize(new Dimension(50, 50));
                    comboColor.setEnabled(false);   // De base, on bloque toutes les combinaisons sauf la 1ère

                    if (i == 0) {
                        comboColor.setEnabled(true);    // On débloque la 1ère combinaison uniquement
                        addActionListener(comboColor);
                    }

                    pnlRound.add(comboColor);

                }
            }
        }
        String namePanel = "round"+0;   // J'initialise le cardPanel avec le 1er round
        pnlRounds.add(pnlRound, namePanel);

        this.add(pnlRounds, BorderLayout.CENTER);

        //this.add(pnlRound, BorderLayout.CENTER);


    }

    public void removeActionListener(JComboBox cbo)
    {
        for( ActionListener al : cbo.getActionListeners() )
        {
            cbo.removeActionListener(al);
        }
    }
    public void addActionListener(JComboBox cbo)
    {
        cbo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                PawnColor selectedColor = (PawnColor) cb.getSelectedItem();
                cb.setBackground(getColorFromPawn(selectedColor));

                int positionCase = getPositionBox(cbo.getName());   // Récupère la position de la case dans la combinaison
                Combination currentAttempt = mastermindGame.getCurrentRound().getCurrentAttempt();
                RoundController currentRoundController = mastermindGame.getCurrentRoundController();
                PawnColor selectedColorr = (PawnColor) cbo.getSelectedItem();
                currentRoundController.addPawn(currentAttempt, positionCase, getColorFromPawn(selectedColorr));
            }
        });

    }

    private Color getColorFromPawn(PawnColor pawnColor) {
        switch (pawnColor) {
            case RED:
                return Color.RED;
            case ORANGE:
                return Color.ORANGE;
            case GREEN:
                return Color.GREEN;
            case BLUE:
                return Color.BLUE;
            case YELLOW:
                return Color.YELLOW;
            case PINK:
                return Color.PINK;
            case WHITE:
                return Color.WHITE;
            case CYAN:
                return Color.CYAN;
            case GRAY:
                return Color.GRAY;
            default:
                return Color.WHITE;
        }
    }

    public void createRoundPanel(int roundNumber)
    {
        pnlRound = new JPanel();
        pnlRounds.setName("round"+roundNumber);
        pnlRound.setBackground(Color.BLUE);
        pnlRound.setLayout(new GridLayout(mastermindGame.getAttemptsNumber(), mastermindGame.getCombinationNumber()+1));  // +1 pour les indices
        pnlRound.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnlRound.setPreferredSize(new Dimension(600, 600));

        // Ajout d'un JButton pour chaque grille
        for(int i = mastermindGame.getAttemptsNumber() - 1; i >= 0; i--)    // On commence par la dernière ligne (la + haute) pour que les boutons possèdent un nom cohérent
        {
            for(int j = 0; j < mastermindGame.getCombinationNumber() +1; j++)    // pas -1 pour mastermindGame.getCombinationNumber() pour mettre aussi la colonne représentant les indices
            {
                // Si c'est la dernière colonne, on crée les indices
                if( j == mastermindGame.getCombinationNumber() )
                {
                    // Création du JPanel des indices
                    JPanel pnlClue = new JPanel(new FlowLayout());
                    pnlClue.setName("cluePanel"+i);
                    pnlClue.setPreferredSize(new Dimension(50, 50));
                    pnlClue.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    pnlRound.add(pnlClue); // Ajouter le JPanel des indices à la grille
                }
                else
                {
                    JComboBox<PawnColor> comboColor = new JComboBox<>(PawnColor.values());
                    comboColor.setName("comboColor" + i + j);
                    // Configure le rendu pour afficher les couleurs dans le JComboBox
                    comboColor.setRenderer(new DefaultListCellRenderer() {
                        @Override
                        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                                      boolean isSelected, boolean cellHasFocus) {
                            Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                            if (value instanceof PawnColor) {
                                PawnColor color = (PawnColor) value;
                                renderer.setBackground(getColorFromPawn(color));
                            }
                            return renderer;
                        }
                    });

                    // Configure le JComboBox pour changer la couleur de fond lorsque un élément est sélectionné
                    comboColor.setBackground(getColorFromPawn((PawnColor) comboColor.getSelectedItem()));
                    comboColor.setPreferredSize(new Dimension(50, 50));
                    // Valeur sélectionné = white
                    comboColor.setSelectedIndex(0);     // Pour que la couleur sélectionnée soit blanche par défaut

                    if (i == 0) {
                        addActionListener(comboColor);
                    }

                    pnlRound.add(comboColor);

                }
            }
        }
        String namePanel = pnlRound.getName();   // J'initialise le cardPanel avec le 1er round
        pnlRounds.add(pnlRound, namePanel);
    }

    public int getPositionBox(String name)
    {
        int position = 0;
        String[] parts = name.split("comboColor");
        String part1 = parts[0]; // comboColor
        String part2 = parts[1]; // 00
        position = Integer.parseInt(part2.substring(1));
        return position;
    }

    @Override
    public void updateNewRound() {
        System.out.println("New round : " + mastermindGame.getCurrentRound().getRoundNumber());

        // Ajout d'un nouveau panel pour le nouveau round
        createRoundPanel( mastermindGame.getCurrentRound().getRoundNumber() );

        // Affichage du nouveau round en passant à la carte suivante
        cardLayout.next(pnlRounds);
    }

    @Override
    public void updateGameFinished() {
        System.out.println("Game finished");
    }


    /*public static String getColorName(Color color) {
        Map<Color, String> colorNames = new HashMap<>();
        for(PawnColor value:PawnColor.values()){
            colorNames.put((Color) Color.class.getField(value.toString()).get(null));
        }
        colorNames.put(Color.RED, "red");
        colorNames.put(Color.GREEN, "green");
        colorNames.put(Color.BLUE, "blue");
        return colorNames.get(color);
    }*/
        /*super("Mastermind");
        JPanel panel = new JPanel(new GridLayout(0, 9, 0, 50));
        for (int i = 0; i < 27; i++) {
            JLabel label = new CircleLabel();
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setPreferredSize(new Dimension(50, 50));
            label.setOpaque(true);
            label.setBorder(BorderFactory.createMatteBorder(1,0,1,0,Color.BLACK));
            panel.add(label);
        }
        add(panel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        JPanel buttonPanel = new JPanel(new FlowLayout());
        for (int i = 0; i < 5; i++) {
            JButton button = new JButton("Button " + (i + 1));
            buttonPanel.add(button);
        }
        buttonPanel.add(new JSeparator());

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }*/
    public class CircleLabel extends JLabel {
        private Color color = Color.WHITE;

        public void setColor(Color color) {
            this.color = color;
            repaint();
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(Color.BLACK);
            g2d.drawOval(0, 0, getWidth() - 1, getHeight() - 1);
            g2d.setColor(Color.WHITE);
            g2d.fillOval(1, 1, getWidth() - 3, getHeight() - 3);
            g2d.dispose();
        }
    }


    @Override
    public void updateRoundFinish() {
        System.out.println("Round finished");

        // Vérifies si la partie est finie
        if( mastermindGame.isGameFinished() )
        {
            // Code de fin de partie

            // Affichage de la dernière vue en lui transmettant le score et le nom du joueur
            this.mastermindController.setGameFinished(true);
        }
        else
        {
            // Code pour passer au prochain round
            System.out.println("Game not finished");
            MastermindController mastermindController = new MastermindController(mastermindGame);
            mastermindController.nextRound();
        }
    }

    @Override
    public void updateCombination(int numCombination, int boxPosition, PawnColor pawnColor) {
        // J'hésite à donner un id aux combinaisons (ou juste utiliser la position dans la liste)
        System.out.println("Pion de couleur "+pawnColor+" ajouté à la combinaison n°"+numCombination + " en position "+ boxPosition );   // La 1ère combinaison est 0

        // Modifier la couleur de la combobox correspondante en utilisant les paramètres
        // Trouver la combobox correspondant
        // Modifier sa couleur
        // Repaint

        // Trouver la combobox correspondant
        JComboBox<PawnColor> cboColor = null;
        for( Component c : pnlRound.getComponents() )
        {
            if( c instanceof JComboBox )
            {
                if( c.getName().equals("comboColor"+numCombination+boxPosition) )
                {
                    cboColor = (JComboBox<PawnColor>) c;
                }
            }
        }

        if( cboColor != null )
        {
            // Modifier sa couleur
            cboColor.setBackground(getColorFromPawn(pawnColor));
            // Repaint
            cboColor.revalidate();
            cboColor.repaint();
        }
    }

    @Override
    public void updateCombinationFinish(Combination combination) {
        System.out.println("Combination finished");

        /*
        // Génération des indices
        combination.generateClues();
        // Affichage console des indices
        combination.showClues();
        //nextTentative();
        */
        RoundController currentRoundController = mastermindGame.getCurrentRoundController();
        currentRoundController.generateClues( mastermindGame.getCurrentRound().getCurrentAttempt() );

        /*
        // Générer les indices
        RoundController currentRoundController = mastermindGame.getCurrentRoundController();
        currentRoundController.generateClues(numCombination);

        // Je passe à la tentative suivante (si elle existe) ne pas oublier bloquer toutes les autres tentatives sauf celle acutelle
        // Je vérifie si la tentative suivante existe
        Round currentRound = mastermindGame.getCurrentRound();
        if( currentRound.getAttempts().size() > mastermindGame.getTryNumber() )
        {
            // Code pour passer au round suivant (s'il existe)
        }
        else
        {
            // Code pour passer à la tentative suivante

            // Je bloque la tentative actuelle
            // Je passe à la tentative suivante
        }*/
    }

    @Override
    public void updateCluesModified(Combination combination) {
        System.out.println("Clues added");

        // Trouver le JPanel des indices correspondant à la combinaison
        JPanel pnlClue = null;
        for( Component c : pnlRound.getComponents() )
        {
            if( c instanceof JPanel )
            {
                if( c.getName().equals("cluePanel"+combination.getNumCombination()) )
                {
                    pnlClue = (JPanel) c;
                }
            }
        }

        if( pnlClue != null )
        {
            // Ajouter les nouveaux composants
            for( int i = 0; i < combination.getClues().length; i++ )
            {
                //pnlClue.setBackground(Color.YELLOW);
                if( mastermindGame.getGameMode().equals("Easy") )
                {
                    JLabel lblClue = new JLabel();
                    lblClue.setPreferredSize(new Dimension(12, 12));
                    lblClue.setOpaque(true);
                    lblClue.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    if( combination.getClues()[i] == "black" )
                    {
                        lblClue.setBackground(Color.BLACK);
                    }
                    else
                    {
                        lblClue.setBackground(Color.WHITE);
                    }
                    pnlClue.add(lblClue);

                }
            }
            // Pour MAJ le panel des indices (pour afficher les nouveaux composants)
            pnlClue.revalidate();
            pnlClue.repaint();
        }

        // Maintenant que les indices sont ajoutés, je bloque tentative actuelle et je passe à la tentative suivante
        RoundController currentRoundController = mastermindGame.getCurrentRoundController();
        currentRoundController.blockCombination( combination );
        currentRoundController.nextTentative();
    }

    @Override
    public void updateCombinationIsBlocked(Combination combination) {
        if( combination.getIsBlocked() )
        {
            for( Component c : pnlRound.getComponents() )
            {
                if( c instanceof JComboBox )
                {
                    if( c.getName().contains("comboColor"+combination.getNumCombination()) )   // Si le nom du bouton contient btnPawn+numCombination alors c'est un bouton de la combinaison
                    {
                        JComboBox cboColor = (JComboBox) c;

                        // Trouver la couleur sélectionnée
                        PawnColor selectedColor = (PawnColor) cboColor.getSelectedItem();

                        // Créer un JLabel avec la couleur sélectionnée
                        JLabel lblColor = new JLabel();
                        lblColor.setPreferredSize(cboColor.getPreferredSize());
                        lblColor.setOpaque(true);
                        lblColor.setBackground(getColorFromPawn(selectedColor));
                        lblColor.setBorder(cboColor.getBorder()); // Copier la bordure

                        // Obtenir l'index du JComboBox avant de le supprimer
                        int index = pnlRound.getComponentZOrder(cboColor);

                        // Supprimer le JComboBox
                        pnlRound.remove(cboColor);

                        // Ajouter le JLabel à l'index spécifique
                        pnlRound.add(lblColor, index);

                    }
                }
            }

            System.out.println("Combination "+ combination.getNumCombination() +" blocked");
        }
        else
        {
            // J'ajoute les MouseListener aux JComboBoxs de la combinaison pour pouvoir les modifier
            for( Component c : pnlRound.getComponents() )
            {
                if( c instanceof JComboBox )
                {
                    if( c.getName().contains("comboColor"+combination.getNumCombination()) )   // Si le nom du bouton contient btnPawn+numCombination alors c'est un bouton de la combinaison
                    {
                        JComboBox cboColor = (JComboBox) c;
                        addActionListener(cboColor);
                    }
                }
            }


            System.out.println("Combination "+ combination.getNumCombination() +" unblocked");
        }

        /*// Je bloque la combinaison
        // Trouver le JPanel correspondant à la combinaison
        JPanel pnlCombination = null;
        for( Component c : pnlRound.getComponents() )
        {
            if( c instanceof JPanel )
            {
                if( c.getName().equals("combinationPanel"+combination.getNumCombination()) )
                {
                    pnlCombination = (JPanel) c;
                }
            }
        }

        if( pnlCombination != null )
        {
            // Je bloque la combinaison
            pnlCombination.setEnabled(false);
        }*/
    }

    @Override
    public void updateNextAttempt(int attemptNumber) {
        //System.out.println("Next attempt = " + attemptNumber);

        // Ajout des MouseListener aux comboboxs de la nouvelle tentative (pour pouvoir les modifier)
        for( Component c : pnlRound.getComponents() )
        {
            if( c instanceof JComboBox )
            {
                if( c.getName().contains("comboColor"+attemptNumber) )   // Si le nom du bouton contient btnPawn+numCombination alors c'est un bouton de la combinaison
                {
                    JComboBox cboColor = (JComboBox) c;
                    addActionListener(cboColor);
                }
            }
        }


}
}
