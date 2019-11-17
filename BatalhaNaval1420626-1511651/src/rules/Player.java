package rules;
import java.awt.*;
import java.util.*;

import models.weaponGraphics;
import rules.Weapons.*;
import utils.*;

public class Player {
	private int aliveWeapons;
	private String playerName;
	private Grid ownWeaponsView;
	private Grid enemyWeaponsView;
	private ArrayList<Weapon>  ownWeapons;
	private ArrayList<Weapon> 	enemyWeapons;
	private int currentWeaponQty;
	
	public Player() {
		aliveWeapons 		= 15;
		currentWeaponQty 	= 0;
		
		ownWeaponsView 		= new Grid(15, 15, -1);
		enemyWeaponsView 	= new Grid(15, 15, -1);
		
		ownWeapons 		= new ArrayList<Weapon> ();
		enemyWeapons 	= new ArrayList<Weapon> ();
		
		
	}
	
	public String getName() {
		return playerName;
	}
	
	public void setName(String name) {
		playerName = name;
	}
	
	public Grid getOwnGrid() {
		return ownWeaponsView;
	}
	
	public Grid getEnemyGrid() {
		return enemyWeaponsView;
	}
	
	public Boolean checkIfNoWeaponsLeft() {
		return aliveWeapons == 0;
	}
	
	public Boolean isGridFull() {
		return currentWeaponQty == 15;
	}
	
	public int checkForWeaponInPosition(Position p)
	{
		return ownWeaponsView.getValue(p).listIndex;
	}
	
	public Boolean checkForCompletelyDestroyedWeaponInOwnGrid(int weaponIndx) 
	{
		
		for(int i = 0; i < 15; i++) 
		{
			for (int j = 0; j < 15; j++) 
			{
				GridValue gridAtIndx = ownWeaponsView.getValue(new Position(i,j));
				
				if(gridAtIndx.listIndex == weaponIndx) 
				{
					// At least one is still not hit
					if(gridAtIndx.weaponType != WeaponType.PARTIALLYHIT) 
					{
						return false;
					}
				}
			}			
		}
		
		//If here, its completely destroyed, so we should update the values	
		for(int i = 0; i < 15; i++) 
		{
			for (int j = 0; j < 15; j++) 
			{
				GridValue gridAtIndx = ownWeaponsView.getValue(new Position(i,j));
				
				if(gridAtIndx.listIndex == weaponIndx) 
				{
					ownWeaponsView.setValue(new Position(i,j), new GridValue(weaponIndx, WeaponType.COMPLETELYHIT));
				}
			}			
		}
		
		aliveWeapons--;
		
		System.out.println("Weapons left:" + aliveWeapons);
		
		return true;
	}
	
	public Boolean checkForCompletelyDestroyedWeaponInEnemyGrid(int weaponIndx) 
	{
		
		for(int i = 0; i < 15; i++) 
		{
			for (int j = 0; j < 15; j++) 
			{
				GridValue gridAtIndx = enemyWeaponsView.getValue(new Position(i,j));
				
				if(gridAtIndx.listIndex == weaponIndx) 
				{
					// At least one is still not hit
					if(gridAtIndx.weaponType != WeaponType.PARTIALLYHIT) 
					{
						return false;
					}
				}
			}			
		}
		
		//If here, its completely destroyed, so we should update the values	
		for(int i = 0; i < 15; i++) 
		{
			for (int j = 0; j < 15; j++) 
			{
				GridValue gridAtIndx = enemyWeaponsView.getValue(new Position(i,j));
				
				if(gridAtIndx.listIndex == weaponIndx) 
				{
					enemyWeaponsView.setValue(new Position(i,j), new GridValue(weaponIndx, WeaponType.COMPLETELYHIT));
				}
			}			
		}
		
		return true;
	}

	public Boolean setWeaponInGrid(Weapon weapon, Position upperLeftCorner){
		
		ArrayList<Position> weaponBlocks = weapon.placeWeaponInGrid(upperLeftCorner);
		
		for(Position p: weaponBlocks) 
		{
			if(ownWeaponsView.isOutsideOfGrid(p) ||  ownWeaponsView.getValue(p).listIndex != -1) {
				// there is already a weapon there or cant place it
				return false;
			}
		}
		
		//No overlap found, so gridValues can be set now!	
		for(Position p: weaponBlocks) 
		{
			ownWeaponsView.setValue(p, new GridValue(currentWeaponQty, weapon.type));
		}
		
		ownWeapons.add(weapon);
		currentWeaponQty++;
			
		return true;
	}
	
	public Boolean setHitValueInOwnGrid(int weaponIndx, Position p)
	{
		
		ownWeaponsView.setValue(p, new GridValue(weaponIndx, WeaponType.PARTIALLYHIT));
			
		return true;
	}
	
	public Boolean setMissedValueInOwnGrid(int weaponIndx, Position p)
	{
		
		ownWeaponsView.setValue(p, new GridValue(weaponIndx, WeaponType.MISSED));
			
		return true;
	}
	
	
	public Boolean setHitValueInEnemyGrid(int weaponIndx, Position p)
	{
		
		enemyWeaponsView.setValue(p, new GridValue(weaponIndx, WeaponType.PARTIALLYHIT));
			
		return true;
	}
	
	public Boolean setMissedValueInEnemyGrid(int weaponIndx, Position p)
	{
		
		enemyWeaponsView.setValue(p, new GridValue(weaponIndx, WeaponType.MISSED));
			
		return true;
	}
	
	public Boolean hasAlreadyBeenShotInGrid(Position p)
	{	
		return  ownWeaponsView.getValue(p).weaponType == WeaponType.MISSED 			||
				ownWeaponsView.getValue(p).weaponType == WeaponType.PARTIALLYHIT 	||
				ownWeaponsView.getValue(p).weaponType == WeaponType.COMPLETELYHIT;
	}
	
	public Boolean completelyHitWeaponInEnemyGrid(int weaponIndx){
		
		for(int i = 0; i < 15; i++) 
		{
			for (int j = 0; j < 15; j++) 
			{
				// Empty space and subtract 1 from bigger indxs accounting for the shift from the remove
				GridValue gridAtIndx = ownWeaponsView.getValue(new Position(i,j));
				
				if(gridAtIndx.listIndex == weaponIndx)
				{
					ownWeaponsView.setValue(new Position(i, j), new GridValue(-1, WeaponType.COMPLETELYHIT));
				}				
			}			
		}
			
		return true;
	}
	
	public WeaponType removeWeaponFromGrid(Position pos) 
	{
		GridValue gridValue = ownWeaponsView.getValue(pos);
		
		if(gridValue.listIndex != -1) 
		{
			cleanFromOwnGrid(gridValue.listIndex);
			
			Weapon removedWeapon = ownWeapons.remove(gridValue.listIndex);
			
			return removedWeapon.type;
		}
		
		return null;
	}
	
	private void cleanFromOwnGrid(int val) 
	{
		for(int i = 0; i < 15; i++) 
		{
			for (int j = 0; j < 15; j++) 
			{
				// Empty space and subtract 1 from bigger indxs accounting for the shift from the remove
				GridValue gridAtIndx = ownWeaponsView.getValue(new Position(i,j));
				
				if(gridAtIndx.listIndex == val)
				{
					ownWeaponsView.setValue(new Position(i, j), new GridValue(-1, null));
				}
				else if(gridAtIndx.listIndex > val) 
				{
					ownWeaponsView.setValue(new Position(i, j), new GridValue(gridAtIndx.listIndex - 1, gridAtIndx.weaponType));
				}
					
			}
			
		}
		currentWeaponQty--;
	}
}
