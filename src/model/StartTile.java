package model;


public class StartTile extends Field{
	private Player owner;
	
	//Default Constructor
	public StartTile(Player player){
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
