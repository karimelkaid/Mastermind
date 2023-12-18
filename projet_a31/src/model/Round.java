package model;

import view.MastermindGameDisplay;
import view.RoundObserver;

import java.util.*;

public class Round {
    private int _score;
    private boolean endBool=false;
    private int pawnNumber;
    private int combinaisonNumber;
    private int tryNumber;
    private int attemptNumber;
    private List<Combination> attempts;

    private PawnColor[] secretCombination;
    private List<PawnColor> randomTab= new ArrayList<PawnColor>();
    private List<RoundObserver> roundObservers;

    public Round(int pawnNumber, int combinaisonNumber, int tryNumber){
        this.pawnNumber=pawnNumber;
        this.tryNumber=tryNumber;
        this.combinaisonNumber=combinaisonNumber;
        for(PawnColor color : PawnColor.values())
        {
            randomTab.add(color);
            System.out.println(color);
        }

        this.attempts = new ArrayList<>();
        this.attemptNumber=1;
        //this.secretCombination=this.generateCombination();
        this.secretCombination= new PawnColor[]{PawnColor.BLUE, PawnColor.GREEN, PawnColor.RED, PawnColor.ORANGE};

        this.roundObservers= new ArrayList<>();
    }
    /*public Combination generateCombination(){

        PawnColor[] tabCombination = new PawnColor[combinaisonNumber];
        for(int i = 0 ; i<tabCombination.length;i++){
            tabCombination[i]= randomTab.get((int)(Math.random()*(PawnColor.values().length-0))+0);
        }
        for(PawnColor color : tabCombination){
            System.out.println("TabCombi : "+color);
        }
        Combination secretCombination = new Combination(tabCombination);
        return secretCombination;
    }*/
    public void nextTentative() {
        attemptNumber = 4;      // Pour tester le notifyRoundFinish (ça marche)
        if(attemptNumber<tryNumber) {
            System.out.println("Try number : " + attemptNumber);
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
            }
        }
        else {
            System.out.println("No more tries : You lost.");
            notifyRoundFinish();
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
        RoundObserver roundObserver1 = new MastermindGameDisplay();
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

        nextTentative();



        return 90;
    }

    public Combination getCombination(int numCombination) {
        Combination res = this.attempts.get(numCombination);
        return res;
    }

   /* public void startRound(){
        for(int i = 1; i<tryNumber;i--){
            System.out.println("Try number : "+i);
            PawnColor[] tabCombination = new PawnColor[combinaisonNumber];
            for(int j = 0 ; j<tabCombination.length;i++){
                Scanner myObj = new Scanner(System.in);  // Create a Scanner object
                System.out.println("Enter value");

                int choice = Integer.parseInt(myObj.nextLine());  // Read user input
                System.out.println("choice is: " + randomTab.get(choice));  // Output user input
                tabCombination[i]=randomTab.get(choice);
            }
            Combination attempts = new Combination()
        }*/
}

