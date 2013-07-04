package model;

import java.util.HashMap;
import java.util.ArrayList;

/**
 * Class to model the game board. <br>
 * Contains accessors for the players, pawns, dice rolls, etc.
 * @author Luuk
 *
 */

public class Board {
	
	private int currentRoll;
	private int currentPlayer;
	private Player[] players;
	private Field[] gameBoard;
	private HashMap<Player, Field[]> playerHomeMap;
	private HashMap<Player, Field[]> playerEndMap;
	
	//Constructor
	public Board(){
		// Create board and player arrays
		gameBoard = new Field[40];
		players = new Player[4];
		
		// Generate the players
		Player human = new HumanPlayer(0);
		players[0] = human;
		for(int i=1; i<4; i++){
			players[i] = new ComputerPlayer(i);
		}
		
		// Map the players to their corresponding home and end fields
		playerHomeMap = new HashMap<Player, Field[]>();
		playerEndMap = new HashMap<Player, Field[]>();
		for (Player p: players){
			playerHomeMap.put(p, new Field[4]);
			playerEndMap.put(p, new Field[4]);
		}
	}
	
	/**
	 * @return Returns the next player object whose turn it is
	 */
	public Player getNextPlayer(){
		currentPlayer = (++currentPlayer)%4;
		return players[currentPlayer];
	}
	
	/**
	 * @return Returns an array containing all the Pawn objects on the game board
	 * 
	 */
	public Pawn[] getPawns(){
		int count = 0;
		Pawn[] allPawns = new Pawn[players.length*4];
		for(Player player: players){
			for(Pawn pawn: player.getPawns()){
				allPawns[count++] = pawn;
			}
		}
		return allPawns;
	}
	 /**
	  * 
	  * @return Returns all pawns that can be moved
	  */
	public ArrayList<Pawn> getActivePawns(){
		Player player = players[currentPlayer];
		ArrayList<Pawn> ActivePawns = new ArrayList<Pawn>();
		ArrayList<Pawn> AllPawns = new ArrayList<Pawn>();
		if(currentRoll == 6){
			for(Pawn p: player.getPawns()){
				AllPawns.add(p);
				if(p.position == -1){
					if(gameBoard[p.getOwner().getStartPosition()].getOccupant().getOwner() != p.getOwner()){
						ActivePawns.add(p);
					}
				}
			}
			if(ActivePawns.isEmpty()){
				// need to add cases where pawns are unmovable for 6
				return AllPawns; 
			}else{
				return ActivePawns;
			}
		}else{
			for(Pawn p: player.getPawns()){
				int pos = p.getPosition() + currentRoll;
				if(pos>=40) pos-=40;
				if(pos < p.getOwner().getStartPosition()){
					ActivePawns.add(p);
				}
			}
			return null;
		}
	}
	
	/**
	 * Makes a move for a computer player based on its strategy
	 */
	public void makeMove(){
		
	}
	
	/**
	 * Moves the pawn selected by the player "currentRoll" number of spaces
	 * @param Pawn object to be moved
	 */
	public void makeMove(Pawn pawn){
		
			
	}
}
