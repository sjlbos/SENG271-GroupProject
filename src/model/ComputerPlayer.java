package model;

public class ComputerPlayer implements Player {

	private int playerNumber;
	private Pawn[] pawns;
	private Strategy strategy;
	
	// Constructor
	public ComputerPlayer(int playerNumber){
		this.playerNumber = playerNumber;
		
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

}
