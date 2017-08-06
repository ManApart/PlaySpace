package rak.playspace.ui;

import java.util.ArrayList;

import rak.playspace.flow.FlowManager;
import rak.playspace.model.Tile;
import rak.utility.grid.Grid;
import rak.utility.grid.GridSquare;

public class GridHistory {
	private Grid<Tile> grid;
	private int currentStep = 0;
	private ArrayList<int[][]> history;
	private FlowManager flowManager; 
	
	public GridHistory(Grid<Tile> grid, FlowManager flowManager){
		this.grid = grid;
		this.flowManager = flowManager;
		history = new ArrayList<>();
		addHistoryStep(grid);
	}

	public int[][] getCurrentView(){
		return history.get(currentStep);
	}
	
	public void next(){
		currentStep++;
		if (currentStep >= getNumberOfSteps()){
			flowManager.step();
			addHistoryStep(grid);
		}
	}
	
	private int getNumberOfSteps(){
		return history.size();
	}
	
	private void addHistoryStep(Grid<Tile> grid){
		int[][] stepValues = getValues(grid);
		history.add(stepValues);
	}

	private int[][] getValues(Grid<Tile> grid) {
		int size = grid.getSizeInOneDimension();
		int[][] stepValues = new int[size][size];
		for (GridSquare<Tile> square : grid.getAllSquares()){
			int level = flowManager.getLevel(square);
			stepValues[square.getX()][square.getY()] = level;
		}
		return stepValues;
	}

	public void previous() {
		if (currentStep > 0){
			currentStep--;
		}
	}
	
	public Tile getTile(int x, int y){
		return grid.getItemAt(x, y);
	}
	
	protected Grid<Tile> getGrid(){
		return grid;
	}
	
}
