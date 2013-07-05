package model;

public class Pawn {
	int position;
	Player Owner;
	Boolean isMoveable;
	
	public Pawn(Player owner){
		this.Owner = owner;
		this.position = -1;
		this.isMoveable = false;
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
	public void setIsMoveable(Boolean TorF){
		this.isMoveable = TorF;
	}
	public Boolean getIsMoveable(){
		return this.isMoveable;
	}
}
