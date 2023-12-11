package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Round {
    private int _score;
    private boolean endBool=false;
    private int pawnNumber;
    private int combinaisonNumber;
    private int tryNumber;
    private int attemptNumber;
    private Combination attempt;
    private Combination secretCombination;
    private List<PawnColor> randomTab= new ArrayList<PawnColor>();

    Round(int pawnNumber,int combinaisonNumber,int tryNumber){
        this.pawnNumber=pawnNumber;
        this.tryNumber=tryNumber;
        this.combinaisonNumber=combinaisonNumber;
        for(PawnColor color : PawnColor.values()){
            randomTab.add(color);
            System.out.println(color);
        }
        this.attemptNumber=1;
        this.secretCombination=this.generateCombination();
    }
    public Combination generateCombination(){

        PawnColor[] tabCombination = new PawnColor[combinaisonNumber];
        for(int i = 0 ; i<tabCombination.length;i++){
            tabCombination[i]= randomTab.get((int)(Math.random()*(PawnColor.values().length-0))+0);
        }
        for(PawnColor color : tabCombination){
            System.out.println("TabCombi : "+color);
        }
        Combination secretCombination = new Combination(tabCombination);
        return secretCombination;
    }
    public void nextTentative() {
        if(attemptNumber<tryNumber) {
            System.out.println("Try number : " + attemptNumber);
            if (attempt.getCombination == secretCombination.getCombination) {
                System.out.println("Round won!");
                endBool = true;
            } else {
                attempt.showClues();
                attemptNumber++;
            }
        }
        else {
            System.out.println("No more tries : You lost.");
        }
    }
    public void inputCombination(Combination combi) {
        attempt=combi;
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

}
