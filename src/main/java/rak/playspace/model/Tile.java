package rak.playspace.model;

import rak.playspace.atmosphere.Atmosphere;
import rak.playspace.power.Item;
import rak.playspace.power.Wire;
import rak.playspace.radiation.Radiator;
import rak.utility.grid.GridItem;
import rak.utility.grid.PathingType;

public class Tile implements GridItem{
	private Radiator radiator;
	private Atmosphere atmosphere;
	private Item item;
	private Wire wire;

	public Radiator getRadiator() {
		return radiator;
	}

	public void setRadiator(Radiator radiator) {
		this.radiator = radiator;
	}

	public Atmosphere getAtmosphere() {
		return atmosphere;
	}

	public void setAtmosphere(Atmosphere atmosphere) {
		this.atmosphere = atmosphere;
	}

	@Override
	public PathingType getPathingType() {
		return PathingType.OPEN;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Wire getWire() {
		return wire;
	}

	public void setWire(Wire wire) {
		this.wire = wire;
	}
	

}
