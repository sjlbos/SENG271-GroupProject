package model;

public class HumanPlayer implements Player {
	
	private int playerNumber;
	private int pawnsAtHome;
	private int startPosition;
	private Strategy strategy;
	private Pawn[] pawns;
	
	//Constructor
	public HumanPlayer(int playerNumber){
		this.playerNumber = playerNumber;
		this.startPosition = determineStartPosition(playerNumber);
		pawns = new Pawn[4];
	}
	
	/*===============================
	 *    GETTERS AND SETTERS
	 *===============================*/
	
	@Override
	public int getPlayerNumber(){
		return this.playerNumber;
	}
	
	@Override
	public void setPlayerNumber(int number){
		this.playerNumber = number;
	}
	
	@Override
	public Pawn[] getPawns(){
		return this.pawns;
	}
	
	@Override
	public Strategy getStrategy(){
		return this.strategy;
	}
	
	@Override
	public void setStrategy(Strategy strategy){
		this.strategy = strategy;
	}
	
	@Override
	public int getPawnsAtHome(){
		return this.pawnsAtHome;
	}
	
	@Override
	public void setPawnsAtHome(int pawnsAtHome){
		this.pawnsAtHome = pawnsAtHome;
	}
	
	@Override
	public int getStartPosition(){
		return this.startPosition;
	}
	
	/**
	 * Utility method used in constructing the player object to set their starting tile.
	 * @param player - Player number of the player object being created.
	 */
	@Override
	public int determineStartPosition(int player){
		switch(player){
			case(1):
				return 36;
			case(2):
				return 6;
			case(3):
				return 16;
			case(4):
				return 26;
			default:
				//This should never occur
				return -1;
				
		}	
	}

	/**
	 * increments the pawnsAtHome counter by one
	 */
	@Override
	public void incrementPawnsAtHome() {
		this.pawnsAtHome++;
		
	}

	/**
	 * decrements the pawnsAtHome counter by one
	 */
	@Override
	public void decrementPawnsAtHome() {
		this.pawnsAtHome--;
		
	}
	
	
}
