package bugworldanim;

//Butterfly class sets up its position, size and image. Besides, butterflies moves through a trial, 
//when their energy run out, they will die.


import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class Butterfly extends Thing{

	private float dx;
	private float dy;
	private int energy = 1000;
	private int width = 600;
	private int height = 600;


	public Butterfly(float x, float y, int size, float dx, float dy) {
		// TODO Auto-generated constructor stub
		super(x, y, size);
		this.dx = dx;
		this.dy = dy;

		Image image = new Image("file:/C:/Users/zhanglian4/gitLian/FXBugWorld/butterfly.png");
		this.setFill(new ImagePattern(image));
	}

	// Move method which allow those butterflies move through a trail, when they hits 
	//boundaries, they will change directions.

	public void move() {
		energy--;
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

	// Avoid method which will be used when butterflies meets obstacles, plants and foods.
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

	// Get and set energy. When energy run out, butterflies will die.
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
