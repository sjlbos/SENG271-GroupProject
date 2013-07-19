package controller;

import java.awt.event.*;
import java.util.Random;

import model.Board;
import model.Player;
import model.Pawn;
import model.HumanPlayer;
import model.ComputerPlayer;
import view.FieldTile;
import view.ViewPanel;

public class Controller {
	
	/*===================================
 	FIELDS
 	===================================*/
	
	private Board board;
	private Player currentPlayer;
	private ViewPanel viewPanel;
	private int currentRoll;
	private StartNewGameListener startNewGameListener;
	private FieldTileListener fieldTileListener;
	private DiceListener diceListener;
	
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
		this.viewPanel.setDieRoll(currentRoll);
		// if human set the tiles to active and wait for input
		if (currentPlayer instanceof HumanPlayer){
			Pawn[] activePawns = board.getMoveablePawns();
			for(Pawn pawn: activePawns){
				// set corresponding tiles to active
				int pos = pawn.getPosition();
				if (pos == -1){
					// set the home tile to active
					viewPanel.getHomeTileForPlayerAt(currentPlayer.getPlayerNumber(), 1).toggleIsActive();
				} else if (pos >= 0 && pos <= 39){
					viewPanel.getBoardTileAt(pos).toggleIsActive();
				} else {
					// this should never happen
					// only other case is if the tile is a home tile.. shouldn't happen
				}
			}
		} else {
			//begin computer player's turn
			board.makeMove();
		}
	}
	
	
	/**
	 * Resets the game board and begins a new game.
	 */
	public void startNewGame(){
		System.out.println("Fix Me! Start a new game.");
		
		 /* Do some stuf with resetting everything */
		
		this.currentPlayer = board.getNextPlayer();
		board.setCurrentPlayer(currentPlayer.getPlayerNumber());
		if (this.currentPlayer instanceof HumanPlayer){
			this.viewPanel.setTilesInactive();
		} else {
			board.makeMove();
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
	
	/**
	 * Factory method for a DiceListener object.
	 * <p>
	 * @return Returns the default DiceListener
	 */
	public ActionListener getDiceListener(){
		if(this.diceListener==null){
			this.diceListener = new DiceListener();
		}
		return this.diceListener;
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
				//System.out.println("Entered Field");
			}
			else if(e.getActionCommand().equals(FieldTile.EXIT_EVENT)){
				//System.out.println("Exited Field");
			}
			else if(e.getActionCommand().equals(FieldTile.CLICK_EVENT)){
				System.out.println("Tile " + e.getSource().toString() +" Clicked");
				FieldTile ft = (FieldTile)e.getSource();
			}
		}
	}
	
	// Nested action listener for Dice events.
	private class DiceListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			//System.out.println("Dice Rolled.");
			Controller.this.rollDie();
		}	
	}
}
