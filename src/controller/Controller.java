package controller;

import java.awt.event.*;
import java.util.Random;
import java.util.ArrayList;

import model.*;
import view.*;

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
	private TitlePanel titlePanel;
	
	/*===================================
	 GETTERS & SETTERS
	 ===================================*/
	
	public void setBoard(Board board){
		this.board=board;
	}
	
	public void setViewPanel(ViewPanel viewPanel){
		this.viewPanel=viewPanel;
	}
	
	public void setTitlePanel(TitlePanel titlePanel){
		this.titlePanel = titlePanel;
	}
	
	public void setCurrentPlayer(Player player){
		this.currentPlayer = player;
		this.titlePanel.setTurnForPlayerNumber(this.currentPlayer.getPlayerNumber());
	}
	
	public int getCurrentRoll(){
		return this.currentRoll;
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
	
	/*===================================
	 METHODS
	 ===================================*/
	
	/**
	 * Resets the game board and begins a new game.
	 */
	public void startNewGame(){
		viewPanel.resetBoard();
		this.currentPlayer = board.getPlayer(1);
		titlePanel.setTurnForPlayerNumber(1);
		this.viewPanel.toggleDieIsActive();
	}
	
	private void updateActiveStatuses(){
		if (currentPlayer instanceof HumanPlayer){
			ArrayList<Pawn> activePawns = board.getMoveablePawns(currentRoll, currentPlayer);
			for(Pawn pawn: activePawns){
				// set corresponding tiles to active and wait for player input
				int pos = pawn.getPosition();
				if (pos == -1){
					viewPanel.getHomeTileForPlayerAt(currentPlayer.getPlayerNumber(), 0).setActive();
				} else if (pos >= 0 && pos <= 39){
					viewPanel.getBoardTileAt(pos).setActive();
				} else {
					viewPanel.getGoalTileForPlayerAt(currentPlayer.getPlayerNumber(), pos-40).setActive();
				}
			}
		} else {
			viewPanel.setTilesInactive();
		}
	}
	
	private void makeComputerMove(){
		rollDie();
		Player collision = board.makeMove(currentRoll, currentPlayer);
		if (collision != null){
			viewPanel.setPlayerAtHomeTile(collision.getPlayerNumber(), collision.getPawnsAtHome()-1);
		}
	}
	
	private void makeMove(ActionEvent e){
		
	}
	
	private void makeComputerMoves(){
		for (int i=2; i<=4; i++){
			this.setCurrentPlayer(board.getPlayer(i));
			makeComputerMove();
		}
		this.setCurrentPlayer(board.getPlayer(1));
		viewPanel.toggleDieIsActive();
	}
	
	
	/**
	 * Gets all pawns on the board and translates their information to the view
	 * Home tiles should simply be updated based on the number of pawns the player currently has at "home"
	 */
	public void updateView(){
		/* clear all circles first */
		Pawn[] allPawns = board.getPawns();
		for (Pawn pawn: allPawns){
			int pos = pawn.getPosition();
			//System.out.print(pos);
			if (pos >= 0 && pos <= 39){
				viewPanel.setPlayerAtBoardTile(pawn.getOwner().getPlayerNumber(), pos);
			} else if (pos == -1){
				// colour the home tiles based on how many pawns are in there
				continue;
			} else {
				FieldTile ft = viewPanel.getGoalTileForPlayerAt(currentPlayer.getPlayerNumber(), pos-40);
				ft.setColor(viewPanel.getColorForPlayer(currentPlayer.getPlayerNumber()));
			}
		}
		updateViewPlayerHome();
	}
	
	
	/**
	 * Simulates rolling the die <br> 
	 * Updates currentRoll using a pseudo-random number generator
	 */
	public void rollDie(){
		Random rand = new Random();
		this.currentRoll = rand.nextInt(6) + 1;
		this.viewPanel.setDieRoll(currentRoll);
	}
	
	/**
	 * Used for parsing the id string of the tile a player selects when moving a pawn
	 * This information is used to communicate to the board which pawn to move
	 * @param id
	 */
	public void performRound(String id){
		String[] tokens = id.split(":");
		Pawn[] pawns = currentPlayer.getPawns();
		if ("H".equals(tokens[0])){
			/*
			 * Map the home space back to the board and make the move
			 */
			for (Pawn pawn : pawns){
				if (pawn.getPosition() == -1){
					board.makeMove(pawn, currentRoll);
					updateView();
					break;
				}
			}
		} else if ("B".equals(tokens[0])){
			int pos = Integer.parseInt(tokens[1]);
			for (Pawn pawn: pawns){
				if (pawn.getPosition() == pos){
					board.makeMove(pawn, currentRoll);
					updateView();
					break;
				}
			}
			/*
			 * Map the board tile back to the board
			 */
		} else {
			/*
			 * Map the goal tile back to the board
			 */
		}
		makeComputerMoves();
	}
	
	/**
	 * Iterates over all players and sets the colours of their home tiles
	 * Uses the pawnsAtHome attribute to colour that many first, then the rest blank
	 */
	public void updateViewPlayerHome(){
		for (Player player : board.getPlayers()){
			int numPawns = player.getPawnsAtHome();
			for (int i=0; i<4; i++){
				FieldTile ft = viewPanel.getHomeTileForPlayerAt(player.getPlayerNumber(), i);
				if(numPawns != 0){
					ft.setColor(viewPanel.getColorForPlayer(player.getPlayerNumber()));
					numPawns--;
				} else {
					ft.setColor(ViewPanel.BLANK_COLOR);
				}
			}
		}
	}
	
	/*===================================
	 ACTION LISTENERS
	 ===================================*/
	
	// Nested action lister class for the "New Game" button.
	private class StartNewGameListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Controller.this.startNewGame();
		}	
	}
	
	// Nested action listener for FieldPanel events.
	private class FieldTileListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(e.getActionCommand().equals(FieldTile.CLICK_EVENT)){
				System.out.println("Tile " + e.getSource().toString() +" Clicked");
				viewPanel.setTilesInactive();
				FieldTile ft = (FieldTile)e.getSource();
				performRound(ft.getId());
			}
		}
	}
	
	// Nested action listener for Dice events.
	private class DiceListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			Controller.this.rollDie();
			updateActiveStatuses();
			if (board.getMoveablePawns(currentRoll, currentPlayer).size() == 0){
				makeComputerMoves();
			}
		}	
	}
}
