package rak.playspace.ui.toolbox;

import javafx.scene.image.Image;
import rak.playspace.model.Tile;
import rak.playspace.ui.GridViewHelper;

public class FullTool implements Tool {
	private GridViewHelper gridViewHelper;
	private Image image;
	
	public FullTool(GridViewHelper gridViewHelper){
		this.gridViewHelper = gridViewHelper;
		this.image = new Image("rak/playspace/images/tiles/full.png");
	}
	
	@Override
	public void use(int x, int y) {
		Tile tile = gridViewHelper.getTileAt(x, y);
		tile.getAtmosphere().setValidForPathing(true);
		tile.getAtmosphere().setLevel(100);
	}
	
	@Override
	public String getTitle() {
		return "Full";
	}

	@Override
	public Image getImage() {
		return image;
	}

}
