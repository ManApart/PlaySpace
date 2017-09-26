package rak.playspace.radiation

import rak.playspace.model.Tile
import rak.utility.grid.Grid
import rak.utility.grid.GridHelper
import rak.utility.grid.GridSquare
import spock.lang.Specification

class RadiationSpec extends Specification{
	//Shortcut variables for easier reading
	private RadiationTestTileType r = RadiationTestTileType.RADIATOR;
	private RadiationTestTileType f = RadiationTestTileType.FLOOR;
	private RadiationTestTileType w = RadiationTestTileType.WALL;
	
	def "Radiator radiates evenly to each neighbor"(){
		given:
			RadiationTestTileType[][] tilePattern = [
				[f,f,f],
				[f,r,f], 				
				[f,f,f]
			]
			GridHelper helper = new GridHelper();
			Grid<Tile> grid = helper.createGrid(tilePattern)
			
		when:
			RadiationManager manager = new RadiationManager()
			manager.radiate(grid)
			
		then:
			int total = 0
			for (GridSquare<Tile> square : grid.getAllSquares()){
				int level = square.getItem().getRadiator().getRadiationLevel(RadiationType.AIR)
				System.println "tile x:" + square.getX() + ", y:" + square.getY() + ", l:" + level
				total += level
			} 
			System.println "total: " + total
			
		
			for (int[] coordinate : coordinates){
				Tile tile = grid.getItemAt(coordinate[0], coordinate[1])
				Radiator radiator = tile.getRadiator()
				int level = radiator.getRadiationLevel(RadiationType.AIR)
				 
				assert level == 20
			}
		where: 
			coordinates 	|_
			[[0,1],[1,0],[1,1],[1,2],[2,1]]	|_
	}

}
