package rak.playspace.tilePainter;

import rak.playspace.model.Tile;
import rak.playspace.power.Item;
import rak.utility.grid.GridItem;
import rak.utility.grid.GridItemBuilder;

public enum TestTileType implements GridItemBuilder {
	ITEM {
		@Override
		public Item getITem() {
			return new TestTileItem();
		}
	},
	NULL {
		@Override
		public Item getITem() {
			return null;
		}
	};

	public abstract Item getITem();
	
	@Override
	public GridItem buildSquare() {
		Tile tile = new Tile();

		tile.setItem(getITem());
		
		return tile;
	}

}
