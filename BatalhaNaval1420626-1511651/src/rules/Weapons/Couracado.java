package rules.Weapons;

import java.util.*;
import utils.Position;

public class Couracado implements IWeapon {
	// public Position pos = new Position(-1,-1);
	public weaponState state = weaponState.SETUP;
	public WeaponType type = WeaponType.COURACADO; 
	
	int weaponHealth = 0;
	ArrayList<Position> weaponPositions = new ArrayList<Position>();
	
	public Couracado() {
		weaponHealth = 5;
		
		weaponPositions.add(new Position(0,0));
		weaponPositions.add(new Position(1,0));
		weaponPositions.add(new Position(2,0));
		weaponPositions.add(new Position(3,0));
		weaponPositions.add(new Position(4,0));
	}
	
	public Boolean weaponWasHit() {
		weaponHealth--;
		
		if(weaponHealth == 0)
			return true;
		
		return false;
	}
	
	public ArrayList<Position> placeWeaponInGrid(Position leftCornerPosition){
		ArrayList<Position> returningPositions = new ArrayList<Position>();
		
		for(Position p : weaponPositions) {
			returningPositions.add(Position.add(p, leftCornerPosition));
		}
		
		return returningPositions;
	}
}
