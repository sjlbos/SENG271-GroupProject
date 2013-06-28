package controller;
import java.awt.event.*;
import model.Board;
import view.FieldTile;
import view.ViewPanel;

public class Controller {
	
	/*===================================
 	FIELDS
 	===================================*/
	
	private Board board;
	private ViewPanel viewPanel;
	private StartNewGameListener startNewGameListener;
	private FieldTileListener fieldTileListener;
	
	/*===================================
	 GETTERS & SETTERS
	 ===================================*/
	
	public void setBoard(Board board){
		this.board=board;
	}
	
	public void setViewPanel(ViewPanel viewPanel){
		this.viewPanel=viewPanel;
	}
	
	/**
	 * Resets the game board and begins a new game.
	 */
	public void startNewGame(){
		System.out.println("Fix Me! Start a new game.");
	}
	
	/**
	 * Factory method for a StartNewGameListener object.
	 * <p>
	 * @return Returns the default StartNewGameListener
	 */
	public ActionListener getStartNewGameListener(){
		if(this.startNewGameListener==null){
			this.startNewGameListener = new StartNewGameListener();
		}
		return this.startNewGameListener;
	}
	
	/**
	 * Factory method for a FieldPanelListener object.
	 * <p>
	 * @return Returns the default FieldPanelListener
	 */
	public ActionListener getFieldTileListener(){
		if(this.fieldTileListener==null){
			this.fieldTileListener = new FieldTileListener();
		}
		return this.fieldTileListener;
	}
	
	// Nested action lister class for the "New Game" button.
	private class StartNewGameListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Controller.this.startNewGame();
		}	
	}
	
	// Nested action listener for FieldPanel events.
	private class FieldTileListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(e.getActionCommand().equals(FieldTile.ENTER_EVENT)){
				System.out.println("Entered Field");
			}
			else if(e.getActionCommand().equals(FieldTile.EXIT_EVENT)){
				System.out.println("Exited Field");
			}
			else if(e.getActionCommand().equals(FieldTile.CLICK_EVENT)){
				System.out.println("Tile Clicked");
			}
		}
	}
}
