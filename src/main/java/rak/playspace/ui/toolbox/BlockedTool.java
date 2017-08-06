package rak.playspace.ui.toolbox;

import javafx.scene.image.Image;
import rak.playspace.model.Tile;
import rak.playspace.ui.GridViewHelper;

public class BlockedTool implements Tool {
	private GridViewHelper gridViewHelper;
	private Image image;
	
	public BlockedTool(GridViewHelper gridViewHelper){
		this.gridViewHelper = gridViewHelper;
		this.image = new Image("rak/playspace/images/tiles/blocked.png");
	}
	
	@Override
	public void use(int x, int y) {
		Tile tile = gridViewHelper.getTileAt(x, y);
		tile.getAtmosphere().setValidForPathing(false);
	}

	@Override
	public String getTitle() {
		return "Blocked";
	}

	@Override
	public Image getImage() {
		return image;
	}


}
