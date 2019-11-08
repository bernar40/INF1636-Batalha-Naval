package utils;
import rules.Weapons.*;

public class GridValue 
{
	public int 			listIndex;
	public WeaponType 	weaponType;
	
	public GridValue(int indx, WeaponType weapon) 
	{
		listIndex 	= indx;
		weaponType 	= weapon;
	}	
}
