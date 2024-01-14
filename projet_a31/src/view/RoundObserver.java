package view;

import model.Combination;
import model.PawnColor;

public interface RoundObserver
{
    void updateRoundFinish();
    void updateCombination(int numCombination, int boxPosition, PawnColor pawnColor);
    void updateCombinationFinish(Combination combination);

    void updateCluesModified(Combination combination);

    void updateCombinationIsBlocked(Combination combination);

    void updateNextAttempt(int attemptNumber);
}
