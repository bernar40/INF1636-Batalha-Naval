package utils;

public class Grid {
	private int xSize, ySize;
	private GridValue[][] gridValues;
	
	public Grid(int xSize, int ySize, int initIndexVal) 
	{
		this.xSize = xSize;
		this.ySize = ySize;
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
		if (p.getX() < 0 || p.getX() >= xSize)
			return new GridValue (-1, null);
		if (p.getY() < 0 || p.getY() >= ySize)
			return new GridValue (-1, null);
		
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
	
	public String toString() 
	{
		String matrix = "";
		for(int i = 0; i < xSize; i++) 
		{
			for (int j = 0; j < ySize; j++) 
			{
				try {
					matrix += gridValues[i][j].listIndex + "," + gridValues[i][j].weaponType.toString() + ";";
				}
				catch (Exception e){
					matrix += "-1,EMPTY;";
				}
				
			}
			matrix += "\n";
		}
		
		return matrix;
	}
}
