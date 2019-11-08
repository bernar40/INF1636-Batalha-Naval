package models;
import java.awt.geom.Line2D;
import java.awt.*;
import utils.*;

public class GridGraphics {
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
	private Grid gridValues;

	
	public GridGraphics(Position iniPosition, Position finalPosition, Grid grid) {
		iniX = iniPosition.getX();
		iniY = iniPosition.getY();
		finalX = finalPosition.getX();
		finalY = finalPosition.getY();
		gridValues = grid;	
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
	
	public void setGridValue(Position p) {
		
		GridValue gridVal = gridValues.getValue(p);
		Color blockColor = weaponGraphics.findColorFromWeaponType(gridVal.weaponType);
		
		switch(gridVal.listIndex) {
			case -1:
				blockColor = Color.orange;
				break;
			
			case 0:
				blockColor = Color.pink;
				break;
	
			case 1:
				blockColor = Color.blue;
				break;
				
			case 2:
				blockColor = Color.yellow;
				break;
				
			case 3:
				blockColor = Color.red;
				break;
				
			default:
				break;
		}
		gridValues.setValue(p, new GridValue(gridVal.listIndex + 1, gridVal.weaponType));
	}
	
	public void paintGrid(Graphics g) {
		//printGrid();
		Graphics2D g2d=(Graphics2D) g;
		
		g2d.setStroke(new BasicStroke(espLinha,
                BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER,
                miterLimit));
		
		g2d.setFont(font);
		
		for(int i=0;i<15;i++) {
			for(int j=0;j<15;j++) {
				GridValue gridVal = gridValues.getValue(new Position(i, j));
				
				double leftCornerX = skipX*(j+1)+(iniX-skipX);
				double leftCornerY = skipY*(i)+iniY;
				
				if(gridVal.listIndex > -1) {
					g.setColor(weaponGraphics.findColorFromWeaponType(gridVal.weaponType));
					g.fillRect((int)leftCornerX, (int)leftCornerY, 30, 30);
				}
				else {
					g.setColor(Color.white);
					g.fillRect((int)leftCornerX, (int)leftCornerY, 30, 30);
				}
			}
		}
		g2d.setColor(Color.black);
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
	
	private void printGrid() {
		System.out.printf("\n");
		for(int i=0;i<15;i++) {
			for(int j=0;j<15;j++) {
				GridValue gridVal = gridValues.getValue(new Position(i, j));
				
//				System.out.printf(" " + (new Integer(gridVal.listIndex)).toString() + " " + gridVal.weaponType);
				
			}
//			System.out.printf("\n");
		}
		
	}
}
