package model;

public class Move {
	public Pawn pawn;
	public int startPosition;
	public Player collision;
	
	public Move(){
		this.pawn = null;
		this.collision = null;
		this.startPosition = 0;
	}

	public Move(Pawn pawn, Player collision, int startPosition){
		this.pawn = pawn;
		this.collision = collision;
		this.startPosition = startPosition;
	}
}
