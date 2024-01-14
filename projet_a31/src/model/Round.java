package model;

import view.MastermindGameDisplay;
import view.RoundObserver;

import javax.swing.*;
import java.util.*;

import static java.lang.constant.ConstantDescs.NULL;

public class Round {
    private final String gameMode;
    private int _score;
    private boolean endBool=false;
    private int pawnNumber;
    private int combinaisonNumber;
    private int tryNumber;
    private int roundNumber;    // Pour nommer les panels des rounds dans la vue principale
    private int attemptNumber;  // Numéro de la tentative en cours
    private List<Combination> attempts;

    private PawnColor[] secretCombination;
    private List<PawnColor> pawnsColors= new ArrayList<PawnColor>();
    private List<RoundObserver> roundObservers;

    public Round(int pawnNumber, int combinaisonNumber, int tryNumber, String gameMode, RoundObserver mastermindGameDisplay, int roundNumber, List<PawnColor> pawnsColors)
    {
        this.pawnNumber=pawnNumber;
        this.tryNumber=tryNumber;
        this.combinaisonNumber=combinaisonNumber;
        this.gameMode = gameMode;
        /*for(PawnColor color : PawnColor.values())
        {
            pawnsColors.add(color);
            System.out.println(color);
        }*/
        this.pawnsColors = pawnsColors;

        this.roundNumber = roundNumber;

        this.attempts = new ArrayList<>();
        this.attemptNumber=1;
        generateCombination();
        //this.secretCombination= new PawnColor[]{PawnColor.BLUE, PawnColor.GREEN, PawnColor.RED, PawnColor.ORANGE};

        this.roundObservers= new ArrayList<>();

        // ------------------------------------------------
        // Ajout des combinaisons
        for(int i=0; i<tryNumber; i++)
        {
            GenerateCluesStrategy generateCluesStrategy = null;
            if( gameMode.equals("Easy") )
            {
                generateCluesStrategy = new GenerateCluesEasy();
            }
            else if( gameMode.equals("Numerical") )
            {
                generateCluesStrategy = new GenerateCluesNumerical();
            }
            else
            {
                generateCluesStrategy = new GenerateCluesClassic();
            }
            Combination combination = new Combination(generateCluesStrategy, this.secretCombination, i, combinaisonNumber);
            combination.addRoundObserver( mastermindGameDisplay );
            inputCombination(combination);
        }
        // ------------------------------------------------

    }

    public List<PawnColor> getPawnsColors() {
        return new ArrayList<>(pawnsColors);
    }

    private void generateCombination()
    {
        this.secretCombination = new PawnColor[combinaisonNumber];
        List<PawnColor> coloursAlreadyChosen = new ArrayList<>();   // Liste des couleurs déjà choisies pour ne pas les rechoisir (on veut des couleurs toutes différentes)
        for(int i = 0 ; i<secretCombination.length;i++){
            this.secretCombination[i]= pawnsColors.get((int)(Math.random()*(PawnColor.values().length-0))+0);
            // Si on tire le blanc ou la couleur tirée est déjà choisie, on recommence
            if( this.secretCombination[i] == PawnColor.WHITE || coloursAlreadyChosen.contains(this.secretCombination[i]) )
            {
                this.secretCombination[i] = PawnColor.WHITE;
                i--;
            }
            else
            {
                coloursAlreadyChosen.add(this.secretCombination[i]);
            }
        }
        for(PawnColor color : secretCombination){
            System.out.println("secretCombination : "+color);
        }


    }

    public int getRoundNumber() {
        return roundNumber;
    }

    // Retourne la tentative en cours
    public Combination getCurrentAttempt() {
        return attempts.get(attemptNumber-1);
    }

    /*public Combination generateCombination(){

        PawnColor[] tabCombination = new PawnColor[combinaisonNumber];
        for(int i = 0 ; i<tabCombination.length;i++){
            tabCombination[i]= pawnsColors.get((int)(Math.random()*(PawnColor.values().length-0))+0);
        }
        for(PawnColor color : tabCombination){
            System.out.println("TabCombi : "+color);
        }
        Combination secretCombination = new Combination(tabCombination);
        return secretCombination;
    }*/
    public void nextTentative() {
        //attemptNumber = 4;      // Pour tester le notifyRoundFinish (ça marche)
        if(attemptNumber<tryNumber) {
            //System.out.println("Try number : " + attemptNumber);
            if (verifyCombination(attempts.get(attemptNumber-1)))
            {
                System.out.println("Round won!");
                notifyRoundFinish();
                endBool = true; // Je pense qu'il ne va pas servir
            }
            else
            {
               // attempts.showClues();
                attemptNumber++;

                Combination newCombination = getCurrentAttempt();     // Débloquage de la nouvelle tentative
                newCombination.setBlocked(false);



                notifyNextAttempt();
            }
        }
        else {
            System.out.println("No more tries : You lost.");
            notifyRoundFinish();
        }
    }

    public void notifyNextAttempt() {
        for(RoundObserver roundObserver : roundObservers) {
            roundObserver.updateNextAttempt(attemptNumber);
        }
    }

    public boolean verifyCombination(Combination attempt)
    {
        boolean verify = true;
        for(int i=0; i<4 && verify==true; i++) {
            if (attempt.getPawn(i)!=this.secretCombination[i]) {
                verify = false;
            }
        }
        return verify;
    }

    public void notifyRoundFinish() {
        //notifier l'observeur que le round est fini
        for(RoundObserver roundObserver : roundObservers) {
            roundObserver.updateRoundFinish();
        }
    }

    public void addObserver( RoundObserver roundObserver )
    {
        this.roundObservers.add(roundObserver);
    }
    public void inputCombination(Combination combi) {
        attempts.add(combi);
    }

    // Return le score du round
    public int play() {
        /*RoundObserver roundObserver1 = new MastermindGameDisplay();
        this.roundObservers.add(roundObserver1);

        //Creer combinaison (la bonne)
        //Appeler next tentative
        GenerateCluesStrategy generateCluesStrategy = new GenerateCluesNumerical();
        Combination combination = new Combination(generateCluesStrategy,this.secretCombination,0);
        combination.addRoundObserver(roundObserver1);

        combination.addPawn(PawnColor.BLUE);
        combination.addPawn(PawnColor.GREEN);
        combination.addPawn(PawnColor.RED);
        combination.addPawn(PawnColor.ORANGE);

        // Ajout de la combinaison
        inputCombination(combination);

        // Génération des indices
        combination.generateClues();
        // Affichage des indices
        combination.showClues();

        nextTentative();*/



        return 90;
    }

    public Combination getCombination(int numCombination) {
        Combination res = this.attempts.get(numCombination);
        return res;
    }

    public List<Combination> getAttempts() {
        return new ArrayList<>(attempts);
    }

   /* public void startRound(){
        for(int i = 1; i<tryNumber;i--){
            System.out.println("Try number : "+i);
            PawnColor[] tabCombination = new PawnColor[combinaisonNumber];
            for(int j = 0 ; j<tabCombination.length;i++){
                Scanner myObj = new Scanner(System.in);  // Create a Scanner object
                System.out.println("Enter value");

                int choice = Integer.parseInt(myObj.nextLine());  // Read user input
                System.out.println("choice is: " + pawnsColors.get(choice));  // Output user input
                tabCombination[i]=pawnsColors.get(choice);
            }
            Combination attempts = new Combination()
        }*/
}

