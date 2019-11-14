package gameGUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NamesPanel extends JPanel{
	
	private JTextField textfieldPlayer1;
	private JTextField textfieldPlayer2;
	
	public NamesPanel() {
		JButton confirmButton = new JButton("Confirma");
		confirmButton.setBounds(100, 200, 140, 40);
		confirmButton.addActionListener(GameController.getInstance());
	 
		//Player 1
		JLabel labelPlayer1 = new JLabel();		
		labelPlayer1.setText("Nome do Primeiro Jogador:");
		labelPlayer1.setBounds(10, 10, 200, 100);
		textfieldPlayer1 = new JTextField();
		textfieldPlayer1.setBounds(210, 50, 130, 30);
		
		//enter name label
		JLabel labelPlayer2 = new JLabel();		
		labelPlayer2.setText("Nome do Segundo Jogador:");
		labelPlayer2.setBounds(10, 100, 200, 100);
		textfieldPlayer2 = new JTextField();
		textfieldPlayer2.setBounds(210, 150, 130, 30);
		
		//add to frame
		this.add(labelPlayer1);
		this.add(textfieldPlayer1);
		this.add(labelPlayer2);
		this.add(textfieldPlayer2);
		this.add(confirmButton);    
		this.setSize(300,300);    
		this.setLayout(null);    
		this.setVisible(true);    
	}
	
	public void getTextField1() {
		GameController.getInstance().setPlayer1Name(textfieldPlayer1.getText());
	}
	
	public void getTextField2() {
		GameController.getInstance().setPlayer2Name(textfieldPlayer2.getText());
	}
 
}
