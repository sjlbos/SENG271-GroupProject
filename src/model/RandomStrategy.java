package model;

import java.util.*;

public class RandomStrategy implements Strategy {

	@Override
	//returns a random pawn
	public Pawn getNextMove(int currentRoll, Pawn[] moveablePawns, Field[] gameBoard) {
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
