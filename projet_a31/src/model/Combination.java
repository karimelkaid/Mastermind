package model;

public class Combination {
    // Tableau de 4 pions + 4 indoces

    PawnColor[] tab_pawn;
    Clue[] tab_clue;

    public Combination(PawnColor[] tab_pawn)
    {
        // REMPLACER LE 4 PAR UN PARAMÈTRE DES SETTINGS (que je vais passer en paramètre)
        this.tab_pawn = tab_pawn;
        tab_clue = new Clue[4];
    }
}
