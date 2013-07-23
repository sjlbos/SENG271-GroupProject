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
	
	@Override
	public Player getForkOwner(){
		return this.owner;
	}
	
}
