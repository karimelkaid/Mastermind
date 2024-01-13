package controller;

import model.Combination;
import model.PawnColor;
import model.Round;

import java.awt.*;

public class RoundController {
    private Round round;

    public RoundController(Round round)
    {
        this.round = round;
    }

    public void makeSecretCombination(int numCombination)
    {
        Combination combination = round.getCombination(numCombination);
        // Appel de la méthode pour générer la combinaison secrète pour un Round
    }

    public void addPawn(Combination currentAttempt, int positionCase, Color currentPawnColor)
    {
        Color newPawnColor = currentPawnColor;
        if (currentPawnColor.equals(Color.RED)){
            newPawnColor = Color.BLUE;
        }
        else if (currentPawnColor.equals(Color.BLUE)){
            newPawnColor = Color.GREEN;
        }
        else if (currentPawnColor.equals(Color.GREEN)){
            newPawnColor = Color.YELLOW;
        }
        else{
            newPawnColor = Color.RED;
        }

        // Modification de la couleur du pion dans le modèle
        currentAttempt.setPawn(positionCase, newPawnColor);

    }


    public void generateClues(int numCombination)
    {
        Combination combination = round.getCombination(numCombination);
        combination.generateClues();
    }
}
