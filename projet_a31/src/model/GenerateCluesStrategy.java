package model;

public interface GenerateCluesStrategy
{
    String[] generateClues(PawnColor[] tab_pawn, PawnColor[] secretCombination);
}
