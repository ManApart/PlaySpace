package rak.playspace.controllers;

import org.apache.commons.lang3.StringUtils;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import rak.playspace.ui.GridViewHelper;

public class FlowController {
	
	@FXML private TextField userEnteredSize;
	@FXML private Label currentSize;
	@FXML private GridPane gridPane;
	@FXML private ToolboxController toolboxViewController;
	
	private GridViewHelper gridViewHelper;
	
	
	@FXML
	protected void generate(){
		String size = userEnteredSize.getText();
		if (!StringUtils.isNumeric(size)){
			System.out.println("User entered size must be numbers only");
			currentSize.setText("ERROR");
		} else {
			int sizeValue = Integer.parseInt(size);
			gridViewHelper.generateNewGrid(sizeValue);
			currentSize.setText("" + gridViewHelper.getGridSize());
			redraw();
		}
	}
	
	@FXML
	protected void resetFlowLevels(){
		gridViewHelper.resetFlowLevels();
		redraw();
	}
	
	@FXML
	private void next(){
		gridViewHelper.next();
		redraw();
	}
	
	@FXML
	private void previous(){
		gridViewHelper.previous();
		redraw();
	}
	
	private void redraw(){
		gridViewHelper.draw();
	}
	
	protected void setGridViewHelper(GridViewHelper gridViewHelper){
		this.gridViewHelper = gridViewHelper;
		toolboxViewController.setGridViewHelper(gridViewHelper);
	}
	
	protected GridPane getGridPane(){
		return gridPane;
	}
	
}
