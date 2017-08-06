package rak.playspace.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import rak.playspace.ui.AtmosphereGridViewHelper;
import rak.playspace.ui.CSS;
import rak.playspace.ui.PowerGridViewHelper;

public class PlayspaceTestController {
	
	@FXML GridPane powerView;
	@FXML GridPane atmosphereView;
	@FXML FlowController atmosphereViewController;
	@FXML FlowController powerViewController;
	
	public void initialize(){
		atmosphereViewController.getGridPane().getStyleClass().add(CSS.TILE_GRID);
		atmosphereViewController.setGridViewHelper(new AtmosphereGridViewHelper(atmosphereViewController.getGridPane()));
		
		powerViewController.getGridPane().getStyleClass().add(CSS.TILE_GRID);
		powerViewController.setGridViewHelper(new PowerGridViewHelper(powerViewController.getGridPane()));
		
		atmosphereViewController.generate();
		powerViewController.generate();
	}
	

}
