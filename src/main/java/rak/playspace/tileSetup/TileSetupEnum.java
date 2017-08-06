package rak.playspace.tileSetup;

import rak.playspace.model.Tile;
import rak.playspace.tileSetup.creationOperations.BlockedCreation;
import rak.playspace.tileSetup.creationOperations.EmptyCreation;
import rak.playspace.tileSetup.creationOperations.EngineCreation;
import rak.playspace.tileSetup.creationOperations.FullCreation;
import rak.playspace.tileSetup.creationOperations.StationCreation;
import rak.playspace.tileSetup.creationOperations.TileCreationOperation;
import rak.playspace.tileSetup.creationOperations.WireCreation;

public enum TileSetupEnum {
	ENGINE(new EngineCreation()),
	WIRE(new WireCreation()),
	STATION(new StationCreation()),
	EMPTY(new EmptyCreation()),
	BLOCKED(new BlockedCreation()),
	FULL(new FullCreation());
	
	private TileCreationOperation operation;
	
	private TileSetupEnum(TileCreationOperation operation){
		this.operation = operation;
	}

	public void createTile(Tile tile){
		operation.create(tile);
	}
}
