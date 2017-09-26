package rak.playspace.ui.tilePainter;

import rak.playspace.model.Tile;
import rak.playspace.power.Item;
import rak.playspace.tilePainter.ConnectedNeighbors;
import rak.playspace.tilePainter.ItemDimensions;
import rak.utility.grid.Grid;
import rak.utility.grid.GridDirection;

public class TilePainter {

	public ItemDimensions getDimensions(Tile tile, Grid<Tile> grid){
		if (tile == null || tile.getItem() == null){
			return null;
		}
		ConnectedNeighbors neighbors = createNeighbors(tile, grid);
		
		return new ItemDimensions(neighbors);
	}

	public ConnectedNeighbors createNeighbors(Tile tile, Grid<Tile> grid) {
		ConnectedNeighbors neighbors = new ConnectedNeighbors();
		for (GridDirection direction : GridDirection.values()) {
			if (sideMatches(tile, grid, direction)) {
				neighbors.setConnected(direction, true);
			}
		}
		return neighbors;
	}

	private boolean sideMatches(Tile tile, Grid<Tile> grid, GridDirection direction) {
		Item neighborItem = getNeighborItem(tile, grid, direction);
		return tile.getItem().visuallyConnectsWith(neighborItem);
	}

	private Item getNeighborItem(Tile tile, Grid<Tile> grid, GridDirection gridDirection) {
		Tile neighborTile = grid.getNeighbor(tile, gridDirection);
		return neighborTile != null ? neighborTile.getItem() : null;
	}

}
