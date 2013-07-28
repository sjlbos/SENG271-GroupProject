package model;

import java.util.ArrayList;

public class MoveLastStrategy implements Strategy{
	
	@Override
	public Pawn getNextMove(int currentRoll, ArrayList<Pawn> moveablePawns, Field[] gameBoard) {
		//set the players home fork as 0
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

	
	public String toString(){
		return "Move Last";
	}
}
