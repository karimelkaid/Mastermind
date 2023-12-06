package model;

import java.awt.*;

public class GenerateCluesEasy implements  GenerateCluesStrategy
{

    @Override
    public void generateClues(PawnColor[] tab_pawn, PawnColor[] secretCombination) {
        // Comparaison entre le tableau des pions de la combinaison actuelle avec la combinaison secrète

        Clue[] res = new Clue[4];

        for(int i=0; i<4; i++)      // Le 4 = nombreElementAPlacerDansUneCombinaison
        {
            // Si les 2 couleurs correspondent --> noir
            if( tab_pawn[i] == secretCombination[i] )
            {
                res[i] = Color.BLACK;
            }
            // Sinon --> blanc
            else
            {

            }
        }

    }
}
