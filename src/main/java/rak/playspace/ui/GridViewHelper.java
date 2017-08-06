package rak.playspace.ui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import rak.playspace.flow.FlowManager;
import rak.playspace.model.Tile;
import rak.playspace.tileSetup.TileSetupHelper;
import rak.playspace.ui.toolbox.EmptyTool;
import rak.playspace.ui.toolbox.Tool;
import rak.utility.grid.Grid;

public abstract class GridViewHelper {
	private GridPane gridPane;
	private GridHistory gridHistory;
	private static final int DEFAULT_SIZE = 5;
	private Tool currentTool = new EmptyTool(this);
	protected FlowManager flowManager;
	
	public GridViewHelper(GridPane gridPane){
		this.gridPane = gridPane;
	}
	
	protected abstract FlowManager createFlowManager(Grid<Tile> grid);
	
	protected abstract void createStylesForSquare(int x, int y, int level, Button button);
	
	public abstract void createToolBoxButtons(FlowPane flowPane, Label currentTool);
	
	public abstract void resetFlowLevels();
	
	public void generateNewGrid(int size) {
		if (size <= 0){
			size = DEFAULT_SIZE;
		}
		
		Grid<Tile> grid = new Grid<>(size);
		
		populateGridTiles(grid);
		
		flowManager = createFlowManager(grid);
		
		createGridHistory(grid);
	}

	protected void resetGridHistory(){
		createGridHistory(getGrid());
	}

	private void createGridHistory(Grid<Tile> grid) {
		gridHistory = new GridHistory(grid, flowManager);
	}

	protected void populateGridTiles(Grid<Tile> grid) {
		TileSetupHelper tileSetuptHelper = new TileSetupHelper(grid);
		tileSetuptHelper.setDefaultTiles();
	}
	
	public void draw(){
		gridPane.getChildren().clear();
		
		int[][] currentView = gridHistory.getCurrentView();
		
		for (int x=0; x<currentView.length; x++){
			for (int y=0; y<currentView.length; y++){
				int level = currentView[x][y];
				drawSquare(x,y,level);
			}
		}
	}
	
	private void drawSquare( int x, int y, int level) {
		Button button = new Button("" + level);
		GridPane.setColumnIndex(button, x);
		GridPane.setRowIndex(button, y);
		gridPane.getChildren().add(button);
		
		createMouseClickForSquare(x, y, button);
		
		createStylesForSquare(x, y, level, button);
	}

	private void createMouseClickForSquare(int x, int y, Button button) {
		button.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				currentTool.use(x, y);
				resetGridHistory();
				draw();
			}
		});
	}
	
	protected void createdToolButton(FlowPane flowPane, Label currentTool, Tool tool) {
		Button button = new Button(tool.getTitle());
		flowPane.getChildren().add(button);
		
		button.setGraphic(new ImageView(tool.getImage()));
		button.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				setCurrentTool(tool);
				currentTool.setText(tool.getTitle());
			}
		});		
	}
	
	public void next() {
		gridHistory.next();
	}
	
	public void previous() {
		gridHistory.previous();
	}

	public Tool getCurrentTool() {
		return currentTool;
	}

	public void setCurrentTool(Tool currentTool) {
		this.currentTool = currentTool;
	}
	
	public Tile getTileAt(int x, int y){
		return gridHistory.getTile(x, y);
	}
	
	public int getGridSize(){
		return gridHistory.getGrid().getSizeInOneDimension();
	}
	
	protected Grid<Tile> getGrid(){
		return gridHistory.getGrid();
	}


}
