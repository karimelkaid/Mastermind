package view;

public class MastermindGameDisplay implements RoundObserver{

    @Override
    public void updateRoundFinish() {
        System.out.println("Round finished");
    }
}
