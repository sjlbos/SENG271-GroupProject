package model;

import model.Pawn;

public class Field {
	
	private Pawn occupant;
	
	//default Constructor
	public Field(){
		this.occupant = null;
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
}
