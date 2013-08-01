package model;

import java.util.*;

public class RandomStrategy implements Strategy {
	
	/**
	 * @param Current Roll of die
	 * @param ArrayList of movable pawns
	 * @param Field[] array for the gameboard
	 * @return Random pawn
	 */
	@Override
	public Pawn getNextMove(int currentRoll, ArrayList<Pawn> moveablePawns, Field[] gameBoard) {
		Random rand = new Random();
		if(moveablePawns.isEmpty()){
			return null;
		}
		int random = rand.nextInt(moveablePawns.size());
		return moveablePawns.get(random);
	}

	/**
	 * String for the given Strategy
	 */
	public String toString(){
		return "Random";
	}
}
