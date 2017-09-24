package rak.playspace.power;

import rak.utility.grid.PathingType;

public class Station extends PoweredComponent implements Item {
	
	/*
	 * For now just set power to 0, later change this based on station needs
	 */
	public void consumePower(){
		setPowerLevel(0);
	}

	@Override
	public PathingType getPathingType() {
		return PathingType.DESTINATION;
	}
	
}
