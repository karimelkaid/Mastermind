package view;

import controller.RoundController;
import model.Combination;
import model.MastermindGame;
import model.PawnColor;
import model.Round;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MastermindGameDisplay extends JFrame implements RoundObserver{
    MastermindGame mastermindGame;
    RoundController roundController;

    JPanel pnlInformations;
    JPanel pnlRound;
    JPanel pnlTextClue;     // Vérifier s'il doit être créé

    public MastermindGameDisplay(MastermindGame mastermindGame) {
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

        configureRoundPanel();

        int PawnNumber = 3;
        int AttemptNumber = 5;
        int CombinationNumber = 6;


        // Affichage de la fenêtre
        setVisible(true);
    }

    public void configureRoundPanel()
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
                JButton btnPawn = new JButton();
                btnPawn.setName("btnPawn"+i+j);
                System.out.println(btnPawn.getName());
                btnPawn.setPreferredSize(new Dimension(50, 50));
                btnPawn.setOpaque(true);
                btnPawn.setBackground(Color.WHITE);
                btnPawn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                btnPawn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        /*if (btnPawn.getBackground().equals(Color.RED)){
                            btnPawn.setBackground(Color.BLUE);
                        }
                        else if (btnPawn.getBackground().equals(Color.BLUE)){
                            btnPawn.setBackground(Color.GREEN);
                        }
                        else if (btnPawn.getBackground().equals(Color.GREEN)){
                            btnPawn.setBackground(Color.YELLOW);
                        }
                        else{
                            btnPawn.setBackground(Color.RED);
                        }
                        btnPawn.repaint();*/

                        int positionCase = getPositionBox(btnPawn.getName());   // Récupère la position de la case dans la combinaison
                        Combination currentAttempt = mastermindGame.getCurrentRound().getCurrentAttempt();
                        RoundController currentRoundController = mastermindGame.getCurrentRoundController();
                        currentRoundController.addPawn( currentAttempt, positionCase, btnPawn.getBackground() );
                    }
                });
                pnlRound.add(btnPawn);
            }
        }

        this.add(pnlRound, BorderLayout.CENTER);


    }

    public int getPositionBox(String name)
    {
        int position = 0;
        String[] parts = name.split("btnPawn");
        String part1 = parts[0]; // btnPawn
        String part2 = parts[1]; // 00
        position = Integer.parseInt(part2.substring(1));
        return position;
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
            System.out.println("Game finished");
        }
        else
        {
            // Code pour passer au prochain round
            System.out.println("Game not finished");
            mastermindGame.nextRound();
        }
    }

    @Override
    public void updateCombination(int numCombination, int boxPosition, PawnColor pawnColor) {
        // J'hésite à donner un id aux combinaisons (ou juste utiliser la position dans la liste)
        System.out.println("Pion de couleur "+pawnColor+" ajouté à la combinaison n°"+numCombination + " en position "+ boxPosition );   // La 1ère combinaison est 0

        // Modifier la couleur du bouton correspondant en utilisant les paramètres
        // Trouver le bouton correspondant
        // Modifier sa couleur
        // Repaint

        JButton btnPawn = null;
        for( Component c : pnlRound.getComponents() )
        {
            if( c instanceof JButton )
            {
                if( c.getName().equals("btnPawn"+numCombination+boxPosition) )
                {
                    btnPawn = (JButton) c;
                }
            }
        }

        if( btnPawn != null )
        {
            if(pawnColor == PawnColor.BLUE)
            {
                btnPawn.setBackground(Color.BLUE);
            }
            else if(pawnColor == PawnColor.GREEN)
            {
                btnPawn.setBackground(Color.GREEN);
            }
            else if( pawnColor == PawnColor.YELLOW )
            {
                btnPawn.setBackground(Color.YELLOW);
            }
            else if( pawnColor == PawnColor.ORANGE )
            {
                btnPawn.setBackground(Color.ORANGE);
            }
            else if( pawnColor == PawnColor.PINK )
            {
                btnPawn.setBackground(Color.PINK);
            }
            else if( pawnColor == PawnColor.WHITE )
            {
                btnPawn.setBackground(Color.WHITE);
            }
            else if( pawnColor == PawnColor.BLACK )
            {
                btnPawn.setBackground(Color.BLACK);
            }
            else
            {
                btnPawn.setBackground(Color.RED);
            }
            btnPawn.repaint();
        }

    }

    @Override
    public void updateCombinationFinish(int numCombination) {
        System.out.println("Combination finished");
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
        }
    }


}
