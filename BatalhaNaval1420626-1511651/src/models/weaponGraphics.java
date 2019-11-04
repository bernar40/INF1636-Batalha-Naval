package models;

import java.util.*;
import rules.Weapons.*;
import utils.*;

public class weaponGraphics{

	private IWeapon 	weapon;
	private Position 	startingDrawPosition;
	private int 		drawBlockSize;
	private String		color;
	
	public weaponGraphics(WeaponType weaponType, Position upperLeftCorner, int blockSize) 
	{
		switch(weaponType) 
		{
			case COURACADO:
				weapon = new Couracado();
				break;
				
			case CRUZADOR:
				weapon = new Cruzador();
				break;
				
			case DESTROYER:
				weapon = new Destroyer();
				break;
				
			case HIDROAVIAO:
				weapon = new HidroAviao();
				break;
				
			case SUBMARINO:
				weapon = new Submarino();
				break;
				
			default:
				break;
		}
		
		startingDrawPosition = upperLeftCorner;	
		drawBlockSize = blockSize;		
	}
	
	public Boolean wasItClicked (Position mousePos)
	{
		ArrayList<Position> weaponsShape = weapon.placeWeaponInGrid(new Position(0,0));
		
		for(Position p: weaponsShape) 
		{
			Position adjustedBlockPosition = Position.add(Position.scalar(p, drawBlockSize), startingDrawPosition);
			
			if(mousePos.insideSquare(adjustedBlockPosition, drawBlockSize))
				return true;
		}
		
		return false;
	}
	
	public void paint() 
	{
		ArrayList<Position> weaponsShape = weapon.placeWeaponInGrid(new Position(0,0));
		
		for(Position p: weaponsShape) 
		{
			Position adjustedBlockPosition = Position.add(Position.scalar(p, drawBlockSize), startingDrawPosition);
			
			//draw square of color starting at adjustedBlockPosition with length drawBlockSize
		}
	}
	
	
	
}
