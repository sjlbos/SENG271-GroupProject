package model;

public class MoveFirstStrategy implements Strategy {

	public Pawn getNextMove(int currentRoll, Pawn[] moveablePawns, Field[] gameBoard) {
		//normalize pawn position based on home
		int startPos = moveablePawns[0].getOwner().getStartPosition();
		Pawn pawn = null;
		Integer currentVal = null;
		//go through movable pawns
		for(Pawn temp : moveablePawns){
			//if pawn to return has not be set and the pawn is movable, replace the pawn with the current one
			Integer value = startPos - temp.getPosition();
			if(pawn == null && temp.isMoveable()){
				pawn = temp;
				currentVal = value;
			}
			if(pawn == null || !temp.isMoveable()){
				continue;
			//if the new pawn is farther from the home
			// (the normalized value is closer to zero from the positive side or smaller on the negavtive side)
			//set the new pawn
			}else{
				if(value == 0 || temp.getPosition() >= 40){
					return temp;
				}
				if(value > 0 && currentVal > 0){
					if(value > currentVal){
						currentVal = value;
						pawn = temp;
					}
				}else if(value > 0 && currentVal < 0){
					currentVal = value;
					pawn = temp;
				}else if(value < 0 && currentVal < 0){
					if(value < currentVal){
						currentVal = value;
						pawn = temp;
					}
				}
			}
			
			
		}
		
		
		return pawn;
	}

}
