package rak.playspace.tileSetup.creationOperations;

import rak.playspace.atmosphere.AtmosphereManager;
import rak.playspace.model.Tile;

public class FullCreation implements TileCreationOperation{
	@Override
	public void create(Tile tile) {
		tile.getAtmosphere().setLevel(AtmosphereManager.MAX_LEVEL);
		tile.getAtmosphere().setValidForPathing(true);
	}
}