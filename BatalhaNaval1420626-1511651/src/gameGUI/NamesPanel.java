package gameGUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NamesPanel extends JPanel{
	
	public NamesPanel(ActionListener e) {
		JButton confirmButton = new JButton("Confirma");
		confirmButton.setBounds(100, 200, 140, 40);
		confirmButton.addActionListener(e);
	 
		//Player 1
		JLabel labelPlayer1 = new JLabel();		
		labelPlayer1.setText("Nome do Primeiro Jogador:");
		labelPlayer1.setBounds(10, 10, 200, 100);
		JTextField textfieldPlayer1 = new JTextField();
		textfieldPlayer1.setBounds(210, 50, 130, 30);
		
		//enter name label
		JLabel labelPlayer2 = new JLabel();		
		labelPlayer2.setText("Nome do Segundo Jogador:");
		labelPlayer2.setBounds(10, 100, 200, 100);
		JTextField textfieldPlayer2 = new JTextField();
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
 
}
