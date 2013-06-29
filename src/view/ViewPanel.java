package view;
import java.awt.Color;
import java.awt.Dimension;
import controller.Controller;
import javax.swing.*;
import java.awt.*;

public class ViewPanel extends JPanel {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	private static final long serialVersionUID = 1L;
	
	public static final Color PLAYER_1_COLOR = Color.RED;
	public static final Color PLAYER_2_COLOR = Color.BLUE;
	public static final Color PLAYER_3_COLOR = new Color(242,228,29);
	public static final Color PLAYER_4_COLOR = new Color(33,194,52);
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
		this.setLayout(new GridBagLayout());
		
		this.controller = controller;
		this.boardLoop = new FieldTile[40];
		this.homes = new FieldTile[4][4];
		this.goals = new FieldTile[4][4];
	
		DicePanel c = new DicePanel(3,45);
		c.setDieColor(Color.PINK);
		this.add(c);

		initializeTiles();
	}
	
	private void initializeTiles(){
		// Initialize loop tiles
		for(int i=0;i<40;i++){
			FieldTile newTile = new FieldTile(ViewPanel.BLANK_COLOR);
			newTile.addActionListener(controller.getFieldTileListener());
			this.boardLoop[i] = newTile;
		}
		
		// Initialize home tiles
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				FieldTile newTile = new FieldTile(ViewPanel.BLANK_COLOR);
				newTile.addActionListener(controller.getFieldTileListener());
				this.homes[i][j]=newTile;
			}
		}
		
		// Initialize goal tiles
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				FieldTile newTile = new FieldTile(ViewPanel.BLANK_COLOR);
				newTile.addActionListener(controller.getFieldTileListener());
				this.goals[i][j]=newTile;
			}
		}
	}
	
	private JPanel getTileStrip(FieldTile[] tiles,Color c,boolean isHorizontal){
		
		int numberOfTiles=tiles.length;
		
		// Set up dimensions
		int panelPadding=10;
		int tileHeight=FieldTile.HEIGHT;
		int tileWidth=FieldTile.WIDTH;
		int panelHeight=0;
		int panelWidth=0;
		
		// Create panel
		JPanel tileStrip = new JPanel();
		tileStrip.setBackground(c);
		if(isHorizontal){
			tileStrip.setLayout(new BoxLayout(tileStrip,BoxLayout.LINE_AXIS));
			panelHeight=2*panelPadding+tileHeight;
			panelWidth=numberOfTiles*(tileWidth+panelPadding)+panelPadding;
		}
		else{
			tileStrip.setLayout(new BoxLayout(tileStrip,BoxLayout.PAGE_AXIS));
			panelHeight=numberOfTiles*(tileHeight+panelPadding)+panelPadding;
			panelWidth=2*panelPadding+tileWidth;
		}
		Dimension d = new Dimension(panelWidth,panelHeight);
		tileStrip.setPreferredSize(d);
		tileStrip.setMinimumSize(d);
		tileStrip.setMaximumSize(d);
		tileStrip.setBackground(c);
		
		// Add FieldTiles to panel
		if(isHorizontal){
			for(int i=0;i<numberOfTiles;i++){
				tileStrip.add(Box.createRigidArea(new Dimension(panelPadding,panelHeight)));
				tileStrip.add(tiles[i]);
			}
			tileStrip.add(Box.createRigidArea(new Dimension(panelPadding,panelHeight)));
		}
		else{
			for(int i=0;i<numberOfTiles;i++){
				tileStrip.add(Box.createRigidArea(new Dimension(panelWidth,panelPadding)));
				tileStrip.add(tiles[i]);
			}
			tileStrip.add(Box.createRigidArea(new Dimension(panelWidth,panelPadding)));
		}
		
		return tileStrip;
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
}
