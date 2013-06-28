package model;

public class ComputerPlayer implements Player {

	private int playerNumber;
	private int pawnsAtHome;
	private Pawn[] pawns;
	private Strategy strategy;
	
	// Constructor
	public ComputerPlayer(int playerNumber){
		this.playerNumber = playerNumber;
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

}
