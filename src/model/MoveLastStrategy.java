package model;

public class MoveLastStrategy implements Strategy{
	
	@Override
	public Pawn getNextMove(int currentRoll, Pawn[] moveablePawns, Field[] gameBoard) {
		//set the players home fork as 0
		int startPos = moveablePawns[0].getOwner().getStartPosition();
		Pawn pawn = null;
		Integer currentVal = null;
		//go through movable pawns
		for(Pawn temp : moveablePawns){
			//if pawn to return has not be set and the pawn is movable, replace the pawn with the current one
			Integer value = startPos - temp.getPosition();
			if(pawn == null && temp.getIsMoveable()){
				pawn = temp;
				currentVal = value;
			}
			if(pawn == null || !temp.getIsMoveable()){
				continue;
			//if the new pawn is closer to the home
			// (the normalized value is closer to zero from the positive side or smaller on the negavtive side)
			//set the new pawn
			}else{
				if(value > 0 && currentVal > 0){
					if(value < currentVal){
						currentVal = value;
						pawn = temp;
					}
				}else if(value < 0 && currentVal > 0){
					currentVal = value;
					pawn = temp;
				}else if(value < 0 && currentVal < 0){
					if(value > currentVal){
						currentVal = value;
						pawn = temp;
					}
				}
			}
			
			
		}		
		return pawn;
	}

}
