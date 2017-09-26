package rak.playspace.ui.tilePainter;

import rak.playspace.model.Tile;
import rak.playspace.power.Item;
import rak.utility.grid.Grid;
import rak.utility.grid.GridDirection;

public class TilePainter {
	
	public ItemShape getShape(Tile tile, Grid<Tile> grid){
		if (tile == null || tile.getItem() == null){
			return ItemShape.ALL_EDGE;
		}
		
		int matchingSideCount = countMatchingSides(tile, grid);
		boolean isCorner = isCorner(tile, grid, matchingSideCount);
		return ItemShape.determineShape(matchingSideCount, isCorner);
	}

	private int countMatchingSides(Tile tile, Grid<Tile> grid) {
		int matchingSideCount = 0;
		for (GridDirection direction : GridDirection.values()){
			if (sideMatches(tile, grid, direction)){
				matchingSideCount++;
			}
		}
		return matchingSideCount;
	}

	private boolean sideMatches(Tile tile, Grid<Tile> grid, GridDirection direction) {
		Item neighborItem = getNeighborItem(tile, grid, direction);
		return tile.getItem().visuallyConnectsWith(neighborItem);
	}

	private Item getNeighborItem(Tile tile, Grid<Tile> grid, GridDirection gridDirection) {
		Tile neighborTile = grid.getNeighbor(tile, gridDirection);
		return neighborTile != null ? neighborTile.getItem() : null;
	}
	
	private boolean isCorner(Tile tile, Grid<Tile> grid, int matchingSideCount) {
		if (matchingSideCount == 2){
			boolean north = sideMatches(tile, grid, GridDirection.NORTH);
			boolean south = sideMatches(tile, grid, GridDirection.SOUTH);
			return (north != south);
		}	
		return false;
	}
	
}
