package rak.playspace.tileSetup.creationOperations;

import rak.playspace.model.Tile;
import rak.playspace.power.Engine;
import rak.playspace.power.PowerManager;

public class EngineCreation implements TileCreationOperation{
	@Override
	public void create(Tile tile) {
		tile.setItem(new Engine(PowerManager.MAX_POWER));
	}
}