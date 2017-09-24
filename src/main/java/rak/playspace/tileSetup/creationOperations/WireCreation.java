package rak.playspace.tileSetup.creationOperations;

import rak.playspace.model.Tile;
import rak.playspace.power.Wire;

public class WireCreation implements TileCreationOperation{
	@Override
	public void create(Tile tile) {
		tile.setItem(new Wire(50));
	}
}