package rak.playspace.flow;

import rak.playspace.model.Tile;
import rak.utility.grid.GridSquare;

public interface FlowManager {
	
	public void step();
	
	public int getLevel(GridSquare<Tile> square);

}
