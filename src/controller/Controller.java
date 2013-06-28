package controller;

import java.awt.event.*;
import java.util.Random;

import model.Board;
import model.Player;
import model.Pawn;
import model.HumanPlayer;
import view.FieldTile;
import view.ViewPanel;

public class Controller {
	
	/*===================================
 	FIELDS
 	===================================*/
	
	private Board board;
	private ViewPanel viewPanel;
	private boolean diceRolled;
	private boolean pawnSelected;
	private int currentRoll;
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
	
	public int getCurrentRoll(){
		return this.currentRoll;
	}
	
	/**
	 * Simulates rolling the die <br> 
	 * Updates currentRoll using a pseudo-random number generator
	 */
	public void rollDie(){
		Random rand = new Random();
		this.currentRoll = rand.nextInt(6) + 1;
	}
	
	/**
	 * Resets the game board and begins a new game.
	 */
	public void startNewGame(){
		System.out.println("Fix Me! Start a new game.");
		
		Player player = board.getNextPlayer();
		if(player instanceof HumanPlayer){
			for(;;){
				// wait for die roll event
				if(this.diceRolled) break;
			}
			board.getActivePawns();
			for(;;){
				// wait for pawn select event
				if(this.pawnSelected) break;
			}
			//@TODO board.makeMove(pawn);
			Pawn[] pawns = board.getPawns();
			for(Pawn pawn: pawns){
				//@TODO position = pawn.getPosition()
				//viewPanel.setColorAtBoardTile(player, position);
			}
		} else {
			rollDie();
			//board.makeMove();
			Pawn[] pawns = board.getPawns();
			for(Pawn pawn : pawns){
				
			}
		}
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
