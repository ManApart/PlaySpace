package rak.playspace.ui.toolbox;

import javafx.scene.image.Image;
import rak.playspace.model.Tile;
import rak.playspace.power.Engine;
import rak.playspace.power.PowerManager;
import rak.playspace.ui.GridViewHelper;
import rak.playspace.ui.PowerGridViewHelper;

public class EngineTool implements Tool {
	private PowerGridViewHelper gridViewHelper;
	private Image image;
	
	public EngineTool(GridViewHelper gridViewHelper){
		this.gridViewHelper = (PowerGridViewHelper) gridViewHelper;
		this.image = new Image("rak/playspace/images/tiles/engine.png");
	}
	
	@Override
	public void use(int x, int y) {
		Tile tile = gridViewHelper.getTileAt(x, y);
		tile.setItem(new Engine(PowerManager.MAX_POWER));
		gridViewHelper.needsInitialized();
	}
	
	@Override
	public String getTitle() {
		return "Engine";
	}

	@Override
	public Image getImage() {
		return image;
	}

}
