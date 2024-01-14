package controller;

import model.MastermindGame;

public class MastermindController {
    private MastermindGame mastermindGame;

    public MastermindController(MastermindGame mastermindGame)
    {
        this.mastermindGame = mastermindGame;
    }


    public void nextRound() {
        mastermindGame.nextRound();
    }

    public void setGameFinished(boolean gameFinished) {
        mastermindGame.setGameFinished(gameFinished);
    }
}
