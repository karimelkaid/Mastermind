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
    private int _score;

    public MastermindGame(String _playerName, int _numberOfRounds, int _pawnNumber, int _combinaisonNumber, int _tryNumber){
        this._playerName = _playerName;
        this._numberOfRounds = _numberOfRounds;
        this._pawnNumber = _pawnNumber;
        this._combinaisonNumber = _combinaisonNumber;
        this._tryNumber = _tryNumber;
        this._score = 0;
    }



    public void start(){
        // Affiche view start


        boolean restart = true;
        //while(restart)
       // {


        //for(int i=0; i<_numberOfRounds; i++) {

            // Affiche view round
            Round round = new Round();
            List<String> Pawn = round.chooseSecretCombination();
            int roundScore = round.play();
            this._score += roundScore;
            System.out.println("Winround : " + roundScore);
    //}
        // Fait moi ca sans boucle for





        // Affiche view end en lui transmettent le score et le nom du joueur



              //  restart=false;


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


    public boolean isGameOver(){
        return true;
    }
    public int getScore(){
        return _score;
    }
}
