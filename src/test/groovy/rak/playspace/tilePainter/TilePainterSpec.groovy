package rak.playspace.tilePainter

import rak.playspace.model.Tile
import rak.playspace.ui.tilePainter.ItemShape
import rak.playspace.ui.tilePainter.TilePainter
import rak.utility.grid.Grid
import rak.utility.grid.GridDirection
import rak.utility.grid.GridHelper
import spock.lang.Specification

class TilePainterSpec extends Specification {

	def "proper shape of #expected is identified given tile pattern #tilePattern"(ItemShape expected, TestTileType[][] tilePattern){
		given:
			GridHelper helper = new GridHelper()
			Grid<Tile> grid = helper.createGrid(tilePattern)

		when:
			TilePainter manager = new TilePainter()
			ItemShape actual = manager.getDimensions(grid.getItemAt(1, 1), grid).getShape()

		then:
			expected == actual
			
		where:
			expected 				| 	tilePattern
			ItemShape.NO_EDGE		|	TestTileType.NO_EDGE
			ItemShape.ONE_EDGE		|	TestTileType.ONE_EDGE
			ItemShape.ONE_EDGE		|	TestTileType.ONE_EDGE_EAST
			ItemShape.ONE_EDGE		|	TestTileType.ONE_EDGE_SOUTH
			ItemShape.ONE_EDGE		|	TestTileType.ONE_EDGE_WEST
			ItemShape.TWO_EDGE		|	TestTileType.TWO_EDGE
			ItemShape.TWO_EDGE		|	TestTileType.TWO_EDGE_WEST
			ItemShape.CORNER		|	TestTileType.CORNER
			ItemShape.CORNER		|	TestTileType.CORNER_EAST
			ItemShape.CORNER		|	TestTileType.CORNER_SOUTH
			ItemShape.CORNER		|	TestTileType.CORNER_WEST
			ItemShape.THREE_EDGE	|	TestTileType.THREE_EDGE
			ItemShape.THREE_EDGE	|	TestTileType.THREE_EDGE_EAST
			ItemShape.THREE_EDGE	|	TestTileType.THREE_EDGE_SOUTH
			ItemShape.THREE_EDGE	|	TestTileType.THREE_EDGE_WEST
			ItemShape.ALL_EDGE		|	TestTileType.ALL_EDGE
	}
	
	def "proper rotation of #expected is identified given tile pattern #tilePattern"(GridDirection expected, TestTileType[][] tilePattern){
		given:
			GridHelper helper = new GridHelper()
			Grid<Tile> grid = helper.createGrid(tilePattern)
			
		when:
			TilePainter manager = new TilePainter()
			GridDirection actual = manager.getDimensions(grid.getItemAt(1, 1), grid).getRotation()
			
		then:
			expected == actual
			
			where:
				expected 				| 	tilePattern
				GridDirection.NORTH		|	TestTileType.NO_EDGE
				GridDirection.NORTH		|	TestTileType.ONE_EDGE
				GridDirection.EAST		|	TestTileType.ONE_EDGE_EAST
				GridDirection.SOUTH		|	TestTileType.ONE_EDGE_SOUTH
				GridDirection.WEST		|	TestTileType.ONE_EDGE_WEST
				GridDirection.NORTH		|	TestTileType.TWO_EDGE
				GridDirection.WEST		|	TestTileType.TWO_EDGE_WEST
				GridDirection.NORTH		|	TestTileType.CORNER
				GridDirection.EAST		|	TestTileType.CORNER_EAST
				GridDirection.SOUTH		|	TestTileType.CORNER_SOUTH
				GridDirection.WEST		|	TestTileType.CORNER_WEST
				GridDirection.NORTH		|	TestTileType.THREE_EDGE
				GridDirection.EAST		|	TestTileType.THREE_EDGE_EAST
				GridDirection.SOUTH		|	TestTileType.THREE_EDGE_SOUTH
				GridDirection.WEST		|	TestTileType.THREE_EDGE_WEST
				GridDirection.NORTH		|	TestTileType.ALL_EDGE
	}
}
