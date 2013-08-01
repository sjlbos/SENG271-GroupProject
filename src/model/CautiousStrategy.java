package model;

import java.util.ArrayList;
import java.util.Random;

public class CautiousStrategy implements Strategy{

	/**
	 * @param Current Roll of die
	 * @param ArrayList of movable pawns
	 * @param Field[] array for the gameboard
	 * @return Pawn that is in the most danger between this turn and the next
	 */
	public Pawn getNextMove(int currentRoll, ArrayList<Pawn> moveablePawns, Field[] gameBoard) {
		for(Pawn pawn: moveablePawns){
			int startPos = pawn.getPosition();
			Player passedHomeOwner = null;
			for(int i=1;i<6;i++){
				startPos = startPos-1 < 0 ? 39 : startPos-1;
				if(gameBoard[startPos%40] instanceof StartTile){
					passedHomeOwner = gameBoard[startPos%40].getForkOwner();
				}
				if(gameBoard[startPos%40].getOccupant() != null && gameBoard[startPos%40].getOccupant() != (passedHomeOwner)){
					if(gameBoard[startPos%40].getOccupant() == pawn.getOwner()){
						return pawn;
					}
				}else{
					continue;
				}
			}
		}
		return moveRandom(moveablePawns);
	}
	
	/**
	 * @param a arrayList of moveable Pawns
	 * @return a  random pawn
	 */
	public Pawn moveRandom(ArrayList<Pawn> moveablePawns){
		Random rand = new Random();
		if(moveablePawns.isEmpty()){
			return null;
		}
		int random = rand.nextInt(moveablePawns.size());
		return moveablePawns.get(random);
	}
	
	public String toString(){
		return "Cautious";
	}

}
