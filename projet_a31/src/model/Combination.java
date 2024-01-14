package model;

import view.RoundObserver;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Combination {
    // Tableau de 4 pions + 4 indoces

    private PawnColor[] pawns;
    //private Clue[] tab_clue;
    private String[] clues;

    private GenerateCluesStrategy generateCluesStrategy;
    private PawnColor[] secretCombination;  // Besoin pour générer les indices (en comparant avec la combinaison actuelle)
    private List<RoundObserver> roundObservers;
    private int numCombination;    // Numéro de la combinaison pour identifier la combinaison dans la liste des combinaisons du Round

    private boolean isBlocked;      // Pour savoir si la combinaison est bloquée ou non (si elle est bloquée, on ne peut pas la modifier)

    // numCombination pour pouvoir notifier le RoundObserver en lui passant la bonne combinaison (voir méthode updateAddPawn)
    public Combination(GenerateCluesStrategy generateCluesStrategy, PawnColor[] secretCombination, int numCombination)
    {
        // REMPLACER LE 4 PAR UN PARAMÈTRE DES SETTINGS (que je vais passer en paramètre)
        pawns = new PawnColor[4];
        //tab_clue = new Clue[4];

        clues = new String[4];


        this.generateCluesStrategy = generateCluesStrategy;
        this.secretCombination = secretCombination;
        this.roundObservers = new ArrayList<>();
        this.numCombination = numCombination;

        // De base, nous bloquons toutes les combinaisons sauf la 1ère
        if( this.numCombination == 0 )
        {
            this.isBlocked = false;
        }
        else
        {
            this.isBlocked = true;
        }
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
        // Notification des RoundObservers pour modifier la vue en conséquence
        notifyCombinationIsBlocked(this);
    }

    public void notifyCombinationIsBlocked(Combination combination) {
        for( RoundObserver roundObserver : roundObservers )
        {
            roundObserver.updateCombinationIsBlocked(combination);
        }
    }

    public String[] getClues() {
        return clues;
    }

    public void notifyAddPawn(int boxPosition , PawnColor pawnColorAdd)
    {
        for(RoundObserver roundObserver : roundObservers)
        {
            roundObserver.updateCombination(this.numCombination, boxPosition, pawnColorAdd);
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
            if( pawns[i] == null )
            {
                pawns[i] = pawnColor;
                stop = true;
            }
        }

        // Notification des RoundObservers pour modifier la vue en conséquence
        //notifyAddPawn(pawnColor);
    }

    // Générer les indices
    public void generateClues()
    {
        if( this.generateCluesStrategy instanceof GenerateCluesNumerical )   // Si mode de jeu numérique
        {
            clues = generateCluesStrategy.generateClues(this.pawns, secretCombination);
        }
        else
        {
            //clues_not_numerical = generateCluesStrategy.generateClues(this.tab_pawn, secretCombination);
            clues = generateCluesStrategy.generateClues(this.pawns, secretCombination);
        }

        // Affichage des indices
        showClues();

        // Modèle modifié, on notifie les RoundObservers pour modifier la vue en conséquence
        notifyCluesModified();
    }

    public void notifyCluesModified() {
        for( RoundObserver roundObserver : roundObservers )
        {
            roundObserver.updateCluesModified(this);
        }
    }

    public int getNumCombination() {
        return numCombination;
    }

    public PawnColor getPawn(int i) {
        PawnColor res;
        res = pawns[i];
        return res;
    }

    public void showPawns()
    {
        // Affichage de la combinaison
        for(int i=0; i<4; i++)
        {
            System.out.println(pawns[i]);
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

    public void setPawn(int positionCase, Color newPawnColor) {
        // Conversion de la couleur en PawnColor
        PawnColor pawnColor = PawnColor.RED;
        if( newPawnColor.equals(Color.RED) )
        {
            pawnColor = PawnColor.RED;
        }
        else if( newPawnColor.equals(Color.BLUE) )
        {
            pawnColor = PawnColor.BLUE;
        }
        else if( newPawnColor.equals(Color.GREEN) )
        {
            pawnColor = PawnColor.GREEN;
        }
        else if( newPawnColor.equals(Color.YELLOW) )
        {
            pawnColor = PawnColor.YELLOW;
        }
        else if( newPawnColor.equals(Color.ORANGE) )
        {
            pawnColor = PawnColor.ORANGE;
        }
        else if( newPawnColor.equals(Color.PINK) )
        {
            pawnColor = PawnColor.PINK;
        }
        else if( newPawnColor.equals(Color.WHITE) )
        {
            pawnColor = PawnColor.WHITE;
        }
        else if( newPawnColor.equals(Color.CYAN) )
        {
            pawnColor = PawnColor.CYAN;
        }
        else if( newPawnColor.equals(Color.GRAY) )
        {
            pawnColor = PawnColor.GRAY;
        }

        // Modification de la couleur du pion dans le modèle
        pawns[positionCase] = pawnColor;

        // Notification des RoundObservers pour modifier la vue en conséquence
        notifyAddPawn(positionCase, pawnColor);

        // Si la combinaison est complète, nous notifions les RoundObservers pour qu'ils puissent modifier la vue en conséquence
        if( this.isComplete() )
        {
            // Notification des RoundObservers
            notifyCombinationFinish();


        }
    }

    public void notifyCombinationFinish() {
        for( RoundObserver roundObserver : roundObservers )
        {
            roundObserver.updateCombinationFinish(this);
        }
    }

    public boolean isComplete() {
        boolean res = true;
        for(int i=0; i<4; i++)
        {
            if( pawns[i] == null )
            {
                res = false;
            }
        }

        return res;
    }

    public boolean getIsBlocked() {
        return isBlocked;
    }
}
