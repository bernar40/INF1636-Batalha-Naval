package rules.Weapons;

import java.util.*;
import utils.*;

enum weaponState {
	SETUP,
	ALIVE,
	SINKED,
}

public class Weapon {
	public weaponState state = weaponState.SETUP;
	public WeaponType type = WeaponType.HIDROAVIAO; 
	
	int weaponHealth = 0;
	ArrayList<Position> weaponPositions = new ArrayList<Position>();
	
	public Weapon(WeaponType Wtype) {
		
		
		type = Wtype;
		switch(Wtype) {
			case COURACADO:
				weaponHealth = 5;
				
				weaponPositions.add(new Position(0,0));
				weaponPositions.add(new Position(0,1));
				weaponPositions.add(new Position(0,2));
				weaponPositions.add(new Position(0,3));
				weaponPositions.add(new Position(0,4));
				break;
				
			case CRUZADOR:
				weaponHealth = 4;
				
				weaponPositions.add(new Position(0,0));
				weaponPositions.add(new Position(0,1));
				weaponPositions.add(new Position(0,2));
				weaponPositions.add(new Position(0,3));
				break;
				
			case DESTROYER:
				weaponHealth = 2;
				
				weaponPositions.add(new Position(0,0));
				weaponPositions.add(new Position(0,1));
				break;
				
			case HIDROAVIAO:
				weaponHealth = 3;
				
				weaponPositions.add(new Position(0,1));
				weaponPositions.add(new Position(1,0));
				weaponPositions.add(new Position(1,2));
				break;
				
			case SUBMARINO:
				weaponHealth = 1;
				
				weaponPositions.add(new Position(0,0));
				break;
		}
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
	
	public WeaponType getType() {
		return type;
	}
}