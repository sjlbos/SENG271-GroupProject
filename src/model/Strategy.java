package model;

import java.util.ArrayList;

public interface Strategy {
	
	public Pawn getNextMove(int currentRoll, ArrayList<Pawn> moveablePawns, Field[] gameBoard);
	public String toString();
	
}
