package model;

public class Pawn {
	int position;
	Player Owner;
	
	public Pawn(Player owner){
		this.Owner = owner;
		this.position = -1;
	}
	
	public int getPosition(){
		return this.position;
	}
	
	public void setPosition(int pos){
		this.position = pos;
	}
}
