package model;

public class Pawn {
	private int position;
	private Player Owner;
	private int tilesMoved;
	
	public Pawn(Player owner){
		this.Owner = owner;
		this.position = -1;
		this.tilesMoved = 0;
	}
	
	/*=================================
	Getters and Setters
	=================================*/
	
	public void setTilesMoved(int moves){
		this.tilesMoved = moves;
	}
	
	public void incrementTilesMoved(int moves){
		this.tilesMoved += moves;
	}
	
	public int getTilesMoved(){
		return this.tilesMoved;
	}
	
	public int getPosition(){
		return this.position;
	}
	
	public void setPosition(int pos){
		this.position = pos;
	}
	
	public Player getOwner(){
		return this.Owner;
	}
	
	public String toString(){
		return Owner + " : " + position;
	}
}
