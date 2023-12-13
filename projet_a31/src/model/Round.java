package model;

import view.RoundObserver;

import java.util.*;

public class Round {
    private int _score;
    private boolean endBool=false;
    private int pawnNumber;
    private int combinaisonNumber;
    private int tryNumber;
    private int attemptNumber;
    private Combination attempt;     // ICI Changer pour plus tard

    private PawnColor[] secretCombination;
    private List<PawnColor> randomTab= new ArrayList<PawnColor>();
    private List<RoundObserver> roundObservers;

    public Round(int pawnNumber, int combinaisonNumber, int tryNumber){
        this.pawnNumber=pawnNumber;
        this.tryNumber=tryNumber;
        this.combinaisonNumber=combinaisonNumber;
        for(PawnColor color : PawnColor.values()){
            randomTab.add(color);
            System.out.println(color);
        }
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
        if(attemptNumber<tryNumber) {
            System.out.println("Try number : " + attemptNumber);
            if (verifyCombination(attempt)) {
                System.out.println("Round won!");
                notifyRoundFinish();
                endBool = true;
            } else {
               // attempt.showClues();
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
    public void inputCombination(Combination combi) {
        attempt=combi;
    }

    public int play() {
        //Creer combinaison (la bonne)
        //Appeler next tentative
        GenerateCluesStrategy generateCluesStrategy = new GenerateCluesNumerical();
        PawnColor[] secetCombinationTest = {PawnColor.BLUE, PawnColor.GREEN, PawnColor.RED, PawnColor.ORANGE};
        Combination combination = new Combination(generateCluesStrategy,secetCombinationTest);
        combination.addPawn(PawnColor.BLUE);
        combination.addPawn(PawnColor.GREEN);
        combination.addPawn(PawnColor.RED);
        combination.addPawn(PawnColor.ORANGE);

        // Génération des indices
        combination.generateClues();
        // Affichage des indices
        combination.showClues();

        nextTentative();



        return 90;
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
            Combination attempt = new Combination()
        }*/
}

