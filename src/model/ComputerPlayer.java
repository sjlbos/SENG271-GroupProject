package model;

public class ComputerPlayer implements Player {

	private int playerNumber;
	private int pawnsAtHome;
	private int startPosition;
	private Pawn[] pawns;
	private Strategy strategy;
	
	// Constructor
	public ComputerPlayer(int playerNumber, Strategy strategy){
		this.playerNumber = playerNumber;
		this.startPosition = determineStartPosition(playerNumber);
		this.pawnsAtHome = 4;
		this.strategy = strategy;
		pawns = new Pawn[4];
		for(int i=0;i<4;i++){
			pawns[i] = new Pawn(this);
		}
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

	@Override
	public void incrementPawnsAtHome() {
		this.pawnsAtHome++;
		
	}

	
	@Override
	public void decrementPawnsAtHome() {
		this.pawnsAtHome--;
		
	}

	public Pawn makeMove(int currentRoll, Pawn[] moveablePawns, Field[] gameBoard) {
		Pawn pawn = this.strategy.getNextMove(currentRoll,moveablePawns,gameBoard);
		return pawn;
	}

}
