package model;

import java.util.*;

public class RandomStrategy implements Strategy {

	@Override
	//returns a random pawn
	public Pawn getNextMove(int currentRoll, ArrayList<Pawn> moveablePawns, Field[] gameBoard) {
		Random rand = new Random();
		if(moveablePawns.isEmpty()){
			return null;
		}
		int random = rand.nextInt(moveablePawns.size());
		return moveablePawns.get(random);

	}

}
