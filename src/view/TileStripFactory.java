package view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * 
 * @author Stephen Bos
 * June, 2013
 *
 * This class acts as a factory for a ViewPanel object, responsible for creating the tile strips that
 * will be arranged to create the game board.
 */
public abstract class TileStripFactory {
	
	// The padding that will be added between, above, and below each FieldTile 
	private static final int panelPadding = 5;
	
	/*===================================
 	LOOP STRIPS
 	===================================*/
	
	public static JPanel getRightBoardStrip(FieldTile[] boardLoop, Color c){
		JPanel newPanel = getBlankPanel(11, false, c, false);
		layoutTiles(boardLoop, 20, 30, newPanel, false);
		return newPanel;
	}
	
	public static JPanel getLeftBoardStrip(FieldTile[] boardLoop, Color c){
		JPanel newPanel = getBlankPanel(11, false, c, false);
		layoutTiles(boardLoop, 10, 0, newPanel, false);
		return newPanel;
	}
	
	public static JPanel getTopBoardStrip(FieldTile[] boardLoop, Color c){
		JPanel newPanel = getBlankPanel(9, true, c, true);
		layoutTiles(boardLoop, 11, 19, newPanel, true);
		return newPanel;
	}
	
	public static JPanel getBottomBoardStrip(FieldTile[] boardLoop, Color c){
		JPanel newPanel = getBlankPanel(9, true, c, true);
		layoutTiles(boardLoop, 39, 31, newPanel, true);
		return newPanel;
	}
	
	/*===================================
 	HOME STRIPS
 	===================================*/
	
	public static JPanel getHomeForRight(FieldTile[] home, Color c){
		JPanel newPanel = getBlankPanel(4, false, c, false);
		layoutTiles(home, 3, 0, newPanel, false);
		return newPanel;
	}
	
	public static JPanel getHomeForLeft(FieldTile[] home, Color c){
		JPanel newPanel = getBlankPanel(4, false, c, false);
		layoutTiles(home, 0, 3, newPanel, false);
		return newPanel;
	}
	
	public static JPanel getHomeForTop(FieldTile[] home, Color c){
		JPanel newPanel = getBlankPanel(4, false, c, true);
		layoutTiles(home, 3, 0, newPanel, false);
		return newPanel;
	}
	
	public static JPanel getHomeForBottom(FieldTile[] home, Color c){
		JPanel newPanel = getBlankPanel(4, false, c, true);
		layoutTiles(home, 0, 3, newPanel, false);
		return newPanel;
	}
	
	/*===================================
 	GOAL STRIPS
 	===================================*/
	
	public static JPanel getGoalForRight(FieldTile[] goal, Color c){
		JPanel newPanel = getBlankPanel(4, true, c, true);
		layoutTiles(goal, 3, 0, newPanel, true);
		return newPanel;
	}
	
	public static JPanel getGoalForLeft(FieldTile[] goal, Color c){
		JPanel newPanel = getBlankPanel(4, true, c, true);
		layoutTiles(goal, 0, 3, newPanel, true);
		return newPanel;
	}
	
	public static JPanel getGoalForTop(FieldTile[] goal, Color c){
		JPanel newPanel = getBlankPanel(4, true, c, false);
		layoutTiles(goal, 0, 3, newPanel, true);
		return newPanel;
	}
	
	public static JPanel getGoalForBottom(FieldTile[] goal, Color c){
		JPanel newPanel = getBlankPanel(4, true, c, false);
		layoutTiles(goal, 3, 0, newPanel, true);
		return newPanel;
	}
	
	/*===================================
 	HELPER METHODS
 	===================================*/
	
	/**
	 * Creates a blank JPanel of the appropriate size necessary to hold a specified number of FieldTile components.
	 * 
	 * @param numberOfTiles - the number of FieldTiles that will be added to this panel.
	 * @param isTruncated - if true, no padding space will be allocated to the beginning and end of the panel.
	 * @param backgroundColor - the color to be used as the background color for this panel.
	 * @param isHorizontal - if true, the returned panel's width will be greater than its height, allowing tiles to be layed out along a horizontal axis.
	 * @return Returns a finished panel of the appropriate size.
	 */
	private static JPanel getBlankPanel(int numberOfTiles, boolean isTruncated, Color backgroundColor, boolean isHorizontal){
		
		//Create the panel to be returned
		JPanel newPanel = new JPanel();
		
		// Determine panel dimensions
		int tileHeight=FieldTile.HEIGHT;
		int tileWidth=FieldTile.WIDTH;
		int panelHeight,panelWidth;
		
		if(isHorizontal){
			newPanel.setLayout(new BoxLayout(newPanel,BoxLayout.LINE_AXIS));
			panelWidth = numberOfTiles*(tileWidth+panelPadding);
			panelHeight = tileHeight + 2 * panelPadding;
			if(!isTruncated){
				panelWidth+=panelPadding;
			}
			else{
				panelWidth-=panelPadding;
			}
		}
		else{
			newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.PAGE_AXIS));
			panelWidth = tileWidth + 2 * panelPadding;
			panelHeight = numberOfTiles*(tileHeight+panelPadding);
			if(!isTruncated){
				panelHeight+=panelPadding;
			}
			else{
				panelHeight-=panelPadding;
			}
		}
		Dimension size = new Dimension(panelWidth,panelHeight);
		
		// Set panel dimensions and properties
		newPanel.setBackground(backgroundColor);
		newPanel.setSize(size);
		newPanel.setPreferredSize(size);
		newPanel.setMinimumSize(size);
		newPanel.setMaximumSize(size);
		
		return newPanel;
	}
	
	/**
	 * This is a helper method which attaches FieldTile objects to a passed JPanel. 
	 * 
	 * @param tiles - an array of the FieldTile objects to be arranged.
	 * @param startIndex - the array index from which to start copying the tiles onto the panel. If the start index is less than the end index, the tiles will be added in reverse order.
	 * @param endIndex - the array index from which to stop copying the tiles onto the panel.
	 * @param tileStrip - the panel to which the tiles will be added. It is assumed that this panel is of the correct size and is using a BoxLayout layout manager.
	 * @param isTruncated - if set to true, no padding will be added to the before the first tile or after the last tile.
	 */
	private static void layoutTiles(FieldTile[] tiles, int startIndex, int endIndex, JPanel tileStrip, boolean isTruncated){		
		
		if(!isTruncated){
			tileStrip.add(Box.createRigidArea(new Dimension(panelPadding, panelPadding)));
		}
		
		if(startIndex<endIndex){
			for(int i = startIndex; i<=endIndex; i++){
				tileStrip.add(tiles[i]);
				if(i!=endIndex){
					tileStrip.add(Box.createRigidArea(new Dimension(panelPadding, panelPadding)));		
				}
			}
		}
		else{
			for(int i = startIndex; i>= endIndex; i--){
				tileStrip.add(tiles[i]);
				if(i!=endIndex){
					tileStrip.add(Box.createRigidArea(new Dimension(panelPadding, panelPadding)));
				}
			}
		}
		
		if(!isTruncated){
			tileStrip.add(Box.createRigidArea(new Dimension(panelPadding, panelPadding)));
		}
	}
}
