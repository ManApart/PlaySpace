package rak.playspace.ui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import rak.playspace.atmosphere.AtmosphereManager;
import rak.playspace.flow.FlowManager;
import rak.playspace.model.Tile;
import rak.playspace.tileSetup.TileSetupHelper;
import rak.playspace.ui.toolbox.BlockedTool;
import rak.playspace.ui.toolbox.EmptyTool;
import rak.playspace.ui.toolbox.FullTool;
import rak.utility.grid.Grid;
import rak.utility.grid.PathingType;

public class AtmosphereGridViewHelper extends GridViewHelper {

	public AtmosphereGridViewHelper(GridPane gridPane) {
		super(gridPane);
	}

	@Override
	protected FlowManager createFlowManager(Grid<Tile> grid) {
		return new AtmosphereManager(grid);
	}
	
	@Override
	protected void populateGridTiles(Grid<Tile> grid) {
		TileSetupHelper tileSetuptHelper = new TileSetupHelper(grid);
		tileSetuptHelper.setDefaultTiles();
		tileSetuptHelper.setupAtmosphereGrid3x3();
	}
	
	@Override
	public void resetFlowLevels() {
		for (Tile tile : getGrid().getAllItems()){
			if (tile.getAtmosphere() != null){
				tile.getAtmosphere().setLevel(0);
			}
		}
		resetGridHistory();
	}
	
	@Override
	protected void createStylesForSquare(int x, int y, int level, Button button) {
		button.getStyleClass().removeAll();
		button.getStyleClass().add(CSS.BUTTON_TILE);
		if (isBlocked(x,y)){
			button.getStyleClass().add(CSS.BUTTON_BLOCKED);
			button.setText("");
		} else if (level == 0){
			button.getStyleClass().add(CSS.BUTTON_EMPTY);
			button.setText("");
		} else{
			button.getStyleClass().add(CSS.BUTTON_FULL);
		}
	}

	private boolean isBlocked(int x, int y) {
		return getTileAt(x, y).getAtmosphere().getPathingType() == PathingType.BLOCKED;
	}

	@Override
	public void createToolBoxButtons(FlowPane flowPane, Label currentTool){
		flowPane.getChildren().clear();
		
		createdToolButton(flowPane, currentTool, new BlockedTool(this));
		createdToolButton(flowPane, currentTool, new EmptyTool(this));
		createdToolButton(flowPane, currentTool, new FullTool(this));
	}

}
