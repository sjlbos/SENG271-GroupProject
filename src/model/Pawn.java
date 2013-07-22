package model;

public class Pawn {
	private int position;
	private Player Owner;
	
	public Pawn(Player owner){
		this.Owner = owner;
		this.position = -1;
	}
	
	/*=================================
	Getters and Setters
	=================================*/
	
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
