package rak.playspace.tileSetup;

import rak.playspace.atmosphere.Atmosphere;
import rak.playspace.model.Tile;
import rak.utility.grid.Grid;
import rak.utility.grid.GridSquare;

public class TileSetupHelper {
	private Grid<Tile> grid;
	
	//Enum Shortcuts
	private static final TileSetupEnum E = TileSetupEnum.ENGINE;
	private static final TileSetupEnum W = TileSetupEnum.WIRE;
	private static final TileSetupEnum S = TileSetupEnum.STATION;
	private static final TileSetupEnum O = TileSetupEnum.EMPTY;
	private static final TileSetupEnum B = TileSetupEnum.BLOCKED;
	private static final TileSetupEnum F = TileSetupEnum.FULL;
	
	public TileSetupHelper(Grid<Tile> grid){
		this.grid = grid;
	}
	
	public void setupPowerGrid3x3(){
		TileSetupEnum[][] setupGrid = {
				{O,O,O},
				{E,W,S},
				{O,O,O}
		};
		createbyGrid(setupGrid);
	}
	
	public void setupPowerGrid3x3Uneven(){
		TileSetupEnum[][] setupGrid = {
				{W,W,W},
				{W,O,W},
				{E,W,S}
		};
		createbyGrid(setupGrid);
	}
	
	public void setupPowerGrid5x5(){
		TileSetupEnum[][] setupGrid = {
				{W,W,W,W,W},
				{W,O,O,O,W},
				{E,O,O,O,S},
				{W,O,O,O,W},
				{W,W,W,W,W}
		};
		createbyGrid(setupGrid);
	}
	
	public void setupPowerGrid5x5CrissCross(){
		TileSetupEnum[][] setupGrid = {
				{O,O,W,W,W},
				{O,O,W,O,W},
				{E,W,W,W,S},
				{W,O,O,O,O},
				{W,W,W,O,O}
		};
		createbyGrid(setupGrid);
	}
	
	public void setupPowerGrid7x7(){
		TileSetupEnum[][] setupGrid = {
				{W,W,W,W,W,W,W},
				{W,O,O,O,O,O,W},
				{E,O,W,W,W,O,W},
				{W,O,W,O,W,O,W},
				{W,W,W,W,W,W,S}
		};
		createbyGrid(setupGrid);
	}
	
	public void setupAtmosphereGrid3x3(){
		TileSetupEnum[][] setupGrid = {
				{F,O,O},
				{B,B,O},
				{O,O,O}
		};
		createbyGrid(setupGrid);
	}
	
	public void setDefaultTiles(){
		for (GridSquare<Tile> square : grid.getAllSquares()){
			Tile tile = new Tile();
			tile.setAtmosphere(new Atmosphere());
			tile.getAtmosphere().setValidForPathing(false);
			grid.setItem(square, tile);
		}
	}
	
	private void createbyGrid(TileSetupEnum[][] setupGrid){
		for (int x=0; x<setupGrid.length; x++){
			for (int y=0; y<setupGrid[x].length; y++){
				Tile tile = grid.getItemAt(x, y);
				if (tile != null){
					//invert coordinates to match the look of the array
					TileSetupEnum setup = setupGrid[y][x];
					if (setup == null){
						setup = TileSetupEnum.EMPTY;
					}
					setup.createTile(tile);
				}
			}
		}
	}
}
