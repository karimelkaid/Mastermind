import view.view_end;

import java.awt.*;
public class Main {
    public static void main(String[] args)
    {


        view_end view_end= new view_end(100, true);
    }
        /*// Choisir une combinaison secrète
        MastermindGame mastermindGame = new MastermindGame();
        mastermindGame.init();  // La combinaison secrète sera directement appelé dans init

        // Lancement partie
        mastermindGame.startGame();*/
        /*
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
                    round.tentative();
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

         */
}
