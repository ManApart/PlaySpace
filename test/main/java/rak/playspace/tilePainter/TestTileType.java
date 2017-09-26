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
	
	//Shortcut variables for easier reading
	public static final TestTileType x = TestTileType.ITEM;
	public static final TestTileType o = TestTileType.NULL;
	
	public static final TestTileType[][] NO_EDGE = 	{{x, x, x}, 
														{x, x, x}, 
														{x, x, x}};

	public static final TestTileType[][] ONE_EDGE = 	{{o, o, o}, 
												       	 {x, x, x}, 
												       	 {x, x, x}};

	public static final TestTileType[][] ONE_EDGE_EAST = 	{{x, x, o}, 
											        		 {x, x, o}, 
															 {x, x, o}};
	
	public static final TestTileType[][] ONE_EDGE_SOUTH = 	{{x, x, x}, 
											             	 {x, x, x}, 
											             	 {o, o, o}};
	
	public static final TestTileType[][] ONE_EDGE_WEST = 	{{o, x, x}, 
											             	 {o, x, x}, 
											             	 {o, x, x}};
	
	public static final TestTileType[][] TWO_EDGE = 	{{o, o, o}, 
											        	 {x, x, x}, 
											        	 {o, o, o}};
	
	public static final TestTileType[][] TWO_EDGE_WEST = 	{{o, x, o}, 
											        		 {o, x, o}, 
															 {o, x, o}};
	
	public static final TestTileType[][] CORNER = 		{{o, o, o}, 
											        	 {o, x, x}, 
											        	 {o, x, x}};
	
	public static final TestTileType[][] CORNER_EAST = {{o, o, o}, 
											            {x, x, o}, 
											            {x, x, o}};
	
	public static final TestTileType[][] CORNER_SOUTH = {{x, x, o}, 
											      		  {x, x, o}, 
														  {o, o, o}};
	
	public static final TestTileType[][] CORNER_WEST = {{o, x, x}, 
											            {o, x, x}, 
											            {o, o, o}};
	
	public static final TestTileType[][] THREE_EDGE = 	{{o, x, o}, 
											        	 {o, x, o}, 
											        	 {o, o, o}};
	
	public static final TestTileType[][] THREE_EDGE_EAST = 	{{o, o, o}, 
										  			       	 {o, x, x}, 
															{o, o, o}};
	
	public static final TestTileType[][] THREE_EDGE_SOUTH = {{o, o, o}, 
											    		      {o, x, o}, 
															  {o, x, o}};
	
	public static final TestTileType[][] THREE_EDGE_WEST = 	{{o, o, o}, 
										        	  		 {x, x, o}, 
															 {o, o, o}};
	
	public static final TestTileType[][] ALL_EDGE = 	{{o, o, o}, 
											        	 {o, x, o}, 
											        	 {o, o, o}};
}
