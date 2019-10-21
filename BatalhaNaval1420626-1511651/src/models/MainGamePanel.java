package models;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.*;
import java.awt.event.*;


public class MainGamePanel extends JPanel implements MouseListener{
	double xIni=30.0,yIni=30.0,larg=80.0,alt=80.0,espLinha=5.0;
	Line2D.Double hor[]=new Line2D.Double[15];
	Line2D.Double ver[]=new Line2D.Double[16];

	private static final long serialVersionUID = 1L;
	
	public MainGamePanel() {
		
		double x=xIni,y=yIni;
		
		addMouseListener(this);

		for(int i=0;i<=15;i++) {
			double x1 = x*(i+1)+650; 
			ver[i]=new Line2D.Double(x1,50.0,x1,500.0);
		}
		
		for(int i=0;i<15;i++) {
			double y1 = y*(i+1)+50; 
			if (i < 13) {
				hor[i]=new Line2D.Double(680.0,y1,1130.0,y1);
			}
			else {
				hor[i]=new Line2D.Double(678.0,y1,1133.0,y1);
			}
		}
			
		this.setLayout(null);    
		this.setVisible(true);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D) g;
		Rectangle2D rt;
		
		g2d.setStroke(new BasicStroke(5.0f,
                BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER,
                10.0f));
		
		for(int i=0;i<=15;i++) {
			g2d.draw(ver[i]);
		}
		for(int i=0;i<15;i++) {
			g2d.draw(new Line2D.Double(678.0,50,1133.0,50));
			g2d.draw(hor[i]);
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		int x=e.getX(),y=e.getY();
		x-=30;
		y-=30;
		
		int j = x/30;
		int i = y/30;
		
		System.out.printf("Clicked %d, %d\n", i, j);
	}
	
	public void mouseEntered(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
