package model;

public interface Player {
	
	public int getPlayerNumber();
	public void setPlayerNumber(int number);
	
	public Pawn[] getPawns();
	
	public Strategy getStrategy();
	public void setStrategy(Strategy strategy);
	
	public int getPawnsAtHome();
	public void setPawnsAtHome(int pawnsAtHome);
	public void incrementPawnsAtHome();
	public void decrementPawnsAtHome();
	
	public int getStartPosition();
	public int determineStartPosition(int player);
}
