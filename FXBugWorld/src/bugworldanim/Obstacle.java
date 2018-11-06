package bugworldanim;

// Obstacle class sets up its position, size and image.

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class Obstacle extends Thing{
	
	private int width = 600;
	private int height = 600;
	
	public Obstacle(float x, float y, int size) {
		super(x, y, size);
		
		Image image = new Image("file:/C:/Users/zhanglian4/gitLian/FXBugWorld/rock.png");
		this.setFill(new ImagePattern(image));
	}

}
