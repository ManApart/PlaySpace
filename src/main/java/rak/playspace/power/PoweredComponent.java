package rak.playspace.power;

import rak.utility.MathFunctions;
import rak.utility.grid.GridItem;

public abstract class PoweredComponent implements GridItem {
	private int powerLevel;
	private int desiredPowerLevel = 100;
	private int capacity = PowerManager.MAX_POWER;
	
	
	public void setDesiredPower(int desiredLevel){
		this.desiredPowerLevel = desiredLevel;
	}
	
	public int getRequestedPower(){
		return Math.min(capacity, desiredPowerLevel) - getPowerLevel();
	}

	public int getPowerLevel() {
		return powerLevel;
	}
	
	public void incPowerLevel(int amount){
		setPowerLevel(amount + getPowerLevel());
	}

	public void setPowerLevel(int powerLevel) {
		this.powerLevel = MathFunctions.clamp(powerLevel, 0, capacity);
	}

	public void takePowerFrom(PoweredComponent otherComponent) {
		int requestedPower = getRequestedPower();
		int availablePower = otherComponent.getPowerLevel();
		int actualPower = Math.min(requestedPower, availablePower);
		
		otherComponent.incPowerLevel(-actualPower);
		incPowerLevel(actualPower);
	}
	
	protected void setCapacity(int capacity){
		this.capacity = capacity;
	}
	
	

}
