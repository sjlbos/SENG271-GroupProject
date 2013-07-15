package model;


public class Fork extends Field{
	private Player owner;
	
	//Default Constructor
	public Fork(Player player){
		this.owner = player;
	}
	
	
	/*==================================
	 * Getters And Setters
	===================================*/
	
	public Player getOwner(){
		return this.owner;
	}
	
}
