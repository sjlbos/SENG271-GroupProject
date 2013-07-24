package controller;

import java.awt.event.*;
import java.util.Random;
import java.util.ArrayList;
import javax.swing.Timer;

import model.*;
import view.FieldTile;
import view.ViewPanel;

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
	private Timer timer;
	
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
		
		timer = new Timer(15, new ActionListener(){
			public void actionPerformed(ActionEvent e){
				viewPanel.repaint();
			}
		});
		timer.start();
	}
	
	private void updateActiveStatuses(){
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
	}
	
	private void makeComputerMove(){
		rollDie();
		Move move = board.makeMove(currentRoll, currentPlayer);	
		if (move != null) {
			if (move.startPosition >= 0 && move.startPosition <= 39){
				viewPanel.getBoardTileAt(move.startPosition).setColor(ViewPanel.BLANK_COLOR);
			} else if (move.startPosition > 39 && move.startPosition < 44){
				viewPanel.getHomeTileForPlayerAt(currentPlayer.getPlayerNumber(), move.startPosition-40).setColor(ViewPanel.BLANK_COLOR);
			}
			if (move.collision != null){
				viewPanel.setPlayerAtHomeTile(move.collision.getPlayerNumber(), move.collision.getPawnsAtHome()-1);
			}
		}
	}
	
	private void makeComputerMoves(){
		for (int i=2; i<=4; i++){
			try { 
				Thread.sleep(2000);
			} catch (Exception e){
				e.printStackTrace();
			}
			this.setCurrentPlayer(board.getPlayer(i));
			makeComputerMove();
			updateView();
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
		//this.currentRoll = 6;
		this.animateDieRoll(currentRoll);

	}
	
	/**
	 * Used for parsing the id string of the tile a player selects when moving a pawn
	 * This information is used to communicate to the board which pawn to move
	 * @param id
	 */
	public void makeHumanMove(String id){
		String[] tokens = id.split(":");
		Pawn[] pawns = currentPlayer.getPawns();
		if ("H".equals(tokens[0])){
			/*
			 * Map the home space back to the board and make the move
			 */
			for (Pawn pawn : pawns){
				if (pawn.getPosition() == -1){
					Move move = board.makeMove(pawn, currentRoll);
					viewPanel.setPlayerAtBoardTile(currentPlayer.getPlayerNumber(), currentPlayer.getStartPosition());
					viewPanel.getHomeTileForPlayerAt(currentPlayer.getPlayerNumber(), currentPlayer.getPawnsAtHome()).setColor(ViewPanel.BLANK_COLOR);
					if (move.collision != null){
						viewPanel.setPlayerAtHomeTile(move.collision.getPlayerNumber(), move.collision.getPawnsAtHome()-1);
					}
					break;
				}
			}
		} else if ("B".equals(tokens[0])){
			int pos = Integer.parseInt(tokens[1]);
			viewPanel.getBoardTileAt(pos).setColor(ViewPanel.BLANK_COLOR);
			for (Pawn pawn: pawns){
				if (pawn.getPosition() == pos){
					Move move = board.makeMove(pawn, currentRoll);
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
	
	/**
	 * Animates the rolling of the die. This method randomly generates 6 numbers
	 * between 1 and 6 and displays them on the die at half second intervals until
	 * the real die roll is finally displayed.
	 * @param toNumber - the number to which the die will be rolled.
	 */
	private void animateDieRoll(int toNumber){
		Random r = new Random();
		
		for(int i=0;i<6;i++){
			viewPanel.setDieRoll(r.nextInt(6)+1);
			try{Thread.sleep(500);}catch(Exception e){}
		}

		viewPanel.setDieRoll(toNumber);
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
				(new TileEventThread(e)).start();
			}
		}
	}
	
	// Nested action listener for Dice events.
	private class DiceListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			(new DieEventThread()).start();
		}	
	}
	
	/*===================================
	 EVENT THREADS
	 ===================================*/
	
	/**
	 * This class defines the thread that will be executed when a player rolls the die. 
	 * Once the die has been clicked, this thread sets the die to be inactive, rolls the die 
	 * and animates the die roll, and performs a round of play if the player is unable to make a
	 * move.
	 */
	private class DieEventThread extends Thread{
		
		public void run(){
			viewPanel.toggleDieIsActive();
			Controller.this.rollDie();
			updateActiveStatuses();
			// If the player is unable to move, perform a round of play.
			if (board.getMoveablePawns(currentRoll, currentPlayer).size() == 0){
				makeComputerMoves();
			}
		}
	}
	
	/**
	 * This class defines the thread that will be executed when the player clicks a tile to make a move.
	 * This thread performs a complete round of play, first setting all tiles to be inactive, performing the 
	 * player's move and then moving each of the computer players in succession. When this thread ends, the 
	 * die will once again be active, awaiting another roll from the player.
	 */
	private class TileEventThread extends Thread{
		
		ActionEvent event;
		
		// This thread requires the event that triggered this thread's execution in its constructor.
		public TileEventThread(ActionEvent event){
			this.event=event;
		}
		
		public void run(){
			viewPanel.setTilesInactive();
			FieldTile ft = (FieldTile)event.getSource();
			makeHumanMove(ft.getId());
			makeComputerMoves();
		}
	}
}
