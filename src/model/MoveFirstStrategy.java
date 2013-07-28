package model;

import java.util.ArrayList;

public class MoveFirstStrategy implements Strategy {

	public Pawn getNextMove(int currentRoll, ArrayList<Pawn> moveablePawns, Field[] gameBoard) {
		//normalize pawn position based on home
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
	
	public String toString(){
		return "Move First";
	}

}
