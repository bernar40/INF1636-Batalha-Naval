package gameGUI;
import utils.*;
import javax.swing.*;
import models.weaponGraphics;
import rules.Weapons.WeaponType;
import models.GridGraphics;
import java.awt.geom.*;
import java.awt.*;
import java.awt.event.*;

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
	public GridGraphics setupGrid;
	public Position iniPos;
	public Position finalPos;
	public Position sub1UpperLeftCorner = new Position (100, 100);
	public Position sub2UpperLeftCorner = new Position (150, 100);
	public Position sub3UpperLeftCorner = new Position (200, 100);
	public Position sub4UpperLeftCorner = new Position (250, 100);
	public Position destroyer1UpperLeftCorner = new Position (100, 150);
	public Position destroyer2UpperLeftCorner = new Position (200, 150);
	public Position destroyer3UpperLeftCorner = new Position (300, 150);
	public Position cruzador1UpperLeftCorner = new Position (100, 200);
	public Position cruzador2UpperLeftCorner = new Position (250, 200);
	public Position hidroaviao1UpperLeftCorner = new Position (100, 250);
	public Position hidroaviao2UpperLeftCorner = new Position (200, 250);
	public Position hidroaviao3UpperLeftCorner = new Position (300, 250);
	public Position hidroaviao4UpperLeftCorner = new Position (400, 250);
	public Position hidroaviao5UpperLeftCorner = new Position (500, 250);
	public Position couracadoUpperLeftCorner = new Position (100, 350);
	private static final long serialVersionUID = 1L;
	
	public MainGamePanel(Position iniPos, Position finalPos) {
		this.iniPos = iniPos;
		this.finalPos = finalPos;
		setupGrid = new GridGraphics (iniPos, finalPos);
		addMouseListener(this);
		setupGrid.buildGrid();
		submarino1 = new weaponGraphics (WeaponType.SUBMARINO, sub1UpperLeftCorner, 30, Color.red);
		submarino2 = new weaponGraphics (WeaponType.SUBMARINO, sub2UpperLeftCorner, 30, Color.red);
		submarino3 = new weaponGraphics (WeaponType.SUBMARINO, sub3UpperLeftCorner, 30, Color.red);
		submarino4 = new weaponGraphics (WeaponType.SUBMARINO, sub4UpperLeftCorner, 30, Color.red);
		destroyer1 = new weaponGraphics (WeaponType.DESTROYER, destroyer1UpperLeftCorner, 30, Color.blue);
		destroyer2 = new weaponGraphics (WeaponType.DESTROYER, destroyer2UpperLeftCorner, 30, Color.blue);
		destroyer3 = new weaponGraphics (WeaponType.DESTROYER, destroyer3UpperLeftCorner, 30, Color.blue);
		cruzador1 = new weaponGraphics (WeaponType.CRUZADOR, cruzador1UpperLeftCorner, 30, Color.green);
		cruzador2 = new weaponGraphics (WeaponType.CRUZADOR, cruzador2UpperLeftCorner, 30, Color.green);
		hidroaviao1 = new weaponGraphics (WeaponType.HIDROAVIAO, hidroaviao1UpperLeftCorner, 30, Color.yellow);
		hidroaviao2 = new weaponGraphics (WeaponType.HIDROAVIAO, hidroaviao2UpperLeftCorner, 30, Color.yellow);
		hidroaviao3 = new weaponGraphics (WeaponType.HIDROAVIAO, hidroaviao3UpperLeftCorner, 30, Color.yellow);
		hidroaviao4 = new weaponGraphics (WeaponType.HIDROAVIAO, hidroaviao4UpperLeftCorner, 30, Color.yellow);
		hidroaviao5 = new weaponGraphics (WeaponType.HIDROAVIAO, hidroaviao5UpperLeftCorner, 30, Color.yellow);
		couracado = new weaponGraphics (WeaponType.COURACADO, couracadoUpperLeftCorner, 30, Color.black);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setupGrid.paintGrid(g);	
		submarino1.paint(g);
		submarino2.paint(g);
		submarino3.paint(g);
		submarino4.paint(g);
		destroyer1.paint(g);
		destroyer2.paint(g);
		destroyer3.paint(g);
		cruzador1.paint(g);
		cruzador2.paint(g);
		hidroaviao1.paint(g);
		hidroaviao2.paint(g);
		hidroaviao3.paint(g);
		hidroaviao4.paint(g);
		hidroaviao5.paint(g);
		couracado.paint(g);
	}
	
	public void mouseClicked(MouseEvent e) {
		int x=e.getX(),y=e.getY();

		if (submarino1.wasItClicked(new Position (x, y)))
			// do something
		if (submarino2.wasItClicked(new Position (x, y)))
			// do something
		if (submarino3.wasItClicked(new Position (x, y)))
			// do something
		if (submarino4.wasItClicked(new Position (x, y)))
			// do something
		if (destroyer1.wasItClicked(new Position (x, y)))
			// do something
		if (destroyer2.wasItClicked(new Position (x, y)))
			// do something
		if (destroyer3.wasItClicked(new Position (x, y)))
			// do something
		if (cruzador1.wasItClicked(new Position (x, y)))
			// do something
		if (cruzador2.wasItClicked(new Position (x, y)))
			// do something			
		if (hidroaviao1.wasItClicked(new Position (x, y)))
			// do something		
		if (hidroaviao2.wasItClicked(new Position (x, y)))
			// do something	
		if (hidroaviao3.wasItClicked(new Position (x, y)))
			// do something	
		if (hidroaviao4.wasItClicked(new Position (x, y)))
			// do something
		if (hidroaviao5.wasItClicked(new Position (x, y)))
			// do something	
		if (couracado.wasItClicked(new Position (x, y)))
			// do something	
			
		if(x < iniPos.getX() || x > finalPos.getX())
			return;
		
		if(y < iniPos.getY() || y > finalPos.getY())
			return;
		
		x-=iniPos.getX();
		y-=iniPos.getY();
		
		int j = x/30;
		int i = y/30;
		
		setupGrid.setGridValue(new Position(i, j));
		
		System.out.printf("Clicked %d, %d\n", i, j);
		
		paintComponent(getGraphics());
	}
	
	public void mouseEntered(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

}
