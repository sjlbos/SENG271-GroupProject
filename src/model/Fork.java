package model;


public class Fork extends Field{
	private Player owner;
	
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
