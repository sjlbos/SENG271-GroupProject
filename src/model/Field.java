package model;

import model.Pawn;

public class Field {
	
	private Pawn occupant;
	private Player pawnOwner;
	
	/*==========================
	 * DEFAULT CONSTRUCTOR
	 *==========================*/
	public Field(){
		this.occupant = null;
		this.pawnOwner = null;
	}
	
	/*=================================
	 * GETTERS AND SETTERS
	 *===============================*/
	
	public Pawn getOccupant(){
		return this.occupant;
	}
	
	public void setOccupant(Pawn pawn){
		this.occupant = pawn;
	}
	
	public Player getPawnOwner(){
		return this.pawnOwner;
	}
	
	public Player getForkOwner(){
		return null;
	}
	
	
	public void setPawnOwner(Player Owner){
		this.pawnOwner = Owner;
	}
	
	
	/*===========================================
	 *                TOSTRING
	 *==========================================*/
	public String toString(){
		return pawnOwner + " : " + occupant;
	}
}
