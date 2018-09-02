package rak.playspace.ui.tilePainter;

import rak.playspace.ui.tilePainter.ItemShape;
import rak.utility.grid.GridDirection;

public class ItemDimensions {
	private ItemShape shape = ItemShape.ALL_EDGE;
	private GridDirection rotation = GridDirection.NORTH;
	private ConnectedNeighbors neighbors;
	
	
	public ItemDimensions(ConnectedNeighbors neighbors){
		this.neighbors = neighbors;
		this.shape = ItemShape.determineShape(neighbors);
		this.rotation = getShape().determineRotation(neighbors);
	}
	
	public ConnectedNeighbors getNeighbors() {
		return neighbors;
	}
	
	public ItemShape getShape() {
		return shape;
	}

	public GridDirection getRotation() {
		return rotation;
	}

}
