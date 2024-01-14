
import model.*;
import view.MastermindGameDisplay;
import view.ViewStart;
import view.View_start;

import java.awt.*;

public class Main {
    public static void main(String[] args)
    {
        ViewStart viewStart = new ViewStart();
        MastermindGame mastermindGame = new MastermindGame("Jawad","Easy", 3,4,4,3);


        /*
        Round round = new Round(4,4,4);
        round.play();
         */







        /*MastermindGame mastermindGame = new MastermindGame("jawad", 10,4,4, 10);
        mastermindGame.start();*/
        /*System.out.println(mastermindGame.getScore());

        GenerateCluesStrategy generateCluesStrategy = new GenerateCluesEasy();
        PawnColor[] secretCombination = {PawnColor.ORANGE,PawnColor.BLUE, PawnColor.RED, PawnColor.GREEN};
        Combination combination = new Combination(generateCluesStrategy,secretCombination);
        combination.addPawn(PawnColor.ORANGE);
        combination.addPawn(PawnColor.BLUE);
        combination.addPawn(PawnColor.GREEN);
        combination.addPawn(PawnColor.RED);

        combination.showPawns();
        // Génération des indices
        combination.generateClues();
        // Affichage des indices
        combination.showClues();

        combination.setStrategy( new GenerateCluesNumerical() );

        combination.generateClues();
        combination.showClues();*/

    }
}

/*
        // Choisir une combinaison secrète
        MastermindGame mastermindGame = new MastermindGame();
        mastermindGame.init();  // La combinaison secrète sera directement appelé dans init

        // Lancement partie
        mastermindGame.startGame();*//*


        // Enlever les boucles de préférence
        MastermindGame mastermindGame = new MastermindGame();

        while(recommencer)
        {
            mastermindGame.setSettings( new MastermindSetting("...") );

            for(int i=0; i<nbManche; i++)
            {
                Round round = new Round();
                round.chooseSecretCombination();

                // On crée la liste de "Tentative" qui correspond à une macnhe
                List<Combination> attempts = new List<Combination>();

                for(int k=0; k<nbTentative && trouve == false; i++)
                {
                    round.tentative();  // Écrire com en dur pr tester
                    round.checkTentative();
                }

                // Calcul du score + affichage du score et gagné ou perdu
                mastermindGame.calculScore();
                mastermindGame.showResult();

                // Manche terminé, vidage de la liste pour la prochaine partie
                round.clearAttempts();
            }
        }

        // Ici, le joueur a finit de jouer --> affichage du nb de manches gagnés et nb de manches perdus
        mastermindGame.showFinalResult();

    }
}
*/
