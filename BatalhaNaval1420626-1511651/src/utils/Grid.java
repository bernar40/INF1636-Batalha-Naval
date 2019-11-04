package utils;

public class Grid {
	private int[][] gridValues;
	
	public Grid(int initVal, int xSize, int ySize) 
	{
		for(int i = 0; i < xSize; i++) 
		{
			for (int j = 0; j < ySize; j++) 
			{
				gridValues[i][j] = initVal;
			}
		}
	}
	
	public int getValue(Position p) 
	{
		return gridValues[p.getX()][p.getY()];
	}
	
	public void setValue(Position p, int val) 
	{
		gridValues[p.getX()][p.getY()] = val;
	}
}
