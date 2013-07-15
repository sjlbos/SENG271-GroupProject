package model;

import java.util.Random;

public class CaptureStrategy implements Strategy{

	public Pawn getNextMove(int currentRoll, Pawn[] moveablePawns, Field[] gameBoard) {
		//go through each pawn and check the ones that can be moved
		for(Pawn pawn: moveablePawns){
			if(pawn.getIsMoveable()){
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
	public Pawn moveRandom(int currentRoll, Pawn[] moveablePawns, Field[] gameBoard){
		Random rand = new Random();
		int count = 4;
		for(Pawn pawn: moveablePawns){
			if(!pawn.getIsMoveable()){
				count--;
			}
		}
		Pawn pawn = null;
		if(count == 0){
			return pawn;
		}
		do{
			int random = rand.nextInt(4);
			pawn = moveablePawns[random];
		}while(!pawn.getIsMoveable());
		
		return pawn;

	}
}
