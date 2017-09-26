package rak.playspace.power;

import rak.utility.grid.PathingType;

public class Engine extends PoweredComponent implements Item {
	
	private int powerProduced;
	
	public Engine(int powerProduced){
		this.powerProduced = powerProduced;
	}

	@Override
	public PathingType getPathingType() {
		return PathingType.DESTINATION;
	}
	
	@Override
	public boolean visuallyConnectsWith(Item north) {
		return north instanceof Engine;
	}
	
	public void producePower(){
		setPowerLevel(getPowerLevel() + powerProduced);
	}

}
