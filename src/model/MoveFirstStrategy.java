package model;

import java.util.ArrayList;

public class MoveFirstStrategy implements Strategy {

	public Pawn getNextMove(int currentRoll, ArrayList<Pawn> moveablePawns, Field[] gameBoard) {
		//normalize pawn position based on home
		if(moveablePawns.isEmpty()){
			return null;
		}
		int startPos = moveablePawns.get(0).getOwner().getStartPosition();
		Pawn pawn = null;
		Integer currentVal = null;
		//go through movable pawns
		for(Pawn temp : moveablePawns){
			//if pawn to return has not be set and the pawn is movable, replace the pawn with the current one
			Integer value = temp.getPosition() - startPos;
			if(pawn == null){
				pawn = temp;
				currentVal = value;
				continue;
			}
			if(value >= 0 && currentVal >= 0){
				if(value > currentVal){
					currentVal = value;
					pawn = temp;
				}
			}else if(value <= 0 && currentVal > 0){
				currentVal = value;
				pawn = temp;
			}else if(value < 0 && currentVal < 0){
				if(value < currentVal){
					currentVal = value;
					pawn = temp;
				}
			}
			
			
		}
		
		
		return pawn;
	}
	
	public String toString(){
		return "Move First";
	}

}
