package model;

public class HumanPlayer implements Player {
	private int playerNumber;
	private Strategy strategy;
	private Pawn[] pawns;
	
	//Constructor
	public HumanPlayer(int playerNumber){
		this.playerNumber = playerNumber;
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
}
