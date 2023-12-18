package model;

import view.RoundObserver;

import java.util.ArrayList;
import java.util.List;

public class Combination {
    // Tableau de 4 pions + 4 indoces

    private PawnColor[] tab_pawn;
    //private Clue[] tab_clue;
    private String[] clues;

    private GenerateCluesStrategy generateCluesStrategy;
    private PawnColor[] secretCombination;  // Besoin pour générer les indices (en comparant avec la combinaison actuelle)
    private List<RoundObserver> roundObservers;
    private int numCombination;

    // numCombination pour pouvoir notifier le RoundObserver en lui passant la bonne combinaison (voir méthode updateAddPawn)
    public Combination(GenerateCluesStrategy generateCluesStrategy, PawnColor[] secretCombination, int numCombination)
    {
        // REMPLACER LE 4 PAR UN PARAMÈTRE DES SETTINGS (que je vais passer en paramètre)
        tab_pawn = new PawnColor[4];
        //tab_clue = new Clue[4];

        clues = new String[4];


        this.generateCluesStrategy = generateCluesStrategy;
        this.secretCombination = secretCombination;
        this.roundObservers = new ArrayList<>();
        this.numCombination = numCombination;
    }

    public void notifyAddPawn(PawnColor pawnColorAdd)
    {
        for(RoundObserver roundObserver : roundObservers)
        {
            roundObserver.updateCombination(this.numCombination, pawnColorAdd);
        }
    }

    public void addRoundObserver( RoundObserver roundObserver )
    {
        this.roundObservers.add(roundObserver);
    }

    public void addPawn(PawnColor pawnColor)
    {
        boolean stop = false;
        for(int i=0; i<4 && stop==false; i++)
        {
            // Dès que je vois une case vide, je place le pion
            if( tab_pawn[i] == null )
            {
                tab_pawn[i] = pawnColor;
                stop = true;
            }
        }

        // Notification des RoundObservers pour modifier la vue en conséquence
        notifyAddPawn(pawnColor);
    }

    // Générer les indices
    public void generateClues()
    {
        if( this.generateCluesStrategy instanceof GenerateCluesNumerical )   // Si mode de jeu numérique
        {
            clues = generateCluesStrategy.generateClues(this.tab_pawn, secretCombination);
        }
        else
        {
            //clues_not_numerical = generateCluesStrategy.generateClues(this.tab_pawn, secretCombination);
            clues = generateCluesStrategy.generateClues(this.tab_pawn, secretCombination);
        }
    }

    public PawnColor getPawn(int i) {
        PawnColor res;
        res = tab_pawn[i];
        return res;
    }

    public void showPawns()
    {
        // Affichage de la combinaison
        for(int i=0; i<4; i++)
        {
            System.out.println(tab_pawn[i]);
        }
    }

    public void showClues()
    {
        System.out.println("------");
        // Si le mode de jeu est numérique --> nous affichons la 1ère case UNIQUEMENT (car c'est la seule qui conntient la chaine nécessaire les reste sont vides)
        if( this.generateCluesStrategy instanceof GenerateCluesNumerical )
        {
            System.out.println("Indices (mode numérique) : ");
            System.out.println(clues[0]);
        }
        else
        {
            System.out.println("Indices (mode pas numérique) : ");

            for(int i=0; i<4; i++)
            {
                System.out.print("\t- "+clues[i]);
            }
            System.out.println();
        }
    }

    public void setStrategy(GenerateCluesStrategy generateCluesStrategy) {
        System.out.println("------");
        String strategyName = "";

        if( generateCluesStrategy instanceof GenerateCluesEasy )
        {
            strategyName = "Easy";
        }
        else if( generateCluesStrategy instanceof GenerateCluesNumerical )
        {
            strategyName = "Numerical";
        }

        System.out.println("Je change de stratégie ("+strategyName+")");

        this.generateCluesStrategy = generateCluesStrategy;

    }
}
