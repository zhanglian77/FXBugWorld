package bugworldanim;

//Plant class sets up its position, size and image.

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class Plant extends Thing{
	
	private int width = 600;
	private int height = 600;
	

	public Plant(float x, float y, int size) {
		super(x, y, size);
		
		Image image = new Image("file:/C:/Users/zhanglian4/gitLian/FXBugWorld/tree1.png");
		this.setFill(new ImagePattern(image));
	}
	
	// A grow method for plants.
	public void grow() {
		double w = Math.random()*100;
		if (w < 1) {
				this.setRadius(this.getRadius()+1);
				if(this.getRadius()>70) {
					this.setRadius(70);
				}
			}
		}
	}


