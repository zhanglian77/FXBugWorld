package bugworldanim;

// The very original class which extends Circle and other classes extends from this.

import javafx.scene.shape.Circle;

public class Thing extends Circle{
	
	float x; 
	float y;
	int size;
	int width = 600; 
	int height = 600;
	
	public Thing(float x, float y, int size) {
		super(x, y, size);
		
		
	}
	

}
