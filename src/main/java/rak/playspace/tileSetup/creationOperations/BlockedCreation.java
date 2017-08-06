package rak.playspace.tileSetup.creationOperations;

import rak.playspace.model.Tile;

public class BlockedCreation implements TileCreationOperation{
	@Override
	public void create(Tile tile) {
		tile.getAtmosphere().setLevel(0);
		tile.getAtmosphere().setValidForPathing(false);
	}
}