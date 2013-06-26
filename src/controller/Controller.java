package controller;
import java.awt.event.*;
import model.Board;
import view.ViewPanel;

public class Controller {
	
	// Fields
	private Board board;
	private ViewPanel viewPanel;
	private StartNewGameListener startNewGameListener;
	
	// Getters and Setters
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
	
	// Nested action lister class for the "New Game" button.
	private class StartNewGameListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Controller.this.startNewGame();
		}	
	}	
}
