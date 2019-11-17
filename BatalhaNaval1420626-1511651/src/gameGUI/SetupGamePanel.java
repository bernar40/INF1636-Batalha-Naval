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
	private Font font = new Font("Serif", Font.PLAIN, 25);
	private WeaponRotation rotation = WeaponRotation.ZERO;
	private boolean weaponSelectedString = false;
	private boolean couldNotPlaceWeapon = false;
	private boolean weaponRemovedFromGrid = false;
	private weaponGraphics weaponTrial = null;
	private JButton confirmButton;
	
	
	public SetupGamePanel(Position iniPos, Position finalPos) {
		this.iniPos = iniPos;
		this.finalPos = finalPos;
		
		rules = Rules.getInstance();
		
		setupGrid = new GridGraphics (iniPos, finalPos, rules.getCurrentPlayerGrid());		
		setupGrid.cleanGrid();
		setupGrid.buildGrid();
		
		addMouseListener(this);
				
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
		
		confirmButton = new JButton("Acabei!");
		confirmButton.setLocation(1050, 520);
		confirmButton.setBounds(1050, 520, 100, 40);
		confirmButton.addActionListener(GameController.getInstance());
		confirmButton.setVisible(false);
		this.add(confirmButton);
		confirmButton.repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setupGrid.paintGrid(g, false);
		for (weaponGraphics w : weaponList)
			w.paint(g);
		
		g.setFont(font);
		g.setColor(Color.BLACK);
		g.drawString("Sua vez de selecionar a posição de suas armas, " + rules.getCurrentPlayerName(), 100, 25);
		
		if (weaponSelectedString) {
			g.setFont(new Font("Arial", Font.PLAIN, 25));
			g.drawString("Selected a " + selectedWeapon.getWeapon().getType() + "\n", 100, 500);
			g.drawString("Rotation is " + rotation, 100, 540);
		}
		if (couldNotPlaceWeapon) {
			g.setFont(new Font("Arial", Font.BOLD, 25));
			g.setColor(Color.RED);
			g.drawString("Could not place " + weaponTrial.getWeapon().getType() + ", try again", 100, 460);
		}
		if (weaponRemovedFromGrid) {
			g.setFont(new Font("Arial", Font.BOLD, 25));
			g.setColor(Color.MAGENTA);
			g.drawString(weaponTrial.getWeapon().getType() + " was removed from the Grid!", 100, 460);
		}
		confirmButton.setLocation(1050, 520);
		confirmButton.setBounds(1050, 520, 100, 40);
		confirmButton.repaint();
	}
	
	public void mouseClicked(MouseEvent e) {
		
		if (SwingUtilities.isRightMouseButton(e)) {
			if (selectedWeapon != null) {
				rotation = rotation.nextRotation();
				paintComponent(getGraphics());
			}
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
					weaponTrial = w;
					rotation = rotation.initialize();
					weaponSelectedString = true;
					couldNotPlaceWeapon = false;
					weaponWasSelected = true;
					weaponRemovedFromGrid = false;
					paintComponent(getGraphics());
					System.out.printf("Selected a " + selectedWeapon.getWeapon().getType() + "\n");
				}
			}
						
			
			if(!weaponWasSelected) {
				if(x > iniPos.getX() && x < finalPos.getX()) {
					if(y > iniPos.getY() && y < finalPos.getY()) {
						
						x-=iniPos.getX();
						y-=iniPos.getY();
						
						int j = x/30;
						int i = y/30;
						
						WeaponType wt = rules.removeWeaponInCurrentPlayerGrid(new Position(i,j));
						
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
							weaponRemovedFromGrid = true;
							paintComponent(getGraphics());
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
			
			if(rules.setWeaponInCurrentPlayerGrid(new Weapon(selectedWeapon.getWeapon().getType(), rotation), new Position(i,j)))
			{
				addedWeaponList.add(selectedWeapon);
				weaponList.remove(selectedWeapon);	
				weaponSelectedString = false;
				couldNotPlaceWeapon = false;
				paintComponent(getGraphics());
			}
			else {
				couldNotPlaceWeapon = true;
			}
			
			System.out.printf("Clicked %d, %d\n", i, j);
			System.out.printf("Rotation is %s\n", rotation);
			selectedWeapon = null;
			weaponSelectedString = false;
		}			
		
		
		setupGrid = new GridGraphics (iniPos, finalPos, rules.getCurrentPlayerGrid());
		setupGrid.buildGrid();

		if(rules.isCurrentPlayersGridFull())
			confirmButton.setVisible(true);
		else
			confirmButton.setVisible(false);
		this.add(confirmButton);
		paintComponent(getGraphics());
	}
	
	public void mouseEntered(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

}