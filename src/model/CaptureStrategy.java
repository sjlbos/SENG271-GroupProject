package model;

import java.util.Random;
import java.util.ArrayList;

public class CaptureStrategy implements Strategy{

	/**
	 * @param Current Roll of die
	 * @param ArrayList of movable pawns
	 * @param Field[] array for the gameboard
	 * @return Pawn most likely to capture a pawn in the next two turns
	 */
	public Pawn getNextMove(int currentRoll, ArrayList<Pawn> moveablePawns, Field[] gameBoard) {
		//go through each pawn and check the ones that can be moved
		for(Pawn pawn: moveablePawns){
			Boolean passed = false;
			//return a pawn if it would catch a pawn
			int pos = pawn.getPosition();
			for(int i=1;i<=currentRoll;i++){
				if(gameBoard[(pos+i)%40] instanceof StartTile){
					if(gameBoard[(pos+i)%40].getForkOwner() == pawn.getOwner()){
						continue;
					}
				}
				if(i == currentRoll){
					if(gameBoard[(pos+i)%40].getOccupant() == null){
						return pawn;
					}else if(passed == true){
						continue;
					}
				}
				if(gameBoard[(pos+i)%40].getOccupant() != null){
					passed = true;
				}
			}
		}
		//if no suitable moves, move random
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
		return "Capture";
	}
}

