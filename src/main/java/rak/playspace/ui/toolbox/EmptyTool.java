package rak.playspace.ui.toolbox;

import javafx.scene.image.Image;
import rak.playspace.model.Tile;
import rak.playspace.ui.GridViewHelper;

public class EmptyTool implements Tool {
	private GridViewHelper gridViewHelper;
	private Image image;
	
	public EmptyTool(GridViewHelper gridViewHelper){
		this.gridViewHelper = gridViewHelper;
		this.image = new Image("rak/playspace/images/tiles/empty.png");
	}

	@Override
	public void use(int x, int y) {
		Tile tile = gridViewHelper.getTileAt(x, y);
		if (tile.getAtmosphere() != null){
			tile.getAtmosphere().setValidForPathing(true);
			tile.getAtmosphere().setLevel(0);
		}
		tile.setWire(null);
		tile.setItem(null);
	}
	
	@Override
	public String getTitle() {
		return "Empty";
	}

	@Override
	public Image getImage() {
		return image;
	}

}
