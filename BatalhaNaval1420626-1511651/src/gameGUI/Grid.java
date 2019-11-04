package gameGUI;
import java.awt.geom.Line2D;
import java.awt.*;

import utils.*;

public class Grid {
	private double iniX;
	private double iniY;
	private double finalX;
	private double finalY;
	private double skipX = 30.0;
	private double skipY = 30.0;
	private float miterLimit=10,espLinha=5;
	public Line2D.Double hor[]=new Line2D.Double[15];
	public Line2D.Double ver[]=new Line2D.Double[16];
	private Font font = new Font("Serif", Font.PLAIN, 12);
	private String[] letras = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O"};
	private String[] nums = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15"};

	
	public Grid(Position iniPosition, Position finalPosition) {
		iniX = iniPosition.getX();
		iniY = iniPosition.getY();
		finalX = finalPosition.getX();
		finalY = finalPosition.getY();
		
	}
		
	public void buildGrid() {

		for(int i=0;i<=15;i++) {
			double x1 = skipX*(i+1)+(iniX-skipX); 
			ver[i]=new Line2D.Double(x1,iniY,x1,finalY);
		}
		for(int i=0;i<15;i++) {
			double y1 = skipY*(i+1)+iniY; 
			if (i < 13) {
				hor[i]=new Line2D.Double(iniX,y1,finalX,y1);
			}
			else {
				hor[i]=new Line2D.Double(iniX,y1,finalX,y1);
			}
		}
	}
	
	public void paintGrid(Graphics g) {
		Graphics2D g2d=(Graphics2D) g;
		
		g2d.setStroke(new BasicStroke(espLinha,
                BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER,
                miterLimit));
		
		g2d.setFont(font);

		for(int i=0;i<=15;i++) {
			double x1 = skipX*(i+1)+(iniX-skipX);
			if (i<15)
				g2d.drawString(letras[i], (int) (x1) + 12, 45);
			g2d.draw(ver[i]);
		}
		for(int i=0;i<15;i++) {
			double y1 = skipY*(i+1)+iniY; 
			g2d.drawString(nums[i], (int) (iniX-18), (int) (y1-12));
			g2d.draw(new Line2D.Double(iniX,iniY,finalX,iniY));
			g2d.draw(hor[i]);
		}
	}
}
