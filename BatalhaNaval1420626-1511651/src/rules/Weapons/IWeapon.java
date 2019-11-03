package rules.Weapons;

import utils.*;

enum weaponState {
	SETUP,
	ALIVE,
	SINKED,
}

public interface IWeapon {
	public int indexX = 0;
	public int indexY = 0;
	public weaponState state = weaponState.SETUP;
	
	private ArrayList<Position> weaponPositions = new ArrayList<Position>();
}
