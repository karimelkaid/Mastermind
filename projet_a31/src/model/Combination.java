package model;

public class Combination {
    // Tableau de 4 pions + 4 indoces

    private PawnColor[] tab_pawn;
    private Clue[] tab_clue;

    private GenerateCluesStrategy generateCluesStrategy;
    private PawnColor[] secretCombination;  // Besoin pour générer les indices (en comparant avec la combinaison actuelle)

    public Combination(GenerateCluesStrategy generateCluesStrategy, PawnColor[] secretCombination)
    {
        // REMPLACER LE 4 PAR UN PARAMÈTRE DES SETTINGS (que je vais passer en paramètre)
        tab_pawn = new PawnColor[4];
        tab_clue = new Clue[4];
        this.generateCluesStrategy = generateCluesStrategy;
        this.secretCombination = secretCombination;
    }

    // Générer les indices
    public void generateClues()
    {
        generateCluesStrategy.generateClues(this.tab_pawn, secretCombination);
    }
}
