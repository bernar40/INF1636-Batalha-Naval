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
	
	public int get_angle() {
		return angle;
	}
	
	public void rotate() {
		if (type == WeaponType.DESTROYER) {
			if (angle == 0) {
				weaponPositions.add(new Position(0,0));
				weaponPositions.add(new Position(1,0));
				angle++;
			}
			if (angle == 1) {
				weaponPositions.add(new Position(0,0));
				weaponPositions.add(new Position(0,1));
				angle = 0;
			}
		}
		else if (type == WeaponType.COURACADO) {
			if (angle == 0) {
				weaponPositions.add(new Position(0,0));
				weaponPositions.add(new Position(1,0));
				weaponPositions.add(new Position(2,0));
				weaponPositions.add(new Position(3,0));
				weaponPositions.add(new Position(4,0));
				angle++;
			}
			if (angle == 1) {
				weaponPositions.add(new Position(0,0));
				weaponPositions.add(new Position(0,1));
				weaponPositions.add(new Position(0,2));
				weaponPositions.add(new Position(0,3));
				weaponPositions.add(new Position(0,4));
				angle = 0;
			}
		}
		else if (type == WeaponType.CRUZADOR) {
			if (angle == 0) {
				weaponPositions.add(new Position(0,0));
				weaponPositions.add(new Position(1,0));
				weaponPositions.add(new Position(2,0));
				weaponPositions.add(new Position(3,0));
				angle++;
			}
			if (angle == 1) {
				weaponPositions.add(new Position(0,0));
				weaponPositions.add(new Position(0,1));
				weaponPositions.add(new Position(0,2));
				weaponPositions.add(new Position(0,3));
				angle = 0;
			}
		}
		else if (type == WeaponType.HIDROAVIAO) {
			if (angle == 0) {
				weaponPositions.add(new Position(1,0));
				weaponPositions.add(new Position(0,1));
				weaponPositions.add(new Position(2,1));
				angle++;
			}
			if (angle == 1) {
				weaponPositions.add(new Position(0,1));
				weaponPositions.add(new Position(1,0));
				weaponPositions.add(new Position(1,2));
				angle = 0;
			}
		}
	}
}