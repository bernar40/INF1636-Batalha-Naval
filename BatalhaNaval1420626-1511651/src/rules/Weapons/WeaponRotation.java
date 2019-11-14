package rules.Weapons;

public enum WeaponRotation {
	ZERO,
	NINETY,
	ONEEIGHTY,
	TWOSEVENTY;
	
	public WeaponRotation nextRotation() {
		switch(this) {
			case ZERO:
				return this.NINETY;
				
			case NINETY:
				return this.ONEEIGHTY;
				
			case ONEEIGHTY:
				return this.TWOSEVENTY;
				
			case TWOSEVENTY:
				return this.ZERO;
						
			default:
				return this.ZERO;			
		}
	}
	
	public WeaponRotation initialize() {
		return this.ZERO;
	}
}
