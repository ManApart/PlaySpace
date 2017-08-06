package rak.playspace.ui.toolbox;

import javafx.scene.image.Image;

public interface Tool {
	
	public void use(int x, int y);
	public String getTitle();
	public Image getImage();

}
