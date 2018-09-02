package rak.playspace.atmosphere;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import rak.playspace.model.Tile;
import rak.utility.grid.Grid;
import rak.utility.grid.GridPath;
import rak.utility.grid.GridSquare;
import rak.utility.grid.PathingType;

public class AtmosphereGrid {
	private Grid<Atmosphere> atmosphereGrid;
	private HashMap<Integer, ArrayList<GridSquare<Atmosphere>>> levelMap = new HashMap<>();
	
	public AtmosphereGrid(Grid<Tile> tiles) {
		createAtmosphereGrid(tiles);
		refreshLevelMap();
	}

	private void createAtmosphereGrid(Grid<Tile> tiles) {
		atmosphereGrid = new Grid<>(tiles.getSizeInOneDimension());
		for (GridSquare<Tile> square : tiles.getAllSquares()){
			atmosphereGrid.setAt(square.getItem().getAtmosphere(), square.getX(), square.getY());
		}
		atmosphereGrid.refreshMaps();
		
	}

	//TODO - need to think about when this is called so we don't get out of sync
	private void refreshLevelMap() {
		levelMap.clear();
		for (GridSquare<Atmosphere> square : atmosphereGrid.getAllSquares()){
			storeSquareLevel(square);
		}
	}

	private void storeSquareLevel(GridSquare<Atmosphere> square) {
		if (square.getItem() != null && square.getItem().getPathingType() == PathingType.OPEN){
			int level = square.getItem().getLevel();
			if (!levelMap.containsKey(level)){
				levelMap.put(level, new ArrayList<>());
			}
			levelMap.get(level).add(square);
		}
	}

	public ArrayList<GridPath<Atmosphere>> getLeastFilledSquares() {
		ArrayList<GridPath<Atmosphere>> paths = new ArrayList<>();
		refreshLevelMap();
		int min = getMinimumLevel();
		int max = getMaximumLevel();
		if (min != max){
			ArrayList<GridSquare<Atmosphere>> squares = levelMap.get(min);
			paths.addAll(GridSquare.getAllPaths(squares));
		}
		
		return paths;
	}
	
	private int getMinimumLevel(){
		ArrayList<Integer> levels = getLevels();
		if (levels.size() > 0){
			return getLevels().get(0);
		}
		return 0;
	}
	
	private int getMaximumLevel(){
		ArrayList<Integer> levels = getLevels();
		if (levels.size() > 0){
			return levels.get(levels.size()-1);
		}
		return 0;
	}
	
	private ArrayList<Integer> getLevels() {
		ArrayList<Integer> levels = new ArrayList<Integer>(levelMap.keySet());
		Collections.sort(levels);
		return levels;
	}
	
	public ArrayList<Atmosphere> getNeighbors(GridSquare<Atmosphere> source){
		return atmosphereGrid.findAllSurroundingItemsThatAreValidForPathing(source, 1);
	}

}
