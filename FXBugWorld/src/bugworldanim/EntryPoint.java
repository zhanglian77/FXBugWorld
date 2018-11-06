package bugworldanim;
// Main class.

import java.util.ArrayList;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class EntryPoint extends Application {
	private int width = 600; 
	private int height = 600;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

		// Add buttons to control the animation.
		Button play = new Button();
		play.setText("Play");
		Button pause = new Button();
		pause.setText("Pause");
		Button stop = new Button();
		stop.setText("Stop");
		play.setLayoutX(5);
		play.setLayoutY(575);
		pause.setLayoutX(50);
		pause.setLayoutY(575);
		stop.setLayoutX(105);
		stop.setLayoutY(575);

		Text text = new Text();
		text.setLayoutX(160);
		text.setLayoutY(580);
		text.setText("Hello Bug");
		text.setFont(Font.font("Times New Roman", FontWeight.BOLD,15));


		// Add different classes into Arraylists.
		List<Bee> bees = new ArrayList<Bee>();

		for(int i = 0; i < 8; i++) {
			Bee a = new Bee((float)Math.random()*width, (float)Math.random()*height, 10, 1f, 1f);
			bees.add(a);
		}

		List<Butterfly> butterflys = new ArrayList<Butterfly>();
		for(int i = 0; i < 3; i++) {
			Butterfly c = new Butterfly((float)Math.random()*width, (float)Math.random()*height, 20, 2f, 2f);
			butterflys.add(c);
		}

		List<Flower> foods = new ArrayList<Flower>();
		for(int i = 0; i < 6; i++) {
			Flower b = new Flower((float)Math.random()*width, (float)Math.random()*height, 20);
			foods.add(b);
		}

		List<Plant> plants = new ArrayList<Plant>();
		for(int i = 0; i < 5; i++) {
			Plant d = new Plant((float)Math.random()*width, (float)Math.random()*height, 20);
			plants.add(d);
		}

		List<Obstacle> obstacles = new ArrayList<Obstacle>();
		for(int i = 0; i < 4; i++) {
			Obstacle e = new Obstacle((float)Math.random()*width, (float)Math.random()*height, 40);
			obstacles.add(e);
		}

		// Create Group and add things and buttons into group.
		Group root = new Group();

		root.getChildren().addAll(bees);
		root.getChildren().addAll(foods);
		root.getChildren().addAll(butterflys);
		root.getChildren().addAll(plants);
		root.getChildren().addAll(obstacles);

		root.getChildren().add(play);
		root.getChildren().add(pause);
		root.getChildren().add(stop);
		root.getChildren().add(text);

		// Create scene and add background to the scene.
		final Scene scene = new Scene(root, width, height);

		Image image = new Image("file:/C:/Users/zhanglian4/gitLian/FXBugWorld/background.png");
		scene.setFill(new ImagePattern(image));

		// Create frame.
		KeyFrame frame = new KeyFrame(Duration.millis(15), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				// Let the plants grow.
				for(int v = 0; v < plants.size(); v++) {
					plants.get(v).grow();
				}

				// Let the foods(flowers) grow.
				for(int h = 0; h < foods.size(); h++) {
					foods.get(h).grow();
				}

				// Let bees move.
				for(int q = 0; q < bees.size(); q++) {
					bees.get(q).move();
				}

				// Let butterflies move.
				for(int p = 0; p < butterflys.size(); p++) {
					butterflys.get(p).move();
				}

				// Set up avoid method to let bees fly away when they meet obstacles and 
				// foods. They obtain energy when they meet foods, and die out when energy
				// run out.
				for(Bee n :bees) {

					for(Obstacle o :obstacles) {
						if (n.getBoundsInParent().intersects(o.getBoundsInParent())) {
							n.avoid();
							n.setEnergy(n.getEnergy()-20);
							text.setText("Bee" + bees.indexOf(n) + "hits a rock." + " Now its energy is: " + n.getEnergy() );
						}
					}


					for(Flower f : foods) {
						if (f.getBoundsInParent().intersects(n.getBoundsInParent())){
							n.setEnergy(n.getEnergy()+200);
							n.avoid();
							f.setRadius(f.getRadius()-1);
							text.setText("Bee" + bees.indexOf(n) + "eats a flower." + " Now its energy is: " + n.getEnergy() );
						}
					}

					if(n.getEnergy() <= 0) {
						n.die();
						bees.remove(n);
						text.setText("Bee" + bees.indexOf(n) + "dies." );
					}
				}

				// Set up avoid method to let butterflies fly away when they meet obstacles, 
				// plants and foods. They obtain energy when they meet foods and plants, 
				// and die out when energy run out.

				for(Butterfly m : butterflys) {

					for(Obstacle o : obstacles)
						if (m.getBoundsInParent().intersects(o.getBoundsInParent())) {
							m.avoid();
							m.setEnergy(m.getEnergy()-20);
							text.setText("Butterfly" + butterflys.indexOf(m) + "hits a rock." + " Now its energy is: " + m.getEnergy() );
						}

					for(Plant p:plants) {
						if (p.getBoundsInParent().intersects(m.getBoundsInParent())) {
							m.avoid();
							m.setEnergy(m.getEnergy()+100);
							p.setRadius(p.getRadius()-1);
							text.setText("Butterfly" + butterflys.indexOf(m) + "eats a plant." + " Now its energy is: " + m.getEnergy() );
						}
					}

					for(Flower f : foods) {
						if (f.getBoundsInParent().intersects(m.getBoundsInParent())) {
							m.avoid();
							m.setEnergy(m.getEnergy()+200);
							f.setRadius(f.getRadius()-1);
							text.setText("Butterfly" + butterflys.indexOf(m) + "eats a flower." + " Now its energy is: " + m.getEnergy() );
						}
					}

					if(m.getEnergy() <= 0) {
						m.die();
						butterflys.remove(m);
						text.setText("Butterfly " + butterflys.indexOf(m) + " dies."  );
					}
				}
			}
		});

		// Set up timeline.
		Timeline timeline = new Timeline(frame);
		timeline.setCycleCount(javafx.animation.Animation.INDEFINITE);
		timeline.play();

		// Set up control method of each buttons.
		play.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				timeline.play();
			}
		});

		pause.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				timeline.pause();
			}
		});

		stop.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				timeline.stop();
				primaryStage.close();
			}
		});

		// Set up primaryStage.
		primaryStage.setTitle("Bug World");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);



	}
}

