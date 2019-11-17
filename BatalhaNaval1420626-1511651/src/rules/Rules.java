package rules;
import rules.Weapons.*;
import utils.*;

public class Rules {

	static Turn playerTurn = Turn.PLAYER1;
	static GameState state = GameState.SETUPPLAYER1;
	static Player player1, player2;
	static String namePlayer1, namePlayer2;
	static String winningPlayerName = "";
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
	
	public String getCurrentPlayerName() 
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
	
	public Boolean isCurrentPlayersGridFull() 
	{
		switch (playerTurn) 
		{
			case PLAYER1:
				return player1.isGridFull();
				
			case PLAYER2:
				return player2.isGridFull();
				
			default:
				return false;
		}
	}
	
	public Grid getCurrentPlayerGrid() 
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
	
	public Grid getOppositePlayerGrid() 
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
	
	public Grid getOppositePlayerOwnGrid() 
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
	
	public Boolean hitWeaponInPositionInGrid(Position p) 
	{
		Player currentPlayer;
		Player oppositePlayer;
		
		switch (playerTurn) 
		{
			case PLAYER1:
				currentPlayer = player1;
				oppositePlayer = player2;
				break;
				
				
			case PLAYER2:
				currentPlayer = player2;
				oppositePlayer = player1;
				break;
				
			default:
				return false;
		}
		
		// Already hit this position
		if(oppositePlayer.hasAlreadyBeenShotInGrid(p))
			return false;
		
		int weaponIndex = oppositePlayer.checkForWeaponInPosition(p);
		
		// Player missed
		if(weaponIndex == -1) 
		{
			oppositePlayer.setMissedValueInOwnGrid(100, p);
			System.out.println("Missed!");
		}
		else 
		{
			oppositePlayer.setHitValueInOwnGrid(weaponIndex, p);
			oppositePlayer.checkForCompletelyDestroyedWeaponInOwnGrid(weaponIndex);
			System.out.println("HIT!");
			
			if(oppositePlayer.checkIfNoWeaponsLeft()) {
				winningPlayerName = currentPlayer.getName();
				state = GameState.GAMEFINISHED;
			}
		}
		
		return true;	
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
		
		switch (state) {
			case SETUPPLAYER1:
				state = GameState.SETUPPLAYER2;
				break;
				
			case SETUPPLAYER2:
				state = GameState.ATTACK;
				break;
				
			default:
				break;
		}
	}
	
	public Boolean isAttackPhase() 
	{
		return state == GameState.ATTACK;
	}
	
	public Boolean isGameOver() 
	{
		return state == GameState.GAMEFINISHED;
	}
	
	public Boolean isSetupPhase() 
	{
		return state == GameState.SETUPPLAYER1 || state == GameState.SETUPPLAYER2;
	}
}