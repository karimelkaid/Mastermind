package model;

import java.awt.*;

public class GenerateCluesEasy implements  GenerateCluesStrategy
{

    @Override
    public String[] generateClues(PawnColor[] tab_pawn, PawnColor[] secretCombination) {
        // Comparaison entre le tableau des pions de la combinaison actuelle avec la combinaison secrète

        String[] res = new String[4];

        for(int i=0; i<4; i++)      // Le 4 = nombreElementAPlacerDansUneCombinaison
        {
            // Si les 2 couleurs correspondent --> noir
            if( tab_pawn[i] == secretCombination[i] )
            {
                res[i] = "black";
            }
            // Sinon --> blanc
            else
            {
                res[i] = "white";
            }
        }

        return res;
    }
}
