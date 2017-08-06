package rak.playspace.atmosphere;

import rak.utility.MathFunctions;
import rak.utility.grid.GridItem;
import rak.utility.grid.PathingType;

public class Atmosphere implements GridItem, Comparable<Atmosphere>{
	
	private int level;
	private boolean isOpen;
	
	@Override
	public String toString(){
		return "Atmosphere isOpen:" + isOpen + " level:" + level;
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = MathFunctions.clamp(level, 0, AtmosphereManager.MAX_LEVEL);
	}


	public void setValidForPathing(boolean isValid) {
		this.isOpen = isValid;
	}

	@Override
	public int compareTo(Atmosphere o) {
		int compare = 1;
		
		if (o != null){
			if (getLevel() < o.getLevel()){
				compare = -compare;
			} else if (getLevel() == o.getLevel()){
				compare = 0;
			}
		}
		
		return compare;
	}

	@Override
	public PathingType getPathingType() {
		return isOpen ? PathingType.OPEN : PathingType.BLOCKED;
	}
}
