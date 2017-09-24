package rak.playspace.power;

import java.util.ArrayList;

import org.apache.commons.collections4.CollectionUtils;

import rak.playspace.flow.FlowManager;
import rak.playspace.model.Tile;
import rak.utility.grid.Grid;
import rak.utility.grid.GridPath;
import rak.utility.grid.GridSquare;

public class PowerManager implements FlowManager{
	public static final int MAX_POWER = 100;
	private Grid<PoweredComponent> grid;
	private Grid<Tile> tiles;
	private ArrayList<GridSquare<PoweredComponent>> stations;
	private ArrayList<GridSquare<PoweredComponent>> engines;
	private boolean isInitialized;

	public PowerManager(Grid<Tile> tiles){
		this.tiles = tiles;
		grid = createWireGrid();
	}
	
	@Override
	public void step() {
		initializeIfNeeded();
		consumeStationPower();
		produceEnginePower();
		drawPower();
	}
	
	private void initializeIfNeeded() {
		if (isInitialized){
			return;
		}
		
		grid = createWireGrid();
		findEnginesAndStations();
		
		isInitialized = true;
	}

	private Grid<PoweredComponent> createWireGrid() {
		Grid<PoweredComponent> grid = new Grid<PoweredComponent>(tiles.getSizeInOneDimension());
		for (GridSquare<Tile> square : tiles.getAllSquares()){
			Tile tile = square.getItem();
			if (hasPoweredItem(tile)){
				grid.setAt((PoweredComponent) tile.getItem(), square.getX(), square.getY());
			}
		}
		grid.refreshMaps();
		return grid;
	}

	private boolean hasPoweredItem(Tile tile) {
		return tile.getItem() != null && tile.getItem() instanceof PoweredComponent;
	}

	private void findEnginesAndStations() {
		stations = new ArrayList<>();
		engines = new ArrayList<>();
		
		for (GridSquare<Tile> square : tiles.getAllSquares()){
			Tile tile = square.getItem();
			
			if (hasStation(tile)){
				GridSquare<PoweredComponent> stationSquare = grid.getSquareAt(square.getX(), square.getY());
				stations.add(stationSquare);
			} else if (hasEngine(tile)){
				GridSquare<PoweredComponent> engineSquare = grid.getSquareAt(square.getX(), square.getY());
				engines.add(engineSquare);
			}
		}
	}
	
	private boolean hasStation(Tile tile) {
		return tile.getItem() instanceof Station;
	}

	private boolean hasEngine(Tile tile) {
		return tile.getItem() instanceof Engine;
	}
	
	private void consumeStationPower() {
		for (GridSquare<PoweredComponent> square : stations){
			((Station) square.getItem()).consumePower();
		}
	}
	
	private void produceEnginePower() {
		for (GridSquare<PoweredComponent> square : engines){
			((Engine) square.getItem()).producePower();
		}
	}

	private void drawPower() {
		for (GridSquare<PoweredComponent> station : stations){
			drawPower(station);
		}
	}
	
	private void drawPower(GridSquare<PoweredComponent> square) {
		Station station = (Station) square.getItem();
		
		if (station.getRequestedPower() > 0){
			GridPath<PoweredComponent> path = square.getPath();
			
			for (int i=1; i <= path.getMaxDistance(); i++){
				ArrayList<GridSquare<PoweredComponent>> previousSquares = path.getSquaresAt(i-1);
				ArrayList<GridSquare<PoweredComponent>> currentSquares = path.getSquaresAt(i);
				
				for (GridSquare<PoweredComponent> previousSquare : previousSquares){
//					if (previousSquare.getX() == 2 && previousSquare.getY() == 2){
//						System.out.println("square");
//					}
					ArrayList<PoweredComponent> neighborComponents = getNeighborComponents(currentSquares, previousSquare);
					drawPowerFromNeighbors(previousSquare, neighborComponents);
				}
			}
		}
	}
	
	private ArrayList<PoweredComponent> getNeighborComponents(ArrayList<GridSquare<PoweredComponent>> currentComponents, GridSquare<PoweredComponent> previousComponent) {
		ArrayList<GridSquare<PoweredComponent>> squares = (ArrayList<GridSquare<PoweredComponent>>) CollectionUtils.intersection(currentComponents, grid.findAllSurroundingSquares(previousComponent, 1));
		ArrayList<PoweredComponent> components = GridSquare.getAllItems(squares);
		//Sort by total power, resistance, etc?
		return components;
	}

	private void drawPowerFromNeighbors(GridSquare<PoweredComponent> previousSquare, ArrayList<PoweredComponent> contiguousComponents) {
		PoweredComponent previousComponent = previousSquare.getItem();
		for (PoweredComponent contiguousComponent : contiguousComponents){
			
			previousComponent.takePowerFrom(contiguousComponent);
			if (previousComponent.getRequestedPower() == 0){
				break;
			}
		}
	}

	@Override
	public int getLevel(GridSquare<Tile> square) {
		PoweredComponent item = grid.getItemAt(square.getX(), square.getY());
		if (item != null){
			return item.getPowerLevel();
		}
		return 0;
	}

	public void needsInitialized() {
		this.isInitialized = false;
	}

}
