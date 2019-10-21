package gameGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

enum gameState {
	START,
	SETUP,
	PLAYER1ATTACK,
	PLAYER2ATTACK,
	OVER
}

public class Game implements ActionListener{

	public static gameState currentGameState = gameState.START;
	public static JFrame    currentGameFrame;
	
	public void start() {
		
		loadGameState();
	}
	
	private void loadGameState() {
		
		if(currentGameFrame != null) {
			currentGameFrame.dispose();
		}
		
		switch(currentGameState) {
		case START:
			currentGameFrame = new NamesFrame (400,350, this);
			break;
		
		case SETUP:
			currentGameFrame = new MainGameFrame(800, 800);
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

		loadGameState();
	}

}