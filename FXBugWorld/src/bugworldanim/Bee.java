package bugworldanim;

//Bee class sets up its position, size and image. Besides, bees moves randomly, when their
// energy run out, they will die.

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class Bee extends Thing {

	private float dx;
	private float dy;
	private int energy = 10000;
	private int width = 600;
	private int height = 600;

	public Bee(float x, float y, int size, float dx, float dy) {
		// TODO Auto-generated constructor stub
		super(x, y, size);
		this.dx = dx;
		this.dy = dy;

		Image image = new Image("file:/C:/Users/zhanglian4/gitLian/FXBugWorld/bee.png");
		this.setFill(new ImagePattern(image));
	}

	// Move method which allow those bees move randomly within the boundaries.
	public void move() {
		energy --;
		double d = Math.random();
		if (d < 0.25) {
			dx = Math.abs(dx);}

		else if (d < 0.5)
			dx = -Math.abs(dx);
		else if (d < 0.75)
			dy = Math.abs(dy);
		else
			dy = -Math.abs(dy);

		if(this.getCenterX() + this.getTranslateX() < this.getRadius()) {
			dx = Math.abs(dx);
		}

		if(this.getCenterX() + this.getTranslateX() + this.getRadius() > width) {
			dx = -Math.abs(dx);
		}	


		if(this.getCenterY() + this.getTranslateY() < this.getRadius()) {
			dy = Math.abs(dy);
		}

		if(	this.getCenterY() + this.getTranslateY() + this.getRadius() > height){
			dy = -Math.abs(dy);
		}

		this.setTranslateX(this.getTranslateX()+dx);
		this.setTranslateY(this.getTranslateY()+dy);
	}

	// Avoid method which will be used when bees meets obstacles, plants and foods.
	public void avoid() {

		double x = Math.random()*10;

		if (x >=5) {
			dy = -dy;
		} else {
			dx = -dx;
		}
		this.setTranslateX(this.getTranslateX()+dx);
		this.setTranslateY(this.getTranslateY()+dy);
	}

	// Get and set energy. When energy run out, bees will die.
	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public int getEnergy() {
		return energy;
	}

	public void die() {
		this.setRadius(0);
	}
}
