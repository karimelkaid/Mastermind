package model;

public class MastermindGame {

    private String _playerName;
    //private displaymode
    private int _numberOfRounds;
    private int _pawnNumber;
    private int _combinaisonNumber;
    private int _tryNumber;

    public MastermindGame(String _playerName, int _numberOfRounds, int _pawnNumber, int _combinaisonNumber, int _tryNumber){
        this._playerName = _playerName;
        this._numberOfRounds = _numberOfRounds;
        this._pawnNumber = _pawnNumber;
        this._combinaisonNumber = _combinaisonNumber;
        this._tryNumber = _tryNumber;
    }

    public static void startGame(){
        MastermindGame mastermindGame = new MastermindGame("Jawad", 3,2, 2,3);
        for(int i=0; i<3; i++)
        {}


    }

    public static void makeMove(){

    }

    public boolean isGameOver(){
        return true;
    }
    public static int getScore(){
        return 1;
    }
}
