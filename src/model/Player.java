package model;

public interface Player {
	
	public int getPlayerNumber();
	public void setPlayerNumber(int number);
	
	public Pawn[] getPawns();
	
	public Strategy getStrategy();
	public void setStrategy(Strategy strategy);
}
