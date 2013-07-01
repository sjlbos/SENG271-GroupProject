package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * 
 * @author Stephen Bos
 *
 * This class acts as a factory for a ViewPanel object, responsible for creating the tile strips that
 * will be arranged to create the game board.
 */
public abstract class TileStripFactory {
	
	private static final int panelPadding = 5;
	
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
