package view;
import java.awt.Color;
import java.awt.Dimension;
import controller.Controller;
import javax.swing.*;

public class ViewPanel extends JPanel {

	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;
	private static final long serialVersionUID = 1L;
	
	public static final Color PLAYER_1_COLOR = Color.RED;
	public static final Color PLAYER_2_COLOR = Color.BLUE;
	public static final Color PLAYER_3_COLOR = Color.YELLOW;
	public static final Color PLAYER_4_COLOR = Color.GREEN;
	public static final Color BLANK_COLOR = Color.WHITE;
	
	/*===================================
 	FIELDS
 	===================================*/
	
	private Controller controller;
	private FieldTile[] boardLoop;
	private FieldTile[][] homes;
	private FieldTile[][] goals;
	
	/*===================================
	 CONSTRUCTORS
	 ===================================*/
	
	public ViewPanel(Controller controller){
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(ViewPanel.WIDTH,ViewPanel.HEIGHT));
		
		this.controller = controller;
		this.boardLoop = new FieldTile[40];
		this.homes = new FieldTile[4][4];
		this.goals = new FieldTile[4][4];
		
		initializeTiles();
		layoutTiles();
	}
	
	private void initializeTiles(){
		for(int i=0;i<40;i++){
			FieldTile newTile = new FieldTile(Color.WHITE);
			newTile.addActionListener(controller.getFieldTileListener());
			this.boardLoop[i] = newTile;
		}
	}
	
	private void layoutTiles(){
		
	}
	
	/*===================================
	 GETTERS & SETTERS
	 ===================================*/
	
	private Color getColorForPlayer(int player){
		switch(player){
		
		case 1:
			return ViewPanel.PLAYER_1_COLOR;
		case 2:
			return ViewPanel.PLAYER_2_COLOR;
		case 3:
			return ViewPanel.PLAYER_3_COLOR;
		case 4:
			return ViewPanel.PLAYER_4_COLOR;
		default:
			return ViewPanel.BLANK_COLOR;
		}
	}
	
	/**
	 * @param position - The position of the tile on the main board. Can be an integer between 0 and 39.
	 * @return Returns the FieldTile at the passed position.
	 */
	public FieldTile getBoardTileAt(int position){
		if(position>39 || position<0){
			return null;
		}
		else{
			return this.boardLoop[position];
		}
	}
	
	/**
	 * @param player - The player number of the player who owns this home. Can be an integer between 1 and 4.
	 * @param position - The position of the tile within the home. Can be an integer between 0 and 3
	 * @return Returns the FieldTile at the passed position in the home belonging to the passed player.
	 */
	public FieldTile getHomeTileForPlayerAt(int player, int position){
		if(position>3 || position<0 || player<1 || player>4){
			return null;
		}
		else{
			return this.homes[player-1][position];
		}
	}
	
	/**
	 * @param player - The player number of the player who owns this goal. Can be an integer between 1 and 4.
	 * @param position - The position of the tile within the goal. Can be an integer between 0 and 3.
	 * @return Returns the FieldTile at the passed position in the goal belonging to the passed player.
	 */
	public FieldTile getGoalTileForPlayerAt(int player, int position){	
		if(position>3 || position<0 || player<1 || player>4){
			return null;
		}
		else{
			return this.goals[player-1][position];
		}
	}
	
	/**
	 * @param player - The player number of the player to set at this tile. Can be an integer between 1 and 4 or 0 to clear the tile.
	 * @param position - The position of the tile on the main board that the player is being assigned to.
	 */
	public void setColorAtBoardTile(int player,int position){
		FieldTile tile = getBoardTileAt(position);
		if(tile!=null){
			tile.setColor(getColorForPlayer(player));
		}	
	}
	
	/**
	 * @param player - The player number of the player who owns this home.Can be an integer between 1 and 4 or 0 to clear the tile.
	 * @param position - The position of the tile in the player's home.
	 */
	public void setPlayerAtHomeTile(int player, int position){
		FieldTile tile = getHomeTileForPlayerAt(player,position);
		if(tile!=null){
			tile.setColor(getColorForPlayer(player));
		}
	}
	
	/**
	 * @param player - The player number of the player who owns this goal.Can be an integer between 1 and 4 or 0 to clear the tile.
	 * @param position - The position of the title in the player's goal.
	 */
	public void setPlayerAtGoalTile(int player, int position){
		FieldTile tile = getGoalTileForPlayerAt(player,position);
		if(tile!=null){
			tile.setColor(getColorForPlayer(player));
		}
	}
	
	/*===================================
	 DRAWING METHODS
	 ===================================*/
}
