package rak.playspace.model;

import rak.playspace.radiation.RadiationManager;
import rak.utility.grid.Grid;

public class PlaySpace {
	private Grid<Tile> tiles;
	private RadiationManager radiationManager = new RadiationManager();
	
	public void setTiles(Grid<Tile> tiles){
		this.tiles = tiles;
	}

	public Tile getTile(int x, int y){
		if (tiles != null){
			return tiles.getItemAt(x, y);
		}
		return null;
	}
	
	public int getSize(){
		if (tiles != null){
			return tiles.getSizeInOneDimension();
		}
		return 0;
	}
	
	public void radiate(){
		radiationManager.radiate(tiles);
	}
}
