package rules;
import java.util.*;
import rules.Weapons.*;
import utils.*;

public class Player {
	private int aliveWeapons;
	private String playerName;
	private Grid ownWeaponsView;
	private Grid enemyWeaponsView;
	
	public Player(String name) {
		playerName 		= name;
		aliveWeapons 	= 15;
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
	
	public Boolean setWeaponInGrid(IWeapon weapon, Position upperLeftCorner, int weaponId) {
		
		ArrayList<Position> weaponBlocks = weapon.placeWeaponInGrid(upperLeftCorner);
		
		for(Position p: weaponBlocks) 
		{
			if(ownWeaponsView.getValue(p) != 0) {
				// there is already a weapon there!
				return false;
			}
		}
		
		//No overlap found, so gridValues can be set now!	
		for(Position p: weaponBlocks) 
		{
			ownWeaponsView.setValue(p, weaponId);
		}
		
		return true;
	}
}
