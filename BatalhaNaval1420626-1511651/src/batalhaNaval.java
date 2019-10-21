import models.*;

enum gameState {
	START,
	SETUP,
	PLAYER1ATTACK,
	PLAYER2ATTACK,
	OVER
}

public class batalhaNaval {

	public static gameState currentGameState = gameState.SETUP;
	
	public static void main(String[] args) {
		
		switch(currentGameState) {
			case START:
				(new NamesFrame(400,350)).setVisible(true);
				break;
			
			case SETUP:
				(new MainGameFrame(900,900)).setVisible(true);
				break;
				
			default:
				break;
		}
	}

}
