package rules;
import rules.Weapons.*;
import utils.*;

public class Rules {

	static Turn playerTurn = Turn.PLAYER1;
	static Player player1, player2;
	static String namePlayer1, namePlayer2;
	public static Rules instance;
	
	private Rules(String namePlayer1, String namePlayer2) 
	{
		player1 = new Player();
		player2 = new Player();
	}
	
	public static Rules getInstance() {
		if(instance == null)
			instance = new Rules(namePlayer1, namePlayer2);
		
		return instance;
	}
	
	public void setPlayer1Name(String name) {
		player1.setName(name);
	}
	
	public void setPlayer2Name(String name) {
		player2.setName(name);
	}
	
	public static String getCurrentPlayerName() 
	{
		switch (playerTurn) 
		{
			case PLAYER1:
				return player1.getName();
				
			case PLAYER2:
				return player2.getName();
				
			default:
				return "";
		}
	}
	
	public Grid getCurrentPlayerOwnGrid() 
	{
		switch (playerTurn) 
		{
			case PLAYER1:
				return player1.getOwnGrid();
				
			case PLAYER2:
				return player2.getOwnGrid();
				
			default:
				return null;
		}
	}
	
	public static Grid getCurrentPlayerEnemyGrid() 
	{
		switch (playerTurn) 
		{
			case PLAYER1:
				return player1.getEnemyGrid();
				
			case PLAYER2:
				return player2.getEnemyGrid();
				
			default:
				return null;
		}
	}
	
	public static Grid getOppositePlayerOwnGrid() 
	{
		switch (playerTurn) 
		{
			case PLAYER1:
				return player2.getOwnGrid();
				
			case PLAYER2:
				return player1.getOwnGrid();
				
			default:
				return null;
		}
	}
	
	public Boolean setWeaponInCurrentPlayerGrid(Weapon weapon, Position upperLeftCorner) 
	{
		switch (playerTurn) 
		{
			case PLAYER1:
				return player1.setWeaponInGrid(weapon, upperLeftCorner);
				
			case PLAYER2:
				return player2.setWeaponInGrid(weapon, upperLeftCorner);
				
			default:
				return null;
		}
	}
	
	public WeaponType removeWeaponInCurrentPlayerGrid(Position p) 
	{
		switch (playerTurn) 
		{
			case PLAYER1:
				return player1.removeWeaponFromGrid(p);
				
			case PLAYER2:
				return player2.removeWeaponFromGrid(p);
				
			default:
				return null;
		}
	}
	
	public void changeTurn() 
	{
		switch (playerTurn) 
		{
			case PLAYER1:
				playerTurn = Turn.PLAYER2;
				break;
				
			case PLAYER2:
				playerTurn = Turn.PLAYER1;
				break;
				
			default:
				break;				
		}
	}
}