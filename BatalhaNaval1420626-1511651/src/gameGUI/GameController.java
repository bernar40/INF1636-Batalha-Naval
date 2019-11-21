package gameGUI;
import rules.Rules;
import rules.Turn;

import javax.swing.*;
import java.awt.event.*;

enum gameState {
	START,
	SETUP,
	PLAYER1ATTACK,
	PLAYER2ATTACK,
	OVER
}

public class GameController implements ActionListener{

	public static gameState currentGameState = gameState.START;
	public static JFrame    currentGameFrame;
	public static GameController instance;
	public static Rules rules;
	static String winnerName;
	private boolean loaded = false;
	
	public static GameController getInstance() {
		
		if(instance == null) {
			instance = new GameController();
		}
		return instance;
	}
	
	public void start() {
		
		loadGameState();
	}
	
	private void loadGameState() {
		
		if(currentGameFrame != null && loaded == false ) {
			currentGameFrame.dispose();
		}
		
		switch(currentGameState) {
			case START:
				currentGameFrame = new NamesFrame (400, 350);
				break;
			
			case SETUP:
				currentGameFrame = new SetupGameFrame(1200, 600);
				break;
			
			case PLAYER1ATTACK:
			case PLAYER2ATTACK:
				currentGameFrame = new AttackGameFrame (1200, 600);
				break;
				
			case OVER:
				UIManager.put("OptionPane.yesButtonText", "Reiniciar Jogo");
				UIManager.put("OptionPane.noButtonText", "Não");
				UIManager.put("OptionPane.cancelButtonText", "Cancelar");
				int gameOverDialogBox = JOptionPane.showConfirmDialog(currentGameFrame, "Parabens " + winnerName + "!\n Você ganhou o jogo!",
												"Game Over", JOptionPane.INFORMATION_MESSAGE);
				if (gameOverDialogBox == 0) {
					currentGameState = gameState.START;
					Rules.getInstance().reset();
					loadGameState();
				}
				else
					System.exit(0);
				break;
				
			default:
				break;
		}
		
		currentGameFrame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		String action = e.getActionCommand();
				
		// what button was pressed
		
		// 
		if(action.equals("Confirma")) {
			currentGameState = gameState.SETUP;
		}		
		else if(action.equals("Carregar Jogo")) {
			loaded = true;
			Turn playerWithTurn = Rules.getInstance().load();
			
			switch(playerWithTurn) {
				case PLAYER1:
					currentGameState = gameState.PLAYER1ATTACK;
					break;
					
				case PLAYER2:
					currentGameState = gameState.PLAYER2ATTACK;
					break;
				
				default:
					break;
			}
		}		
		else if(action.equals("Acabei!")) {
			Rules.getInstance().changeTurn();
		}
		else if(action.equals("Termina a vez")) {
			Rules.getInstance().changeTurn();
		}
		else if(action.equals("Salvar Jogo")) {
			Rules.getInstance().save();
		}
		if(Rules.getInstance().isAttackPhase()) {
			switchState();
		}
		
		
		loadGameState();
	}
	
	public void setPlayer1Name(String name) 
	{
		Rules.getInstance().setPlayer1Name(name);
	}
	
	public void setPlayer2Name(String name) 
	{
		Rules.getInstance().setPlayer2Name(name);
	}
	
	public void endGame(String winnersName) 
	{
		currentGameState = gameState.OVER;
		winnerName = winnersName;
			
		loadGameState();
	}
	
	private void switchState() 
	{
		switch(currentGameState) 
		{
			case SETUP:
			case PLAYER2ATTACK:
				currentGameState = gameState.PLAYER1ATTACK;
				break;
			
			case PLAYER1ATTACK:
				currentGameState = gameState.PLAYER2ATTACK;
				break;
				
			default:
				break;
			
		}
	}
}