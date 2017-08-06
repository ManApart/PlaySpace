package rak.playspace.tileSetup.creationOperations;

import rak.playspace.model.Tile;
import rak.playspace.power.Station;

public class StationCreation implements TileCreationOperation{
	@Override
	public void create(Tile tile) {
		tile.setItem(new Station());
	}
}