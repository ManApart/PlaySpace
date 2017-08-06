package rak.playspace.ui.toolbox;

import javafx.scene.image.Image;
import rak.playspace.model.Tile;
import rak.playspace.power.Station;
import rak.playspace.ui.GridViewHelper;
import rak.playspace.ui.PowerGridViewHelper;

public class StationTool implements Tool {
	private PowerGridViewHelper gridViewHelper;
	private Image image;
	
	public StationTool(GridViewHelper gridViewHelper){
		this.gridViewHelper = (PowerGridViewHelper) gridViewHelper;
		this.image = new Image("rak/playspace/images/tiles/stationOn.png");
	}
	
	@Override
	public void use(int x, int y) {
		Tile tile = gridViewHelper.getTileAt(x, y);
		tile.setItem(new Station());
		gridViewHelper.needsInitialized();
	}
	
	@Override
	public String getTitle() {
		return "Station";
	}

	@Override
	public Image getImage() {
		return image;
	}

}
