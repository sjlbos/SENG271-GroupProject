package model;

import model.Pawn;

public class Field {
	
	private Pawn occupant;
	private Player owner;
	
	//default Constructor
	public Field(){
		this.occupant = null;
		this.owner = null;
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
		return this.owner;
	}
	
	public void setOwner(Player Owner){
		this.owner = Owner;
	}
	
	public String toString(){
		return owner + " : " + occupant;
	}
}
