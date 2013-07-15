package model;

public interface Strategy {
	
	/**
	 * 
	 * @param current roll of the die
	 * @param An array of pawns that have been set to movable or non movable
	 * @param gameBoard
	 * @return Pawn to be moved
	 */
	public Pawn getNextMove(int currentRoll, Pawn[] moveablePawns, Field[] gameBoard);
}
