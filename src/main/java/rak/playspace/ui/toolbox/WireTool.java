package rak.playspace.ui.toolbox;

import javafx.scene.image.Image;
import rak.playspace.model.Tile;
import rak.playspace.power.Wire;
import rak.playspace.ui.GridViewHelper;
import rak.playspace.ui.PowerGridViewHelper;

public class WireTool implements Tool {
	private PowerGridViewHelper gridViewHelper;
	private Image image;
	
	public WireTool(GridViewHelper gridViewHelper){
		this.gridViewHelper = (PowerGridViewHelper) gridViewHelper;
		this.image = new Image("rak/playspace/images/tiles/wireOn.png");
	}
	
	@Override
	public void use(int x, int y) {
		Tile tile = gridViewHelper.getTileAt(x, y);
		tile.setItem(new Wire(50));
		gridViewHelper.needsInitialized();
	}
	
	@Override
	public String getTitle() {
		return "Wire";
	}
	
	@Override
	public Image getImage() {
		return image;
	}
}
