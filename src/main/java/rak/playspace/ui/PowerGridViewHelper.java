package rak.playspace.ui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import rak.playspace.flow.FlowManager;
import rak.playspace.model.Tile;
import rak.playspace.power.Engine;
import rak.playspace.power.Item;
import rak.playspace.power.PowerManager;
import rak.playspace.power.PoweredComponent;
import rak.playspace.power.Station;
import rak.playspace.tileSetup.TileSetupHelper;
import rak.playspace.ui.toolbox.EmptyTool;
import rak.playspace.ui.toolbox.EngineTool;
import rak.playspace.ui.toolbox.StationTool;
import rak.playspace.ui.toolbox.WireTool;
import rak.utility.grid.Grid;

public class PowerGridViewHelper extends GridViewHelper{

	public PowerGridViewHelper(GridPane gridPane) {
		super(gridPane);
	}
	
	@Override
	protected void populateGridTiles(Grid<Tile> grid) {
		TileSetupHelper tileSetuptHelper = new TileSetupHelper(grid);
		tileSetuptHelper.setDefaultTiles();
		tileSetuptHelper.setupPowerGrid5x5CrissCross();
	}

	@Override
	protected FlowManager createFlowManager(Grid<Tile> grid) {
		return new PowerManager(grid);
	}
	
	@Override
	public void resetFlowLevels() {
		for (Tile tile : getGrid().getAllItems()){
			resetPoweredItem(tile);
			resetWirePower(tile);
		}
		needsInitialized();
	}

	private void resetPoweredItem(Tile tile) {
		Item item = tile.getItem();
		if(item != null && item instanceof PoweredComponent){
			PoweredComponent poweredItem = (PoweredComponent) item;
			poweredItem.setPowerLevel(0);
		}
	}

	private void resetWirePower(Tile tile) {
		if (tile.getWire() != null){
			tile.getWire().setPowerLevel(0);
		}
	}

	@Override
	protected void createStylesForSquare(int x, int y, int level, Button button) {
		button.getStyleClass().removeAll();
		button.getStyleClass().add(CSS.BUTTON_TILE);
		
		Tile tile = getTileAt(x, y);
		if (tile.getItem() instanceof Engine){
			button.getStyleClass().add(CSS.BUTTON_ENGINE);
		} else if (tile.getItem() instanceof Station){
			if (level >= 50){
				button.getStyleClass().add(CSS.BUTTON_STATION_ON);
			} else {
				button.getStyleClass().add(CSS.BUTTON_STATION_OFF);
			}
		} else if (tile.getWire() != null){
			if (level >= 50){
				button.getStyleClass().add(CSS.BUTTON_WIRE_ON);
			} else {
				button.getStyleClass().add(CSS.BUTTON_WIRE_OFF);
			}
		} else {
			button.getStyleClass().add(CSS.BUTTON_EMPTY);
			button.setText("");
		}
		
	}

	@Override
	public void createToolBoxButtons(FlowPane flowPane, Label currentTool){
		flowPane.getChildren().clear();
		
		createdToolButton(flowPane, currentTool, new EmptyTool(this));
		createdToolButton(flowPane, currentTool, new EngineTool(this));
		createdToolButton(flowPane, currentTool, new WireTool(this));
		createdToolButton(flowPane, currentTool, new StationTool(this));
	}

	
	public void needsInitialized(){
		PowerManager powerManager = (PowerManager) flowManager;
		powerManager.needsInitialized();
		resetGridHistory();
	}
	
}
