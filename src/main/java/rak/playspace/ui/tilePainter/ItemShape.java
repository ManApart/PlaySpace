package rak.playspace.ui.tilePainter;

public enum ItemShape {
	NO_EDGE(4,true,false),
	ONE_EDGE(3,true,false),
	TWO_EDGE(2,false,false),
	CORNER(2,false,true),
	THREE_EDGE(1,true,false),
	ALL_EDGE(0,true,false);
	
	private int contiguousSides;
	private boolean ignoresCorners;
	private boolean isCorner;
	
	private ItemShape(int contiguousSides, boolean ignoresCorners, boolean isCorner){
		this.contiguousSides = contiguousSides;
		this.ignoresCorners = ignoresCorners;
		this.isCorner = isCorner;
	}
	
	public static ItemShape determineShape(int contiguousSides, boolean isCorner) {
		for (ItemShape itemShape : ItemShape.values()){
			if (itemShape.contiguousSides == contiguousSides){
				if (itemShape.ignoresCorners || itemShape.isCorner == isCorner){
					return itemShape;
				}
			}
		}
		return ItemShape.ALL_EDGE;
	}
	
}
