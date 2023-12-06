import java.awt.*;

public class Main {
    public static void main(String[] args)
    {
        /*// Choisir une combinaison secrète
        MastermindGame mastermindGame = new MastermindGame();
        mastermindGame.init();  // La combinaison secrète sera directement appelé dans init

        // Lancement partie
        mastermindGame.startGame();*/

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
