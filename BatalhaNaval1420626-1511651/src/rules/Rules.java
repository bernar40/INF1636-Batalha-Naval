package rules;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFileChooser;

import rules.Weapons.*;
import utils.*;
import java.io.*; 


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
				player2.resetHitCount();
				playerTurn = Turn.PLAYER2;
				break;
				
			case PLAYER2:
				player1.resetHitCount();
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
	
	public void hit()
	{
		switch (playerTurn) 
		{
			case PLAYER1:
				player1.discountHit();
				break;
							
			case PLAYER2:
				player2.discountHit();
				break;
				
			default:
				break;
		}
	}
	
	public boolean attackOver()
	{
		switch (playerTurn) 
		{
			case PLAYER1:
				return player1.noMoreHitsLeft();
							
			case PLAYER2:
				return player2.noMoreHitsLeft();
				
			default:
				return false;
		} 
	}
	
	public void reset ()
	{
		playerTurn = Turn.PLAYER1;
		state = GameState.SETUPPLAYER1;	
		player1.reset();
		player2.reset();
		winningPlayerName = "";

	}
	
	public WeaponType getWeaponFromOpposition(Position p) {
		switch (playerTurn) 
		{
			case PLAYER1:
				return player2.getWeaponFromPosition(p);
							
			case PLAYER2:
				return player1.getWeaponFromPosition(p);
				
			default:
				return WeaponType.MISSED;
		}
	}
	
	public void save() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("savedGames/"));
		int resultado = fileChooser.showSaveDialog(null);
        if (resultado == JFileChooser.APPROVE_OPTION) 
        {
			BufferedWriter out = null;
			try {
				FileWriter fstream = new FileWriter(fileChooser.getSelectedFile() + ".sg", false);
			    out = new BufferedWriter(fstream);
			    
			    
			    out.write(playerTurn.toString() + "\n");
			    
			    //Player 1 
			    out.write(player1.getName() + "\n");
			    out.write(player1.getNumberOfAliveWeapons() + "\n");
			    out.write(player1.getOwnGrid().toString()); 
			    
			    //Player 2 
			    out.write(player2.getName() + "\n");
			    out.write(player2.getNumberOfAliveWeapons() + "\n");
			    out.write(player2.getOwnGrid().toString());
			}
			catch (IOException e) {
			    System.err.println("Error: " + e.getMessage());
			}
	
			finally {
			    if(out != null) {
			        try {
						out.close();
					} catch (IOException e) {
						System.err.println("Error: " + e.getMessage());
					}
			    }
			}
        }
	}
	
	public Turn load() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("savedGames/"));
		int resultado = fileChooser.showSaveDialog(null);
        if (resultado == JFileChooser.APPROVE_OPTION) 
        {
			try
			{
				File file = new File(fileChooser.getSelectedFile(), ""); 
				BufferedReader br = new BufferedReader(new FileReader(file));
				String st;
				while ((st = br.readLine()) != null) {
					
					if(st == "PLAYER1")
						playerTurn = Turn.PLAYER1;
					else if(st == "PLAYER2")
						playerTurn = Turn.PLAYER2;
					
					st = br.readLine();
					player1.setName(st);
					
					st = br.readLine();
					player1.setNumberOfAliveWeapons(st);
					
					for(int i = 0; i< 15; i++) {
						st = br.readLine();
						
						String[] values = st.split(";");
						
						int matrixIndex = 0;
						
						for(String val: values) {
							String[] posValues = val.split(",");
							
							WeaponType wType = null;
							
							if(posValues[1].equals("COURACADO"))
								wType = WeaponType.COURACADO;
							else if(posValues[1].equals("SUBMARINO"))
								wType = WeaponType.SUBMARINO;
							else if(posValues[1].equals("CRUZADOR"))
								wType = WeaponType.CRUZADOR;
							else if(posValues[1].equals("HIDROAVIAO"))
								wType = WeaponType.HIDROAVIAO;
							else if(posValues[1].equals("DESTROYER"))
								wType = WeaponType.DESTROYER;
							else if(posValues[1].equals("PARTIALLYHIT"))
								wType = WeaponType.PARTIALLYHIT;
							else if(posValues[1].equals("COMPLETELYHIT"))
								wType = WeaponType.COMPLETELYHIT;
							else if(posValues[1].equals("MISSED"))
								wType = WeaponType.MISSED;
							
							int index = Integer.parseInt(posValues[0]);					
							GridValue gridVal = new GridValue(index, wType);
							
							player1.setGridValue(new Position(i, matrixIndex), gridVal);
							
							matrixIndex++;
						}
					}
				    st = br.readLine();				    
				    player2.setName(st);
					
					st = br.readLine();
					player2.setNumberOfAliveWeapons(st);
					
					for(int i = 0; i< 15; i++) {
						st = br.readLine();
						
						String[] values = st.split(";");
						
						int matrixIndex = 0;
						
						for(String val: values) {
							String[] posValues = val.split(",");
							
							WeaponType wType = null;
							
							if(posValues[1].equals("COURACADO"))
								wType = WeaponType.COURACADO;
							else if(posValues[1].equals("SUBMARINO"))
								wType = WeaponType.SUBMARINO;
							else if(posValues[1].equals("CRUZADOR"))
								wType = WeaponType.CRUZADOR;
							else if(posValues[1].equals("HIDROAVIAO"))
								wType = WeaponType.HIDROAVIAO;
							else if(posValues[1].equals("DESTROYER"))
								wType = WeaponType.DESTROYER;
							else if(posValues[1].equals("PARTIALLYHIT"))
								wType = WeaponType.PARTIALLYHIT;
							else if(posValues[1].equals("COMPLETELYHIT"))
								wType = WeaponType.COMPLETELYHIT;
							else if(posValues[1].equals("MISSED"))
								wType = WeaponType.MISSED;
							
							int index = Integer.parseInt(posValues[0]);					
							GridValue gridVal = new GridValue(index, wType);
							
							player2.setGridValue(new Position(i, matrixIndex), gridVal);
							
							matrixIndex++;
						}
					}
				}
				state = GameState.ATTACK;
			}
			catch (IOException e) {
			    System.err.println("Error: " + e.getMessage());
			}
        }
        
        return playerTurn;
	}
}