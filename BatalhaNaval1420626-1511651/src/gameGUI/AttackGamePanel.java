package gameGUI;
import utils.*;
import javax.swing.*;
import models.*;
import rules.*;
import rules.Weapons.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class AttackGamePanel extends JPanel implements MouseListener{
	public GridGraphics currentPlayerGrid;
	public GridGraphics oppositePlayerGrid;
	public Position iniPosLeftGrid;
	public Position finalPosLeftGrid;
	public Position iniPosRightGrid;
	public Position finalPosRightGrid;
	private static final long serialVersionUID = 1L;
	private Rules rules;
	private JLabel labelName;
	private JButton confirmButton;
	private Boolean alreadyAttacked = false;
	
	
	public AttackGamePanel(Position iniPosLeftGrid,Position finalPosLeftGrid, Position iniPosRightGrid, Position finalPosRightGrid) {
		this.iniPosLeftGrid = iniPosLeftGrid;
		this.finalPosLeftGrid = finalPosLeftGrid;
		this.iniPosRightGrid = iniPosRightGrid;
		this.finalPosRightGrid = finalPosRightGrid;
		rules = Rules.getInstance();
		currentPlayerGrid = new GridGraphics (iniPosLeftGrid, finalPosLeftGrid, rules.getCurrentPlayerGrid());
		oppositePlayerGrid = new GridGraphics (iniPosRightGrid, finalPosRightGrid, rules.getOppositePlayerGrid());
		oppositePlayerGrid.buildGrid();
		currentPlayerGrid .buildGrid();
		//enter name label
		labelName = new JLabel();		
		labelName.setBounds(400, 500, 200, 100);
		
		this.add(labelName);
		
		confirmButton = new JButton("Termina a vez");
		confirmButton.setBounds(550, 100, 100, 40);
		confirmButton.addActionListener(GameController.getInstance());
		
		this.add(confirmButton);
		addMouseListener(this);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		currentPlayerGrid.paintGrid(g, false);
		oppositePlayerGrid.paintGrid(g, true);
		
		labelName.setText("Sua vez de atacar, " + rules.getCurrentPlayerName());
		
		this.add(labelName);
		
		if(alreadyAttacked)
			confirmButton.setVisible(true);
		this.add(confirmButton);
	}
	
	public void mouseClicked(MouseEvent e) {
		
		if(alreadyAttacked)
			return;
		
		int x=e.getX(),y=e.getY();
			
		
		if(x < iniPosRightGrid.getX() || x > finalPosRightGrid.getX())
			return;
		
		if(y < iniPosRightGrid.getY() || y > finalPosRightGrid.getY())
			return;
		
		x-=iniPosRightGrid.getX();
		y-=iniPosRightGrid.getY();
		
		int j = x/30;
		int i = y/30;
		
		if(rules.hitWeaponInPositionInGrid(new Position(i,j))) {
			if(rules.isGameOver())
				GameController.getInstance().endGame(rules.getCurrentPlayerName());
			
			System.out.printf("Clicked %d, %d\n", i, j);
						
			
			currentPlayerGrid 	= new GridGraphics (iniPosLeftGrid, finalPosLeftGrid, rules.getCurrentPlayerGrid());
			oppositePlayerGrid 	= new GridGraphics (iniPosRightGrid, finalPosRightGrid, rules.getOppositePlayerGrid());
			oppositePlayerGrid.buildGrid();
			currentPlayerGrid .buildGrid();
			paintComponent(getGraphics()); 
			
			alreadyAttacked = true;
		}
		
	}
	
	public void mouseEntered(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

}