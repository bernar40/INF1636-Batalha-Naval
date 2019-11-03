package gameGUI;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Font;
import java.awt.FontMetrics;


public class MainGamePanel extends JPanel implements MouseListener{
	double xIni=30.0,yIni=30.0,larg=80.0,alt=80.0,espLinha=5.0;
	Line2D.Double hor[]=new Line2D.Double[15];
	Line2D.Double ver[]=new Line2D.Double[16];
	String[] letras = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O"};
	String[] nums = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15"};


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
		
		Font font = new Font("Serif", Font.PLAIN, 12);
		g2d.setFont(font);

		for(int i=0;i<=15;i++) {
			int x1 = 30*(i+1)+650;
			if (i<15)
				g2d.drawString(letras[i], x1+12, 45);
			g2d.draw(ver[i]);
		}
		for(int i=0;i<15;i++) {
			int y1 = 30*(i+1)+50; 
			g2d.drawString(nums[i], 662, y1-12);
			g2d.draw(new Line2D.Double(678.0,50,1133.0,50));
			g2d.draw(hor[i]);
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		int x=e.getX(),y=e.getY();
		
		if(x < 678 || x > 1133)
			return;
		
		if(y < 50 || y > 500)
			return;
		
		x-=678;
		y-=50;
		
		int j = x/30 +1;
		int i = y/30 +1;
		
		System.out.printf("Clicked %d, %d\n", i, j);
	}
	
	public void mouseEntered(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
