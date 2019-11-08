package gameGUI;
import utils.*;
import javax.swing.*;
import models.*;
import rules.*;
import rules.Weapons.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MainGamePanel extends JPanel implements MouseListener{
	public weaponGraphics submarino1;
	public weaponGraphics submarino2;
	public weaponGraphics submarino3;
	public weaponGraphics submarino4;
	public weaponGraphics destroyer1;
	public weaponGraphics destroyer2;
	public weaponGraphics destroyer3;
	public weaponGraphics cruzador1;
	public weaponGraphics cruzador2;
	public weaponGraphics hidroaviao1;
	public weaponGraphics hidroaviao2;
	public weaponGraphics hidroaviao3;
	public weaponGraphics hidroaviao4;
	public weaponGraphics hidroaviao5;
	public weaponGraphics couracado;
	public ArrayList<weaponGraphics> weaponList;
	public GridGraphics setupGrid;
	public Position iniPos;
	public Position finalPos;
	private static final long serialVersionUID = 1L;
	private WeaponType selectedWeaponType = WeaponType.SUBMARINO;
	private Rules rules = new Rules("victor", "bernardo");
	
	
	public MainGamePanel(Position iniPos, Position finalPos) {
		this.iniPos = iniPos;
		this.finalPos = finalPos;
		setupGrid = new GridGraphics (iniPos, finalPos, rules.getCurrentPlayerOwnGrid());
		addMouseListener(this);
		setupGrid.buildGrid();
		weaponList =  new ArrayList<weaponGraphics>();
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
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setupGrid.paintGrid(g);
		for (weaponGraphics w : weaponList)
			w.paint(g);
	}
	
	public void mouseClicked(MouseEvent e) {
		int x=e.getX(),y=e.getY();
		
		if(selectedWeaponType == null) 
		{
			for(weaponGraphics w :weaponList)
				if(w.wasItClicked(new Position (x, y)))
					selectedWeaponType = w.getWeapon().getType();
			System.out.printf("Selected a " + selectedWeaponType);
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
			
			rules.setWeaponInCurrentPlayerGrid(new Weapon(selectedWeaponType), new Position(i,j));
			
			System.out.printf("Clicked %d, %d\n", i, j);
			selectedWeaponType = null;
			
		}			
		
		setupGrid = new GridGraphics (iniPos, finalPos, rules.getCurrentPlayerOwnGrid());
		setupGrid.buildGrid();
		paintComponent(getGraphics());
	}
	
	public void mouseEntered(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

}
