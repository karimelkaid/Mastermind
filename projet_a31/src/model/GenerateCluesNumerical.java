package model;

public class GenerateCluesNumerical implements GenerateCluesStrategy{
    @Override
    public String[] generateClues(PawnColor[] tab_pawn, PawnColor[] secretCombination)
    {
        // En réalité, cette méthode ne doit renvoyer qu'un seul String, mais à cause des nombreux comportement de cette méthode, nous décidons de laisser un tableau mais nous remplirons uniquement la 1ère case
        String[] res = new String[tab_pawn.length];

        int _wellPlaced = 0;
        int _misplaces = 0;

        for(int i=0; i<tab_pawn.length; i++)
        {
            if( tab_pawn[i] == secretCombination[i])
            {
                _wellPlaced++;
            }
            else
            {
                _misplaces++;
            }
        }

        res[0] = "Well placed : " + _wellPlaced;
        res[1] = "Misplaced : " + _misplaces;


        return res;
    }
}
