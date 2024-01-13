package view;

import model.Combination;
import model.PawnColor;

public interface RoundObserver{
    void updateRoundFinish();
    void updateCombination(int numCombination, int boxPosition, PawnColor pawnColor);
    void updateCombinationFinish(int numCombination);

}
