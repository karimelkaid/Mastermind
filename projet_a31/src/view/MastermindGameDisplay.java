package view;

import model.Combination;
import model.PawnColor;

public class MastermindGameDisplay implements RoundObserver{

    @Override
    public void updateRoundFinish() {
        System.out.println("Round finished");
    }

    @Override
    public void updateCombination(int numCombination, PawnColor pawnColor) {
        // J'hésite à donner un id aux combinaisons (ou juste utiliser la position dans la liste)
        System.out.println("Pion de couleur "+pawnColor+" ajouté à la combinaison n°"+numCombination);   // La 1ère combinaison est 0
    }


}
