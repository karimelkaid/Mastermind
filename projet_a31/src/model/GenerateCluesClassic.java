package model;

public class GenerateCluesClassic implements GenerateCluesStrategy{
    @Override
    public String[] generateClues(PawnColor[] tab_pawn, PawnColor[] secretCombination) {
        String[] res = new String[tab_pawn.length];

        int wellPlaced = 0;
        int misplaced = 0;
        // Variables pour compter le nombre de pions bien placés et mal placés car en mode classic on ne doit pas placer les indices dans l'ordre
        for(int i=0; i<tab_pawn.length; i++)
        {
            if( tab_pawn[i] == secretCombination[i])
            {
                wellPlaced++;
            }
            else
            {
                misplaced++;
            }
        }

        // Dans le tableau résultat, je place d'abord les indices bien placés puis les mal placés
        for(int i=0; i<wellPlaced; i++)
        {
            res[i] = "black";
        }
        for(int i=wellPlaced; i< tab_pawn.length; i++)
        {
            res[i] = "white";
        }

        return res;
    }
}
