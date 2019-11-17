package gameGUI;
import utils.*;
import javax.swing.*;
import models.*;
import rules.*;
import rules.Weapons.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SetupGamePanel extends JPanel implements MouseListener{
	public ArrayList<weaponGraphics> weaponList;
	public ArrayList<weaponGraphics> addedWeaponList;
	public GridGraphics setupGrid;
	public Position iniPos;
	public Position finalPos;
	private static final long serialVersionUID = 1L;
	private weaponGraphics selectedWeapon = null;
	private Rules rules;
	private WeaponRotation rotation = WeaponRotation.ZERO;	
	private JLabel labelName;
	private JButton confirmButton;
	
	
	public SetupGamePanel(Position iniPos, Position finalPos) {
		this.iniPos = iniPos;
		this.finalPos = finalPos;
		rules = Rules.getInstance();
		setupGrid = new GridGraphics (iniPos, finalPos, rules.getInstance().getCurrentPlayerGrid());
		addMouseListener(this);
		setupGrid.buildGrid();
		weaponList =  new ArrayList<weaponGraphics>();
		addedWeaponList = new ArrayList<weaponGraphics>();
		weaponList.add(new weaponGraphics (WeaponType.SUBMARINO, new Position(100, 100), 30, Color.red));
		weaponList.add(new weaponGraphics (WeaponType.SUBMARINO, new Position(150, 100), 30, Color.red));
		weaponList.add(new weaponGraphics (WeaponType.SUBMARINO, new Position(200, 100), 30, Color.red));
		weaponList.add(new weaponGraphics (WeaponType.SUBMARINO, new Position(250, 100), 30, Color.red));
		weaponList.add(new weaponGraphics (WeaponType.DESTROYER, new Position(100, 150), 30, Color.blue));
		weaponList.add(new weaponGraphics (WeaponType.DESTROYER, new Position(200, 150), 30, Color.blue));
		weaponList.add(new weaponGraphics (WeaponType.DESTROYER, new Position(300, 150), 30, Color.blue));
		weaponList.add(new weaponGraphics (WeaponType.CRUZADOR, new Position(100, 200), 30, Color.green));
		weaponList.add(new weaponGraphics (WeaponType.CRUZADOR, new Position(250, 200), 30, Color.green));
		weaponList.add(new weaponGraphics (WeaponType.HIDROAVIAO, new Position(100, 250), 30, Color.yellow));
		weaponList.add(new weaponGraphics (WeaponType.HIDROAVIAO, new Position(200, 250), 30, Color.yellow));
		weaponList.add(new weaponGraphics (WeaponType.HIDROAVIAO, new Position(300, 250), 30, Color.yellow));
		weaponList.add(new weaponGraphics (WeaponType.HIDROAVIAO, new Position(400, 250), 30, Color.yellow));
		weaponList.add(new weaponGraphics (WeaponType.HIDROAVIAO, new Position(500, 250), 30, Color.yellow));
		weaponList.add(new weaponGraphics (WeaponType.COURACADO, new Position(100, 350), 30, Color.black));
		
		//enter name label
		labelName = new JLabel();		
		labelName.setBounds(400, 500, 200, 100);
		
		this.add(labelName);
		
		confirmButton = new JButton("Acabei!");
		confirmButton.setBounds(550, 100, 100, 40);
		confirmButton.addActionListener(GameController.getInstance());
		
		this.add(confirmButton);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setupGrid.paintGrid(g, false);
		for (weaponGraphics w : weaponList)
			w.paint(g);
		
		labelName.setText("Sua vez de selecionar a posi��o de suas armas, " + rules.getInstance().getCurrentPlayerName());
		
		this.add(labelName);
		this.add(confirmButton);
	}
	
	public void mouseClicked(MouseEvent e) {
		
		if (SwingUtilities.isRightMouseButton(e)) {
			if (selectedWeapon != null)
				rotation = rotation.nextRotation();
			return;
		}
		
		int x=e.getX(),y=e.getY();
		
		if(selectedWeapon == null) 
		{
			Boolean weaponWasSelected = false;
			for(weaponGraphics w :weaponList) 
			{
				if(w.wasItClicked(new Position (x, y))) 
				{
					selectedWeapon = w;
					weaponWasSelected = true;
					rotation = rotation.initialize();
				}
			}
						
			System.out.printf("Selected a " + selectedWeapon + "\n");
			
			if(!weaponWasSelected) {
				if(x > iniPos.getX() && x < finalPos.getX()) {
					if(y > iniPos.getY() && y < finalPos.getY()) {
						
						x-=iniPos.getX();
						y-=iniPos.getY();
						
						int j = x/30;
						int i = y/30;
						
						WeaponType wt = rules.getInstance().removeWeaponInCurrentPlayerGrid(new Position(i,j));
						
						//Find weapon in addedWeaponsList
						if(wt != null) 
						{
							for(weaponGraphics w: addedWeaponList) 
							{
								if(w.getWeapon().getType() == wt) 
								{
									System.out.println("Found a " + w.getWeapon().getType());
									weaponList.add(w);
									addedWeaponList.remove(w);
									break;
								}
							}
						}				
					}
				}
			}
		}
		else
			
		{
			if(x < iniPos.getX() || x > finalPos.getX())
				return;
			
			if(y < iniPos.getY() || y > finalPos.getY())
				return;
			
			x-=iniPos.getX();
			y-=iniPos.getY();
			
			int j = x/30;
			int i = y/30;
			
			if(rules.getInstance().setWeaponInCurrentPlayerGrid(new Weapon(selectedWeapon.getWeapon().getType(), rotation), new Position(i,j)))
			{
				addedWeaponList.add(selectedWeapon);
				weaponList.remove(selectedWeapon);				
			}			
			
			System.out.printf("Clicked %d, %d\n", i, j);
			System.out.printf("Rotation is %s\n", rotation);
			selectedWeapon = null;
			
		}			
		
		if(rules.isCurrentPlayersGridFull())
			confirmButton.setVisible(true);
		
		setupGrid = new GridGraphics (iniPos, finalPos, rules.getInstance().getCurrentPlayerGrid());
		setupGrid.buildGrid();
		paintComponent(getGraphics());
	}
	
	public void mouseEntered(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

}