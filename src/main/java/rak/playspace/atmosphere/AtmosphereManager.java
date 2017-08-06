package rak.playspace.atmosphere;

import java.util.ArrayList;

import rak.playspace.flow.FlowManager;
import rak.playspace.model.Tile;
import rak.utility.grid.Grid;
import rak.utility.grid.GridPath;
import rak.utility.grid.GridSquare;

public class AtmosphereManager implements FlowManager{
	public static final int MAX_LEVEL = 100;
	
	private AtmosphereGrid grid;
	private ArrayList<Atmosphere> closedList = new ArrayList<>();
	private ArrayList<GridPath<Atmosphere>> lowPressureSquares;
	private int step = 0;
	
	public AtmosphereManager(Grid<Tile> tiles){
		grid = new AtmosphereGrid(tiles);
	}
	
	@Override
	public void step(){
		if (step == 0){
			initializeFlow();
		}
		for (GridPath<Atmosphere> path : lowPressureSquares){
			step(path);
		}
		step --;
	}

	@Override
	public int getLevel(GridSquare<Tile> square) {
		return square.getItem().getAtmosphere().getLevel();
	}

	private void initializeFlow() {
		closedList.clear();
		lowPressureSquares = grid.getLeastFilledSquares();
		step = getMaxDistance();
	}


	private int getMaxDistance() {
		int maxDistance = 0;
		for (GridPath<Atmosphere> path : lowPressureSquares){
			maxDistance = Math.max(maxDistance, path.getMaxDistance());
		}
		return maxDistance;
	}

	private void step(GridPath<Atmosphere> path) {
		ArrayList<GridSquare<Atmosphere>> nearTiles = path.getSquaresAt(step);
		for (GridSquare<Atmosphere> square : nearTiles){
			equalizePressure(square);
		}
	}

	private void equalizePressure(GridSquare<Atmosphere> square) {
		ArrayList<Atmosphere> neighbors = grid.getNeighbors(square);
		filterOutTilesThatHaveAlreadyFlowed(neighbors);
		//Add source to the list
		if (!neighbors.contains(square.getItem())){
			neighbors.add(square.getItem());
		}
		
		//average the level amongst the rest
		int total = 0;
		for (Atmosphere atmosphere : neighbors){
			total += atmosphere.getLevel();
		}
		
		int average = total/neighbors.size();
		for (Atmosphere atmosphere : neighbors){
			atmosphere.setLevel(average);
		}
		
		//Don't forget about the remainder
		int remainder = total % neighbors.size();
		square.getItem().setLevel(average + remainder);
		
	}


	private void filterOutTilesThatHaveAlreadyFlowed(ArrayList<Atmosphere> neighbors) {
		neighbors.removeAll(closedList);
	}


}
