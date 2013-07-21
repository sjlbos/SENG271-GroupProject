package model;

import model.Pawn;

public class Field {
	
	private Pawn occupant;
	private Player pawnOwner;
	
	//default Constructor
	public Field(){
		this.occupant = null;
		this.pawnOwner = null;
	}
	
	/*=================================
	Getters and Setters
	=================================*/
	
	public Pawn getOccupant(){
		return this.occupant;
	}
	
	public void setOccupant(Pawn pawn){
		this.occupant = pawn;
	}
	
	public Player getOwner(){
		return this.pawnOwner;
	}
	
	public void setOwner(Player Owner){
		this.pawnOwner = Owner;
	}
	
	public String toString(){
		return pawnOwner + " : " + occupant;
	}
}
