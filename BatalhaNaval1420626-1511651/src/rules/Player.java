package rules;
import java.util.*;
import rules.Weapons.*;
import utils.*;

public class Player {
	private int aliveWeapons;
	private String playerName;
	private Grid ownWeaponsView;
	private Grid enemyWeaponsView;
	private ArrayList<IWeapon>  ownWeapons;
	private ArrayList<IWeapon> 	enemyWeapons;
	private int currentWeaponQty;
	
	public Player(String name) {
		playerName 			= name;
		aliveWeapons 		= 15;
		currentWeaponQty 	= 0;
		
		ownWeaponsView = new Grid(-1, 15, 15);
		enemyWeaponsView = new Grid(-1, 15, 15);
		
		ownWeapons 		= new ArrayList<IWeapon> ();
		enemyWeapons 	= new ArrayList<IWeapon> ();
		
		
	}
	
	public String getName() {
		return playerName;
	}
	
	public Grid getOwnGrid() {
		return ownWeaponsView;
	}
	
	public Grid getEnemyGrid() {
		return enemyWeaponsView;
	}
	
	public Boolean setWeaponInGrid(IWeapon weapon, Position upperLeftCorner){
		
		ArrayList<Position> weaponBlocks = weapon.placeWeaponInGrid(upperLeftCorner);
		
		for(Position p: weaponBlocks) 
		{
			if(ownWeaponsView.getValue(p) != -1) {
				// there is already a weapon there!
				return false;
			}
		}
		
		//No overlap found, so gridValues can be set now!	
		for(Position p: weaponBlocks) 
		{
			ownWeaponsView.setValue(p, currentWeaponQty);
		}
		
		ownWeapons.add(weapon);
		currentWeaponQty++;
			
		return true;
	}
	
	public WeaponType removeWeaponFromGrid(Position pos) 
	{
		int weaponIndex = ownWeaponsView.getValue(pos);
		
		if(weaponIndex != -1) 
		{
			cleanFromOwnGrid(weaponIndex);
			
			IWeapon removedWeapon = ownWeapons.remove(weaponIndex);
			
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
				if(ownWeaponsView.getValue(new Position(i,j)) == val) 
					ownWeaponsView.setValue(new Position(i, j), -1);
			}
			
		}
	}
}
