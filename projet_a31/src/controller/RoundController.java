package controller;

import model.Combination;
import model.PawnColor;
import model.Round;

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

    public void addPawn(int numCombination, PawnColor pawnColor)
    {
        // Récupération de la combinaison à modifier
        Combination combination = round.getCombination(numCombination);
        combination.addPawn(pawnColor);
    }

    public void generateClues(int numCombination)
    {
        Combination combination = round.getCombination(numCombination);
        combination.generateClues();
    }
}
