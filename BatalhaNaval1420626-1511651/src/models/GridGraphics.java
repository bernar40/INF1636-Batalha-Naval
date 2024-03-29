package models;
import java.awt.geom.Line2D;
import rules.Weapons.WeaponType;
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
	
	public void paintGrid(Graphics g, Boolean hideUncoveredWeapons) {
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
					
					WeaponType wpType = gridVal.weaponType;
					Color boxColor = weaponGraphics.findColorFromWeaponType(wpType);
					
					if(hideUncoveredWeapons) {
						if(wpType != WeaponType.MISSED && wpType != WeaponType.COMPLETELYHIT && wpType != WeaponType.PARTIALLYHIT) {
							boxColor = Color.white;
						}
					}
										
					g.setColor(boxColor);
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
	
	public void cleanGrid()
	{
		for(int i=0;i<15;i++)
			for(int j=0;j<15;j++)
				gridValues.setValue(new Position(i, j), new GridValue(-1, null));
	}
	
//	private void printGrid() {
//		System.out.printf("\n");
//		for(int i=0;i<15;i++) {
//			for(int j=0;j<15;j++) {
//				GridValue gridVal = gridValues.getValue(new Position(i, j));
//				
////				System.out.printf(" " + (new Integer(gridVal.listIndex)).toString() + " " + gridVal.weaponType);
//				
//			}
////			System.out.printf("\n");
//		}
//		
//	}
}
