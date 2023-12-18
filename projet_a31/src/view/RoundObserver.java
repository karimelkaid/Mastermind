package view;

import model.Combination;
import model.PawnColor;

public interface RoundObserver{
    public void updateRoundFinish();
    void updateCombination(int numCombination, PawnColor pawnColor);

}
