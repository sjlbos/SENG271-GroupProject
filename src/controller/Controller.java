package controller;

import java.awt.event.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.Timer;
import model.*;
import view.*;
import javax.sound.sampled.*;

public class Controller {
	
	/*===================================
 	CONSTANTS
 	===================================*/
	private static final double SPEED_FACTOR = 1;
	
	private static final long DIE_INTERVAL = (long) (500/SPEED_FACTOR);
	private static final long MOVE_INTERVAL = (long) (300/SPEED_FACTOR);
	private static final long TURN_PAUSE = (long) (2000/SPEED_FACTOR);
	
	/*===================================
 	FIELDS
 	===================================*/
	
	private Board board;
	private Player currentPlayer;
	private ViewPanel viewPanel;
	private int currentRoll;
	private boolean rolledSix;
	private StartNewGameListener startNewGameListener;
	private FieldTileListener fieldTileListener;
	private DiceListener diceListener;
	private TitlePanel titlePanel;
	private Timer timer;
	private HashMap<String,Clip> audioClips;
	
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
		this.titlePanel.setTurnForPlayerNumber(this.currentPlayer.getPlayerNumber(), this.currentPlayer.getName());
	}
	
	public int getCurrentRoll(){
		return this.currentRoll;
	}
	
	public void setAudioClips(HashMap<String,Clip> audioClips){
		this.audioClips = audioClips;
	}
	
	public Clip getAudioClip(String streamName){
		if(this.audioClips!=null){
			return audioClips.get(streamName);
		}
		else{
			return null;
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
	
	/*===================================
	 METHODS
	 ===================================*/
	
	/**
	 * Resets the game board and begins a new game.
	 */
	public void startNewGame(){
		viewPanel.resetBoard();
		this.currentPlayer = board.getPlayer(1);
		titlePanel.setTurnForPlayerNumber(1,currentPlayer.getName());
		this.viewPanel.toggleDieIsActive();
		board.reset();
		
		timer = new Timer(15, new ActionListener(){
			public void actionPerformed(ActionEvent e){
				viewPanel.repaint();
			}
		});
		timer.start();
	}
	
	/**
	 * Updates the active statuses of field tiles so that only movable pawns are active.
	 */
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
	
	/**
	 * Simulates rolling the die <br> 
	 * Updates currentRoll using a pseudo-random number generator
	 */
	public void rollDie(){
		(new AudioThread(this.getAudioClip("Dice"))).start();
		Random rand = new Random();
		this.currentRoll = rand.nextInt(6) + 1;
		rolledSix = currentRoll == 6 ? true : false;
		this.viewPanel.setDieRoll(currentRoll);
		this.animateDieRoll(currentRoll);
	}
	
	
	/**
	 * Parses a FieldTile's ID attribute and returns the pawn object at that tile
	 * @param The Field Tile's ID string
	 * @return The pawn object currently at the tile
	 */
	private Pawn getPawnFromTileId(String id){
		String[] tokens = id.split(":");
		int pos = Integer.parseInt(tokens[1]);
		if ("H".equals(tokens[0])){
			return board.getPawnAtPosition(currentPlayer, -1);
		} 
		else if ("B".equals(tokens[0])){
			return board.getPawnAtPosition(currentPlayer, pos);
		}
		else {
			return board.getPawnAtPosition(currentPlayer, pos+40);
		}
	}
	
	/**
	 * Performs a computer move.
	 */
	private void makeComputerMove(){
		rollDie();
		Move move = board.makeMove(currentRoll, currentPlayer);
		if (move != null) {
			try { Thread.sleep(1500);} catch (Exception e) { e.printStackTrace();}
			animatePlayerMove(currentPlayer,move);
		}
	}
	
	/**
	 * Cycles through all of the computer players, performing each player's move in turn.
	 */
	private void makeComputerMoves(){
		for (int i=2; i<=4; i++){
			this.setCurrentPlayer(board.getPlayer(i));
			makeComputerMove();
			if (board.HasWon(currentPlayer)){
				setVictory();
				return;
			}
			if (rolledSix) i--;
			try{Thread.sleep(TURN_PAUSE);}catch(Exception e){};
		}
		this.setCurrentPlayer(board.getPlayer(1));
		viewPanel.toggleDieIsActive();
	}
	
	/**
	 * Used for parsing the id string of the tile a player selects when moving a pawn
	 * This information is used to communicate to the board which pawn to move
	 * @param id
	 */
	public void makeHumanMove(String id){
		Pawn pawn = getPawnFromTileId(id);
		Move move=board.makeMove(pawn, currentRoll);
		animatePlayerMove(currentPlayer,move);
	}

	/**
	 * Used for setting a tile color on the view panel given a board player and coordinate.
	 * @param player - the player to put at the tile position.
	 * @param position - the board coordinate of the tile to be changed.
	 */
	private void setTileAtPosition(Player player, int position, boolean isEmpty){
		int playerNumber = player.getPlayerNumber();
		
		if(isEmpty){
			if(position<0){
				viewPanel.clearPlayerAtHomeTile(playerNumber, player.getPawnsAtHome());
			}
			else if(position>39){
				viewPanel.clearPlayerAtGoalTile(playerNumber, position-40);
			}
			else{
				viewPanel.clearPlayerAtBoardTile(playerNumber, position);
			}
		}
		else{
			if(position<0){
				viewPanel.setPlayerAtHomeTile(playerNumber, player.getPawnsAtHome()-1);
			}
			else if(position>39){
				viewPanel.setPlayerAtGoalTile(playerNumber, position-40);
			}
			else{
				viewPanel.setPlayerAtBoardTile(playerNumber, position);
			}
		}
	}
	
	/**
	 * Disables all tiles and displays a message that the current player has won
	 */
	private void setVictory(){
		viewPanel.setTilesInactive();
		titlePanel.setVictoryForPlayer(currentPlayer.getPlayerNumber());
	}
	
	/**
	 * Animates a complete pawn move from start to end position.
	 * @param player - the player to be moved.
	 * @param move - the move object representing the moved to be made.
	 */
	private void animatePlayerMove(Player player, Move move){
		int numberOfMoves = move.startPosition==-1?1:currentRoll;
		int currentPosition = move.startPosition;
		int nextPosition;
		if(currentPosition==-1){
			nextPosition = player.getStartPosition();
		}
		else if(currentPosition==player.getStartPosition()-1){
			nextPosition = 40;
		} else if (currentPosition > 39){
			nextPosition = currentPosition + 1;
		}
		else{
			nextPosition = (currentPosition+1)%40;
		}
		
		int moveNumber=1;
		Pawn overridenPawn = null;
		do{
			if(overridenPawn!=null){
				setTileAtPosition(overridenPawn.getOwner(), currentPosition,false);
			}
			else{
				setTileAtPosition(player,currentPosition,true);
			}
			
			overridenPawn = board.getPawnAtPosition(nextPosition);
			setTileAtPosition(player,nextPosition,false);
			
			currentPosition = nextPosition;
			
			if(move.pawn.getPosition()>39){
				if(currentPosition>39){
					nextPosition++;
				}
				else if(currentPosition==player.getStartPosition()-1){
					nextPosition = 40;
				}
				else{
					nextPosition=(nextPosition+1)%40;
				}
			}
			else{
				nextPosition=(nextPosition+1)%40;
			}
			
			(new AudioThread(this.getAudioClip("Move2"))).start();
			
			try{Thread.sleep(MOVE_INTERVAL);}catch(Exception e){};
			
			moveNumber++;
		}while(moveNumber<=numberOfMoves);
		
		if(move.collision!=null){
			setTileAtPosition(move.collision,-1,false);
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
			try{Thread.sleep(DIE_INTERVAL);}catch(Exception e){}
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
			} else {
				FieldTile ft = (FieldTile)e.getSource();
				String id = ft.getId();
				Pawn pawn = getPawnFromTileId(id);
				int destination = board.getMoveDestination(pawn, currentRoll);
				if (e.getActionCommand().equals(FieldTile.ENTER_EVENT)){
					// turn on
					if (destination < 40){
						viewPanel.setActiveDestinationOnBoard(destination, true);
					} else {
						viewPanel.setActiveDestinationAtGoalForPlayer(currentPlayer.getPlayerNumber(), destination-40, true);
					}
				} else if (e.getActionCommand().equals(FieldTile.EXIT_EVENT)){
					// turn off
					if (destination < 40){
						viewPanel.setActiveDestinationOnBoard(destination, false);
					} else {
						viewPanel.setActiveDestinationAtGoalForPlayer(currentPlayer.getPlayerNumber(), destination-40, false);
					}
				}
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
			ArrayList<Pawn> moveable = board.getMoveablePawns(currentRoll, currentPlayer);
			// If the player is unable to move, perform a round of play.
			if (moveable.size() == 0 && rolledSix){
				viewPanel.toggleDieIsActive();
			} else if (moveable.size() == 0) {
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
		
		private ActionEvent event;
		
		// This thread requires the event that triggered this thread's execution in its constructor.
		public TileEventThread(ActionEvent event){
			this.event=event;
		}
		
		public void run(){
			viewPanel.setTilesInactive();
			FieldTile ft = (FieldTile)event.getSource();
			makeHumanMove(ft.getId());
			if (board.HasWon(currentPlayer)){
				setVictory();
				return;
			}
			if ( rolledSix ){
				viewPanel.toggleDieIsActive();
			} else {
				makeComputerMoves();
			}
		}
	}
	
	/**
	 * This class defines a thread that will be executed to play a specified audio stream.
	 */
	private class AudioThread extends Thread{
		
		private Clip clip;
		
		public AudioThread(Clip clip){
			this.clip = clip;
		}
		
		public void run(){
			if(clip!=null){
				clip.setFramePosition(0);
				clip.start();	
			}
		}
	}
}
