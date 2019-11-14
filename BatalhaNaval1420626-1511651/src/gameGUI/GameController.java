package gameGUI;

import javax.swing.*;

import rules.Rules;

import java.awt.*;
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
		
		if(currentGameFrame != null) {
			currentGameFrame.dispose();
		}
		
		switch(currentGameState) {
		case START:
			currentGameFrame = new NamesFrame (400, 350);
			break;
		
		case SETUP:
			currentGameFrame = new MainGameFrame(1200, 600);
			break;
			
		default:
			break;
		}
		
		currentGameFrame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		String action = e.getActionCommand();
				
		// what button was pressed
		if(action.equals("Confirma")) {
			currentGameState = gameState.SETUP;
		}
		
		if(action.equals("Acabei!")) {
			Rules.getInstance().changeTurn();
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

}