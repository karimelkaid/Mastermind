package model;
import java.util.*;

import java.util.List;

public class MastermindGame {

    private String _playerName;
    //private displaymode
    private static int _numberOfRounds;
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

    public static void init(String _playerName, int _numberOfRounds, int _pawnNumber, int _combinaisonNumber, int _tryNumber){
        MastermindGame mastermindGame = new MastermindGame(_playerName, _numberOfRounds,_pawnNumber, _combinaisonNumber,_tryNumber);
        System.out.println("initialisation de la game");
    }

    public static void start(){
        boolean restart = true;
        //while(restart)
        //{
            //for(int i=0; i<_numberOfRounds; i++) {
                Round round = new Round();
                List<String> Pawn = round.chooseSecretCombination();
                for (int j=0; j<4; j++){
                    System.out.println("Pawn : "+Pawn.get(j));
                }

            //}



                restart=false;


                // On crée la liste de "Tentative" qui correspond à une macnhe
//                List<Combination> attempts = new List<Combination>();
//
//                for(int k=0; k<nbTentative && trouve == false; i++)
//                {
//                    round.tentative();
//                    round.checkTentative();
//                }
//
//                // Calcul du score + affichage du score et gagné ou perdu
//                mastermindGame.calculScore();
//                mastermindGame.showResult();
//
//                // Manche terminé, vidage de la liste pour la prochaine partie
//                round.clearAttempts();
            //}

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
