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
	//private HashMap<Player, Field[]> playerHomeMap;
	private HashMap<Player, Field[]> playerEndMap;
	
	//Constructor
	public Board(){
		// Create board and player arrays
		gameBoard = new Field[40];
		players = new Player[4];
		//add a fork for each player
		for(Player player: players){
			gameBoard[player.getStartPosition()-1] = new Fork(player);
		}
		
		// Generate the players
		Player human = new HumanPlayer(0);
		players[0] = human;
		for(int i=1; i<4; i++){
			players[i] = new ComputerPlayer(i);
		}
		
		// Map the players to their corresponding home and end fields
		//playerHomeMap = new HashMap<Player, Field[]>();
		playerEndMap = new HashMap<Player, Field[]>();
		for (Player player: players){
			playerEndMap.put(player, new Field[4]);
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
	 * @param owner of the EndMap you want to check
	 * @return Returns integer of closest pawn to the s
	 */
	private int getClosestPawnInGoal(Player owner, Pawn pawn){
		int standard = 45;
		for(Pawn temp:owner.getPawns()){
			if(temp.getPosition() < standard && temp.getPosition() > pawn.getPosition()){
				standard = temp.getPosition();
			}
		}
		return standard;
	}
	
	/**
	 * 
	 * @param sends the pawn at the given position back to the owners home
	 */
	private void sendPawnHome(int pos){
		Pawn temp = gameBoard[pos].getOccupant();
		temp.setPosition(-1);
		temp.getOwner().incrementPawnsAtHome();
	}
	
	
	
	 /**
	  * 
	  * @return Returns all pawns that can be moved
	  */
	public Pawn[] getMoveablePawns(){
		Player owner = this.players[currentPlayer];
		int startpos = owner.getStartPosition();
		//if a six is rolled and the spot in front of the home is not alreadyt occupied by one of the owners
		//pawns, if possible move a pawn out from home
		if(currentRoll == 6 && gameBoard[startpos].getOccupant() != owner && owner.getPawnsAtHome() != 0){
			for(Pawn pawn: owner.getPawns()){
				if(pawn.getPosition() == -1){
					pawn.setIsMoveable(true);
				}else{
					pawn.setIsMoveable(false);
				}
			}
			return owner.getPawns();
		}
		for(Pawn pawn: owner.getPawns()){
			if(pawn.getPosition() == -1){
				pawn.setIsMoveable(false);
				continue;
			}
			int currentpos = pawn.getPosition();
			//check each spot to make sure it doesnt pass the goal fork
			for(int i=1;i<=currentRoll;i++){
				if(gameBoard[currentpos + i] instanceof Fork && gameBoard[currentpos + i].getOwner() == owner){
					int remainingMoves = currentRoll - i;
					if(remainingMoves > 4){
						pawn.setIsMoveable(false);
						continue;
					}
					//make sure the pawn doesnt pass another in the goal area
					if(getClosestPawnInGoal(owner,pawn) < remainingMoves){
						pawn.setIsMoveable(true);
						continue;
					}else{
						pawn.setIsMoveable(false);
						continue;
					}
				}
			}
			pawn.setIsMoveable(true);
		}
		return owner.getPawns();
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
		int currentpos = pawn.getPosition();
		
		// if pawn clicked is at home, put it in the start position
		if(pawn.getPosition() == -1){
			int startpos = pawn.getOwner().getStartPosition();
			if(gameBoard[startpos].getOccupant() != null){
				sendPawnHome(startpos);
			}
			gameBoard[startpos].setOccupant(pawn);
			pawn.getOwner().decrementPawnsAtHome();
		}else{
			//move the pawn the given number of slots, all error handeling is done by the getMovablePawns method
			for(int i=1;i<=currentRoll;i++){
				if(gameBoard[currentpos + i] instanceof Fork){
					Field[] EndMap = playerEndMap.get(pawn.getOwner());
					EndMap[currentRoll-i].setOccupant(pawn);
					pawn.setPosition(40 + (currentRoll-i));
				}
				if(i == currentRoll){
					if(gameBoard[i].getOccupant() != null){
						sendPawnHome(i);
					}
					gameBoard[currentpos].setOccupant(null);
					gameBoard[currentpos + i].setOccupant(pawn);
					pawn.setPosition(currentpos + i);
				}
			}
		}
			
	}
	
	/**
	 * 
	 * @param player to be checked for winning condition
	 * @return Returns whether or not the player has won the game
	 */
	public Boolean HasWon(Player player){
		for(Field field:playerEndMap.get(player)){
			if(field.getOccupant() == null){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @param player to set all their pawn to non movable at the end of a round
	 */
	public void setPawnsNonMovable(Player player){
		for(Pawn pawn:player.getPawns()){
			pawn.setIsMoveable(false);
		}
	}
	
}
