package rak.playspace.radiation;

import java.util.ArrayList;

import rak.playspace.model.Tile;
import rak.utility.grid.Grid;
import rak.utility.grid.GridSquare;

public class RadiationManager {
	private Grid<Tile> tiles;
	
	public void radiate(Grid<Tile> tiles) {
		this.tiles = tiles;
		
		produceRadiation();
		
		radiate();
	}

	private void produceRadiation() {
		for (Tile tile : tiles.getAllItems()){
			Radiator radiator = tile.getRadiator();
			radiator.produceRadiation();
		}
	}
	
	private void radiate() {
		for (GridSquare<Tile> tileSquare : tiles.getAllSquares()){
			radiate(tileSquare);
		}
	}

	private void radiate(GridSquare<Tile> tileSquare) {
		Radiator radiator = tileSquare.getItem().getRadiator();
		ArrayList<Radiator> neighbors = getNeighbors(tileSquare);
		
		radiator.radiate(neighbors);
	}

	private ArrayList<Radiator> getNeighbors(GridSquare<Tile> tileSquare) {
		ArrayList<Tile> neighbors = tiles.findAllSurroundingItems(tileSquare, 1);
		
		ArrayList<Radiator> radiators = new ArrayList<>();
		for (Tile tile : neighbors){
			radiators.add(tile.getRadiator());
		}
		
		return radiators;
	}
	
	

}
