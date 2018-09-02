package rak.playspace.ui.tilePainter;

import rak.utility.grid.GridDirection;

public enum ItemShape {
	NO_EDGE(4),
	ONE_EDGE(3){
		@Override
		public GridDirection determineRotation(ConnectedNeighbors neighbors){
			for (GridDirection direction : GridDirection.values()){
				if (direction.isCardinal() && !neighbors.isConnected(direction)){
					return direction;
				}
			}
			return super.determineRotation(neighbors);
		}
	},
	TWO_EDGE(2){
		@Override
		public GridDirection determineRotation(ConnectedNeighbors neighbors){
			if (neighbors.isConnected(GridDirection.NORTH)){ 
				return GridDirection.WEST; 
			}
			return GridDirection.NORTH;
		}
	},
	CORNER(2){
		@Override
		protected boolean isCorner(){
			return true;
		}
		@Override
		public GridDirection determineRotation(ConnectedNeighbors neighbors){
			if (neighbors.isConnected(GridDirection.WEST) && neighbors.isConnected(GridDirection.NORTH)) {
				return GridDirection.SOUTH;
			}
			if (neighbors.isConnected(GridDirection.NORTH) && neighbors.isConnected(GridDirection.EAST)) {
				return GridDirection.WEST;
			}
			if (neighbors.isConnected(GridDirection.EAST) && neighbors.isConnected(GridDirection.SOUTH)) {
				return GridDirection.NORTH;
			}
			if (neighbors.isConnected(GridDirection.SOUTH) && neighbors.isConnected(GridDirection.WEST)) {
				return GridDirection.EAST;
			}
			return super.determineRotation(neighbors);
		}
	},
	THREE_EDGE(1){
		@Override
		public GridDirection determineRotation(ConnectedNeighbors neighbors){
			for (GridDirection direction : GridDirection.values()){
				if (neighbors.isConnected(direction)){
					return direction.getInverse();
				}
			}
			return super.determineRotation(neighbors);
		}
	},
	ALL_EDGE(0);
	
	private int contiguousSides;
	
	private ItemShape(int contiguousSides){
		this.contiguousSides = contiguousSides;
	}
	
	protected boolean isCorner(){
		return false;
	}
	
	public GridDirection determineRotation(ConnectedNeighbors neighbors){
		return GridDirection.NORTH;
	}
	
	public static ItemShape determineShape(ConnectedNeighbors neighbors) {
		for (ItemShape itemShape : ItemShape.values()){
			if (itemShape.contiguousSides == neighbors.getNumberOfConnectedNeighbors()){
				if (itemShape.isCorner() == neighbors.isCorner()){
					return itemShape;
				}
			}
		}
		return ItemShape.ALL_EDGE;
	}
	
}
