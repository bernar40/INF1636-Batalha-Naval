package gameGUI;
import utils.*;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.*;
import java.awt.event.*;



public class MainGamePanel extends JPanel implements MouseListener{
	public Grid setupGrid;
	public Position iniPos;
	public Position finalPos;
	private static final long serialVersionUID = 1L;
	
	public MainGamePanel(Position iniPos, Position finalPos) {
		this.iniPos = iniPos;
		this.finalPos = finalPos;
		setupGrid = new Grid (iniPos, finalPos);
		addMouseListener(this);
		setupGrid.buildGrid();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setupGrid.paintGrid(g);	
	}
	
	public void mouseClicked(MouseEvent e) {
		int x=e.getX(),y=e.getY();
		
		if(x < iniPos.getX() || x > finalPos.getX())
			return;
		
		if(y < iniPos.getY() || y > finalPos.getY())
			return;
		
		x-=iniPos.getX();
		y-=iniPos.getY();
		
		int j = x/30 +1;
		int i = y/30 +1;
		
		System.out.printf("Clicked %d, %d\n", i, j);
	}
	
	public void mouseEntered(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

}
