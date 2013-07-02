package model;

public class ComputerPlayer implements Player {

	private int playerNumber;
	private int pawnsAtHome;
	private int startPosition;
	private Pawn[] pawns;
	private Strategy strategy;
	
	// Constructor
	public ComputerPlayer(int playerNumber){
		this.playerNumber = playerNumber;
		setStartPosition(playerNumber);
		this.pawnsAtHome = 4;
		
	}
	
	/*===============================
	 *    GETTERS AND SETTERS
	 *===============================*/
	@Override
	public int getPlayerNumber() {
		return this.playerNumber;
	}

	@Override
	public void setPlayerNumber(int number) {
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
	public Move getMove(){
		return this.strategy.getNextMove(this);
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
	public void setStartPosition(int player){
		switch(player){
			case(1):
				this.startPosition = 36;
			case(2):
				this.startPosition = 6;
			case(3):
				this.startPosition = 16;
			case(4):
				this.startPosition = 26;
			default:
				//This should never occur
				this.startPosition = -1;
				
		}
				
	}

}
