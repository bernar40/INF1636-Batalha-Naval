package gameGUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameOverPanel extends JPanel{
	
	public GameOverPanel(String winnerName) {
		JButton confirmButton = new JButton("Reiniciar o jogo");
		confirmButton.setBounds(80, 80, 140, 40);
		confirmButton.addActionListener(GameController.getInstance());
	 
		JLabel labelWinner = new JLabel();		
		labelWinner.setText("Parabens " + winnerName + "! \n Você ganhou o jogo!");
		labelWinner.setBounds(10, 10, 300, 40);		
		
		//add to frame
		this.add(labelWinner);
		this.add(confirmButton);    
		this.setSize(200,200);    
		this.setLayout(null);    
		this.setVisible(true);    
	}
}
