package models;

import java.awt.Color;
import java.awt.Graphics;
import java.util.*;
import rules.Weapons.*;
import utils.*;

public class weaponGraphics{

	private IWeapon 	weapon;
	private Position 	startingDrawPosition;
	private int 		drawBlockSize;
	private Color		color;
	
	public weaponGraphics(WeaponType weaponType, Position upperLeftCorner, int blockSize, Color color) 
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
		this.color = color;
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
	
	public void paint(Graphics g) 
	{
		ArrayList<Position> weaponsShape = weapon.placeWeaponInGrid(new Position(0,0));
		
		for(Position p: weaponsShape) 
		{
			Position adjustedBlockPosition = Position.add(Position.scalar(p, drawBlockSize), startingDrawPosition);			
			g.setColor(color);
			g.fillRect (adjustedBlockPosition.getX(), adjustedBlockPosition.getY(), drawBlockSize, drawBlockSize); 
		}
	}
	
	
	
}
