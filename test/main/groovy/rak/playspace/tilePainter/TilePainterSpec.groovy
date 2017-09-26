package rak.playspace.tilePainter

import rak.playspace.model.Tile
import rak.playspace.ui.tilePainter.ItemShape
import rak.playspace.ui.tilePainter.TilePainter
import rak.utility.grid.Grid
import rak.utility.grid.GridHelper
import spock.lang.Specification

class TilePainterSpec extends Specification {

	//Shortcut variables for easier reading
	private static final TestTileType x = TestTileType.ITEM;
	private static final TestTileType o = TestTileType.NULL;
	
	private static final TestTileType[][] NO_EDGE = 	[[x, x, x], 
														[x, x, x], 
														[x, x, x]];

	private static final TestTileType[][] ONE_EDGE = 	[[o, o, o], 
	                                                	 [x, x, x], 
	                                                	 [x, x, x]];
	
	private static final TestTileType[][] TWO_EDGE = 	[[o, o, o], 
	                                                 	 [x, x, x], 
	                                                 	 [o, o, o]];
	
	private static final TestTileType[][] CORNER = 		[[o, o, o], 
	                                                 	 [x, x, o], 
	                                                 	 [x, x, o]];
	
	private static final TestTileType[][] THREE_EDGE = 	[[o, o, o], 
	                                                 	 [x, x, o], 
	                                                 	 [o, o, o]];
	
	private static final TestTileType[][] ALL_EDGE = 	[[o, o, o], 
	                                                 	 [o, x, o], 
	                                                 	 [o, o, o]];
	

	def "test"(ItemShape expected, TestTileType[][] tilePattern){
		given:
			GridHelper helper = new GridHelper();
			Grid<Tile> grid = helper.createGrid(tilePattern)

		when:
			TilePainter manager = new TilePainter()
			ItemShape actual = manager.getShape(grid.getItemAt(1, 1), grid)

		then:
			expected == actual
			
		where:
			expected 				| 	tilePattern
			ItemShape.NO_EDGE		|	NO_EDGE
			ItemShape.ONE_EDGE		|	ONE_EDGE
			ItemShape.TWO_EDGE		|	TWO_EDGE
			ItemShape.CORNER		|	CORNER
			ItemShape.THREE_EDGE	|	THREE_EDGE
			ItemShape.ALL_EDGE		|	ALL_EDGE
	}
}
