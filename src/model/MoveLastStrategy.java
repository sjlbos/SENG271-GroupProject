package model;

import java.util.ArrayList;

public class MoveLastStrategy implements Strategy{
	
	/**
	 * @param Current Roll of die
	 * @param ArrayList of movable pawns
	 * @param Field[] array for the gameboard
	 * @return Pawn that is the closest to the start
	 */
	@Override
	public Pawn getNextMove(int currentRoll, ArrayList<Pawn> moveablePawns, Field[] gameBoard) {
		Pawn temp = null;
		if(moveablePawns.isEmpty()){
			return temp;
		}else{
			for(Pawn pawn: moveablePawns){
				if(temp == null){
					temp = pawn;
				}
				if(pawn.getTilesMoved() < temp.getTilesMoved()){
					temp = pawn;
				}
			}
		}
		return temp;
	}

	/**
	 * String for given strategy
	 */
	public String toString(){
		return "Move Last";
	}
}
