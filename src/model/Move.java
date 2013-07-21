package model;

public class Move {
	public Pawn pawn;
	public int startPosition;
	public Player collision;

	public Move(Pawn pawn, Player collision, int startPosition){
		this.pawn = pawn;
		this.collision = collision;
		this.startPosition = startPosition;
	}
}
