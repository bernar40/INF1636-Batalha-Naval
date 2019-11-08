package rules;
import rules.Weapons.*;
import utils.*;

public class Rules {

	static Turn playerTurn = Turn.PLAYER1;
	static Player player1, player2;
	
	public Rules(String namePlayer1, String namePlayer2) 
	{
		player1 = new Player(namePlayer1);
		player2 = new Player(namePlayer2);
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
}