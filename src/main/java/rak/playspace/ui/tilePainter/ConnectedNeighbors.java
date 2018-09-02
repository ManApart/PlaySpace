package rak.playspace.ui.tilePainter;

import java.util.HashMap;
import java.util.Map.Entry;

import rak.utility.grid.GridDirection;

public class ConnectedNeighbors {
	
	private HashMap<GridDirection, Boolean> neighbors;
	
	public ConnectedNeighbors(){
		neighbors = buildMap();
	}
	
	@Override
	public String toString(){
		String message = "";
		for (Entry<GridDirection, Boolean> neighbor : neighbors.entrySet()){
			message += neighbor.getKey() + ": " + neighbor.getValue() + ", ";
		}
		
		return message;
	}

	private HashMap<GridDirection, Boolean> buildMap() {
		HashMap<GridDirection, Boolean> map = new HashMap<>();
		for (GridDirection direction : GridDirection.values()){
			map.put(direction, false);
		}
		return map;
	}
	
	public void setConnected(GridDirection direction, boolean connected){
		neighbors.put(direction, connected);
	}
	
	public boolean isConnected(GridDirection direction){
		return neighbors.get(direction);
	}
	
	public int getNumberOfConnectedNeighbors(){
		int count = 0;
		for (GridDirection direction : neighbors.keySet()){
			if (direction.isCardinal()){
				boolean isConnected = neighbors.get(direction);
				if (isConnected){
					count++;
				}
			}
		}
		return count;
	}
	
	public int getNumberOfConnectedNeighborsIncludingDiagonals(){
		int count = 0;
		for (boolean isConnected : neighbors.values()){
			if (isConnected){
				count++;
			}
		}
		return count;
	}
	
	public boolean isCorner() {
		return (isConnected(GridDirection.NORTH) != isConnected(GridDirection.SOUTH) 
				&& getNumberOfConnectedNeighbors() == 2);
	}

}
