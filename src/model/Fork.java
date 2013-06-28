package model;

import model.Player;

public class Fork extends Field{
	private Field[] goal;
	private Player owner;
	
	public Fork(Player player){
		for(int i=0;i<3;i++){
			goal[i] = new Field();
		}
		this.owner = player;
	}
	
	
	/*==================================
	 * Getters And Setters
	===================================*/
	
	public Player getOwner(){
		return this.owner;
	}
	
}
