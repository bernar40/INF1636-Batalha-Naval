package rules.Weapons;

import java.util.*;
import utils.*;

enum weaponState {
	SETUP,
	ALIVE,
	SINKED,
}

public interface IWeapon {
	// public Position pos = new Position(-1,-1);
	public weaponState state = weaponState.SETUP;
	public WeaponType type = null; 
	
	int weaponHealth = 0;
	ArrayList<Position> weaponPositions = new ArrayList<Position>();
	
	public Boolean weaponWasHit();
	public ArrayList<Position> placeWeaponInGrid(Position leftCornerPosition);
}
