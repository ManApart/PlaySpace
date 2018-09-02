package rak.playspace.tilePainter;

import rak.playspace.power.Item;

public class TestTileItem implements Item {

	@Override
	public boolean visuallyConnectsWith(Item other) {
		return other != null;
	}

}
