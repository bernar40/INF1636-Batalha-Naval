package utils;
import java.awt.*;

public class Grid {
	private GridValue[][] gridValues;
	
	public Grid(int xSize, int ySize, int initIndexVal) 
	{
		gridValues = new GridValue[xSize][ySize];
		for(int i = 0; i < xSize; i++) 
		{
			for (int j = 0; j < ySize; j++) 
			{
				gridValues[i][j] = new GridValue(initIndexVal, null);
			}
		}
	}
	
	public GridValue getValue(Position p) 
	{
		return gridValues[p.getX()][p.getY()];
	}
	
	public void setValue(Position p, GridValue val) 
	{
		gridValues[p.getX()][p.getY()] = val;
	}
	
	public Boolean isOutsideOfGrid(Position p) 
	{
		try {
			getValue(p);
		}
		catch(Exception e){
			return true;
		}
		
		return false;	
	}
}
