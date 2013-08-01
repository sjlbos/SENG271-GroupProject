package model;

import java.util.ArrayList;

public class MoveFirstStrategy implements Strategy {
	
	/**
	 * @param Current Roll of die
	 * @param ArrayList of movable pawns
	 * @param Field[] array for the gameboard
	 * @return Pawn that is the farthest away from the start
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
				if(pawn.getTilesMoved() > temp.getTilesMoved()){
					temp = pawn;
				}
			}
		}
		return temp;
	}
	
	
	/**
	 * @return String for strategy
	 */
	public String toString(){
		return "Move First";
	}

}
