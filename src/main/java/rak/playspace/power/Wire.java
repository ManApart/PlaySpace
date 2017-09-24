package rak.playspace.power;

import rak.utility.grid.PathingType;

public class Wire extends PoweredComponent implements Item{
	private boolean isOpen = true;
	private float conductivity = 1;
	
	public Wire(int capacity){
		setCapacity(capacity);
	}
	
	public void setOpen(boolean open){
		this.isOpen = open;
	}
	
	@Override
	public PathingType getPathingType() {
		return isOpen ? PathingType.OPEN : PathingType.BLOCKED;
	}

	public float getConductivity() {
		return conductivity;
	}

	public void setConductivity(float conductivity) {
		this.conductivity = conductivity;
	}

}
