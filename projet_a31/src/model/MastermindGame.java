package model;
import controller.MastermindController;
import controller.RoundController;
import view.MastermindGameDisplay;
import view.MastermindGameObserver;
import view.RoundObserver;
import view.ViewStart;

import java.util.*;

import java.util.List;

public class MastermindGame {

    private String _playerName;
    //private displaymode
    String gameMode;
    private static int _numberOfRounds;
    private int _pawnNumber;
    private int _combinaisonNumber;
    private int _tryNumber;
    private int _score;

    private List<Round> rounds; // Liste des rounds de la partie
    private List<RoundController> roundControllers; // Liste des controllers associés aux rounds
    private List<RoundObserver> roundObservers; // Liste des observers associés aux rounds
    private Round current_round;    // Round en cours
    private RoundController current_roundController;    // Controller associé au round en cours
    private RoundObserver current_roundObserver;    // Observer associé au round en cours

    private List<MastermindGameObserver> mastermindGameObservers; // Liste des observers associés à la partie

    private boolean isGameFinished;

    private PawnColor[] pawnsColors;    // Liste des couleurs des pions disponibles

    private MastermindGameDisplay mastermindGameDisplay;    // Observer associé aux rounds

    public MastermindGame(String _playerName, String gameMode, int _numberOfRounds, int _pawnNumber, int _combinaisonNumber, int _tryNumber){
        this._playerName = _playerName;
        this.gameMode = gameMode;
        this._numberOfRounds = _numberOfRounds;
        this._pawnNumber = _pawnNumber;
        this._combinaisonNumber = _combinaisonNumber;
        this._tryNumber = _tryNumber;
        this._score = 0;
        this.isGameFinished = false;

        // On prend les pawnNumber+1 premières couleurs car le 1er est la couche blanche et ce n'est pas une couleur pour un pion mais une case vide
        pawnsColors = new PawnColor[_pawnNumber+1];
        for(int i=0; i<_pawnNumber+1; i++)
        {
            pawnsColors[i] = PawnColor.values()[i] ;
        }


        this.rounds = new ArrayList<>();
        this.roundControllers = new ArrayList<>();
        this.roundObservers = new ArrayList<>();

        mastermindGameDisplay = new MastermindGameDisplay(this, new MastermindController(this));  // Création de la vue principale

        // Créations du 1er round avant d'instancier la vue
        Round round = new Round( _pawnNumber, _combinaisonNumber, _tryNumber, gameMode, mastermindGameDisplay, 0, pawnsColors );
        rounds.add(round);
        current_round = rounds.get( rounds.size()-1 );

        // Création du Controller associé au 1er Round
        RoundController roundController = new RoundController(round);
        roundControllers.add(roundController);
        current_roundController = roundControllers.get( roundControllers.size()-1 );

        // Création de l'Observer associé au Round
        roundObservers.add(mastermindGameDisplay);
        current_round.addObserver(mastermindGameDisplay);
        current_roundObserver = roundObservers.get( roundObservers.size()-1 );

        this.mastermindGameObservers = new ArrayList<>();
        addMastermindGameObserver(mastermindGameDisplay);
    }

    public void addMastermindGameObserver( MastermindGameObserver mastermindGameObserver )
    {
        mastermindGameObservers.add(mastermindGameObserver);
    }



    public void start(){
        // Affiche view start
        ViewStart viewStart = new ViewStart();


        boolean restart = true;
        //while(restart)
       // {


        //for(int i=0; i<_numberOfRounds; i++) {

            // Affiche view round
            /*Round round = new Round();
            List<String> Pawn = round.chooseSecretCombination();
            int roundScore = round.play();
            this._score += roundScore;
            System.out.println("Winround : " + roundScore);*/
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

    public int getAttemptsNumber()
    {
        return this._tryNumber;
    }
    public int getCombinationNumber()
    {
        return this._combinaisonNumber;
    }


    public boolean isGameOver(){
        return true;
    }
    public int getScore(){
        return _score;
    }

    public void nextRound() {
        // Quand cette méthode est appelée, c'est qu'il y a encore au moins un round à jouer (vérifié dans updateRoundFinish)
        // On crée donc un nouveau round
        newRound();
        // Modification du modèle --> notification des MastermindGameObserver pour qu'ils mettent à jour la vue
        notifyNewRound();
    }

    public void newRound()
    {
        // Création du Round
        Round round = new Round( _pawnNumber, _combinaisonNumber, _tryNumber, gameMode, mastermindGameDisplay, rounds.size(), pawnsColors );
        rounds.add(round);
        current_round = rounds.get( rounds.size()-1 );

        // Création du Controller associé au Round
        RoundController roundController = new RoundController(round);
        roundControllers.add(roundController);
        current_roundController = roundControllers.get( roundControllers.size()-1 );

        // L'Observer est déjà créé dans le constructeur de MastermindGame, on a juste à l'ajouter en tant qu'Observer du nouveau Round
        //RoundObserver mastermindGameDisplay = new MastermindGameDisplay(this);
        //roundObservers.add(mastermindGameDisplay);
        current_round.addObserver(mastermindGameDisplay);
        //current_roundObserver = roundObservers.get( roundObservers.size()-1 );

        // On débloque la 1ère combinaison du nouveau round
        Combination newCombination = current_round.getCurrentAttempt();     // Débloquage de la nouvelle tentative
        newCombination.setBlocked(false);
    }

    private void notifyNewRound()
    {
        for( MastermindGameObserver mastermindGameObserver : mastermindGameObservers )
        {
            mastermindGameObserver.updateNewRound();
        }
    }

    public boolean isGameFinished() {
        boolean res = false;
        if(rounds.size() > _numberOfRounds)
        {
            res = true;
        }
        return res;
    }

    public RoundController getCurrentRoundController() {
        return current_roundController;
    }

    public Round getCurrentRound() {
        return current_round;
    }

    public int getTryNumber() {
        return _tryNumber;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameFinished(boolean gameFinished) {
        this.isGameFinished = gameFinished;
        // Modification du modèle --> notification des MastermindGameObserver pour qu'ils mettent à jour la vue
        notifyGameFinished();
    }

    public void notifyGameFinished() {
        for( MastermindGameObserver mastermindGameObserver : mastermindGameObservers )
        {
            mastermindGameObserver.updateGameFinished();
        }
    }

    public int getNumberEmptyCellsInCurrentCombination() {
        int res = 0;
        Combination currentCombination = current_round.getCurrentAttempt();
        int size = currentCombination.getPawns().length;
        for(int i=0; i<size-1; i++)
        {
            PawnColor currentPawnColor = currentCombination.getPawn(i);
            if( currentPawnColor == null || currentPawnColor == PawnColor.WHITE )
            {
                res++;
            }
        }
        return res;
    }

    public PawnColor[] getPawnsColors() {
        return pawnsColors;
    }

    public int getRoundNumber() {
        return this._numberOfRounds;
    }

    public String getPlayerName() {
        return _playerName;
    }
}
