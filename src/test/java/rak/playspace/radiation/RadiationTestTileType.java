package rak.playspace.radiation;

import rak.playspace.model.Tile;
import rak.utility.grid.GridItemBuilder;

public enum RadiationTestTileType implements GridItemBuilder<Tile> {
	RADIATOR {
		public Radiation createRadiation(RadiationType radType, int radiatorProductionLevel) {
			Radiation radiation = new Radiation(radType);
			radiation.setRadiationProduced(radiatorProductionLevel);
			return radiation;
		}
	},
	FLOOR {
		public Radiation createRadiation(RadiationType radType, int radiatorProductionLevel) {
			Radiation radiation = new Radiation(radType);
			return radiation;
		}
	},
	WALL {
		public Radiation createRadiation(RadiationType radType, int radiatorProductionLevel) {
			Radiation radiation = new Radiation(radType);
			radiation.setRadiationBlocked(true);
			return radiation;
		}

	};
	
	private static RadiationType radType = RadiationType.AIR;
	private static int radiatorProductionLevel = 100;

	public abstract Radiation createRadiation(RadiationType radType, int radiatorProductionLevel);
	
	@Override
	public Tile buildSquare() {
		Tile tile = new Tile();

		Radiator radiator = createRadiator();
		tile.setRadiator(radiator);
		
		return tile;
	}

	private Radiator createRadiator() {
		Radiator radiator = new Radiator();
		Radiation radiation = createRadiation(radType, radiatorProductionLevel);
		radiator.setRadiation(radType, radiation);
		return radiator;
	}
	
}
