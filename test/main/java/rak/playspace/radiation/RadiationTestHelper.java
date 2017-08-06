package rak.playspace.radiation;

import rak.playspace.model.Tile;
import rak.utility.grid.Grid;

public class RadiationTestHelper {
	private RadiationType radType = RadiationType.AIR;
	private int radiatorProductionLevel = 100;
	
	public void setRadiationType(RadiationType type){
		this.radType = type;
	}
	
	public void setRadiatorProductionLevel(int level){
		this.radiatorProductionLevel = level;
	}
	
	public Grid<Tile> createTiles(RadiationTestTileType[][] tilePattern){
		Grid<Tile> tiles = new Grid<>(tilePattern.length);
		
		for (int x=0; x<tilePattern.length; x++){
			for (int y=0; y<tilePattern[x].length; y++){
				Tile tile = createTile(tilePattern[x][y]);
				tiles.setAt(tile, x, y);
			}
		}
		
		return tiles;
	}

	private Tile createTile(RadiationTestTileType radiationTestTileType) {
		Tile tile = new Tile();

		Radiator radiator = createRadiator(radiationTestTileType);
		tile.setRadiator(radiator);
		
		return tile;
	}

	private Radiator createRadiator(RadiationTestTileType radiationTestTileType) {
		Radiator radiator = new Radiator();
		Radiation radiation = createRadiation(radiationTestTileType);
		radiator.setRadiation(radType, radiation);
		return radiator;
	}

	//TODO - seems like a smell for a pattern
	private Radiation createRadiation(RadiationTestTileType radiationTestTileType) {
		if (radiationTestTileType == RadiationTestTileType.RADIATOR){
			return createRadiatorRadiation();
		} else 	if (radiationTestTileType == RadiationTestTileType.FLOOR){
			return createFloorRadiation();
		} else 	if (radiationTestTileType == RadiationTestTileType.WALL){
			return createWallRadiation();
		}
		
		System.out.println("Didn't find type");
		return null;
	}

	private Radiation createRadiatorRadiation() {
		Radiation radiation = new Radiation(radType);
		radiation.setRadiationProduced(radiatorProductionLevel);
		return radiation;
	}
	
	private Radiation createFloorRadiation() {
		Radiation radiation = new Radiation(radType);
		return radiation;
	}
	
	private Radiation createWallRadiation() {
		Radiation radiation = new Radiation(radType);
		radiation.setRadiationBlocked(true);
		return radiation;
	}

}
