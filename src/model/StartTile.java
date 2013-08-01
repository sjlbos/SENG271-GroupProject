package model;


public class StartTile extends Field{
	private Player owner;
	
	//Default Constructor
	public StartTile(Player player){
		this.owner = player;
	}
	
	
	/*==================================
	 * GETTERS AND SETTERS
	===================================*/
	
	@Override
	public Player getForkOwner(){
		return this.owner;
	}
	
	public String toString(){
		return "" + owner.getPlayerNumber();
	}
}
