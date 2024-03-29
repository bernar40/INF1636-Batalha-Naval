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
	public int angle = 0;
	
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
			default:
				break;
		}
	}
		
	public Weapon(WeaponType Wtype, WeaponRotation rotation) {
		
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
			default:
				break;
		}
		
		ArrayList<Position> rotatedWeaponPositions = new ArrayList<Position>();
		
		switch(rotation) {
			case ZERO:
				break;
				
			case NINETY:					
				for(Position p : weaponPositions ) 
				{
					rotatedWeaponPositions.add(new Position(-1 * p.getY(), -1 * p.getX()));
				}
				weaponPositions = rotatedWeaponPositions;
				break;
			
			case ONEEIGHTY:					
				for(Position p : weaponPositions ) 
				{
					rotatedWeaponPositions.add(new Position(-1 * p.getX(), -1* p.getY()));
				}
				weaponPositions = rotatedWeaponPositions;
				break;
				
			case TWOSEVENTY:					
				for(Position p : weaponPositions ) 
				{
					rotatedWeaponPositions.add(new Position(p.getY(), p.getX()));
				}
				weaponPositions = rotatedWeaponPositions;
				break;
			default:
				break;
		}
			
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