package model;

import java.util.Random;
import java.util.ArrayList;

public class CaptureStrategy implements Strategy{

	public Pawn getNextMove(int currentRoll, ArrayList<Pawn> moveablePawns, Field[] gameBoard) {
		//go through each pawn and check the ones that can be moved
		for(Pawn pawn: moveablePawns){
			//return a pawn if it would catch a pawn
			int pos = pawn.getPosition();
			int finalPos = pos+currentRoll;
			if(finalPos >= 40){
				finalPos = finalPos - 40;
			}
			if(gameBoard[finalPos].getOccupant() != null){
				return pawn;
			}
		}
		//if no suitable moves, move random
		return moveRandom(currentRoll, moveablePawns, gameBoard);
		
	}
	
	/**
	 * Returns a random pawn, of movable ones
	 * @param currentRoll
	 * @param moveablePawns
	 * @param gameBoard
	 * @return
	 */
	public Pawn moveRandom(int currentRoll, ArrayList<Pawn> moveablePawns, Field[] gameBoard){
		Random rand = new Random();
		if(moveablePawns.isEmpty()){
			return null;
		}
		int random = rand.nextInt(moveablePawns.size());
		return moveablePawns.get(random);
	}
	
	public String toString(){
		return "Capture";
	}
}

