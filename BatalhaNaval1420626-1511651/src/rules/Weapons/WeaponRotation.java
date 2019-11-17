package rules.Weapons;

public enum WeaponRotation {
	ZERO,
	NINETY,
	ONEEIGHTY,
	TWOSEVENTY;
	
	public WeaponRotation nextRotation() {
		switch(this) {
			case ZERO:
				return WeaponRotation.NINETY;
				
			case NINETY:
				return WeaponRotation.ONEEIGHTY;
				
			case ONEEIGHTY:
				return WeaponRotation.TWOSEVENTY;
				
			case TWOSEVENTY:
				return WeaponRotation.ZERO;
						
			default:
				return WeaponRotation.ZERO;			
		}
	}
	
	public WeaponRotation initialize() {
		return WeaponRotation.ZERO;
	}
}
