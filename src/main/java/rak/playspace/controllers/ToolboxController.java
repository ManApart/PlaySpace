package rak.playspace.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import rak.playspace.ui.GridViewHelper;
import rak.playspace.ui.toolbox.BlockedTool;
import rak.playspace.ui.toolbox.EmptyTool;
import rak.playspace.ui.toolbox.EngineTool;
import rak.playspace.ui.toolbox.FullTool;
import rak.playspace.ui.toolbox.StationTool;
import rak.playspace.ui.toolbox.WireTool;

public class ToolboxController {
	@FXML private Label currentTool;
	@FXML private FlowPane flowPane;
	private GridViewHelper gridViewHelper;
	
	@FXML private void selectToolBlocked(){
		gridViewHelper.setCurrentTool(new BlockedTool(gridViewHelper));
		currentTool.setText("Blocked");
	}
	
	@FXML private void selectToolEmpty(){
		gridViewHelper.setCurrentTool(new EmptyTool(gridViewHelper));
		currentTool.setText("Empty");
	}
	
	@FXML private void selectToolFull(){
		gridViewHelper.setCurrentTool(new FullTool(gridViewHelper));
		currentTool.setText("Full");
	}
	
	@FXML private void selectToolWire(){
		gridViewHelper.setCurrentTool(new WireTool(gridViewHelper));
		currentTool.setText("Wire");
	}
	
	@FXML private void selectToolEngine(){
		gridViewHelper.setCurrentTool(new EngineTool(gridViewHelper));
		currentTool.setText("Engine");
	}
	
	@FXML private void selectToolStation(){
		gridViewHelper.setCurrentTool(new StationTool(gridViewHelper));
		currentTool.setText("Station");
	}
	
	protected void setGridViewHelper(GridViewHelper gridViewHelper){
		this.gridViewHelper = gridViewHelper;
		gridViewHelper.createToolBoxButtons(flowPane, currentTool);
		
	}

}
