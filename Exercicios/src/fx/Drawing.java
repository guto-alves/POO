package fx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Drawing extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Group grp = new Group();

		Canvas canvas = new Canvas(580, 400);
		grp.getChildren().add(canvas);

		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setStroke(Color.BLACK);
		for (double raiox = 0; raiox < 60; raiox += 5) {
			gc.beginPath();
			gc.arc(400, 200, raiox, 50, 0, 360);
			gc.stroke();
		}

		for (double raioy = 0; raioy < 60; raioy += 5) {
			gc.beginPath();
			gc.arc(400, 200, 60, raioy, 0, 360);
			gc.stroke();
		}

		Rectangle r = new Rectangle(100, 100, Color.WHITE);
		r.setX(100);
		r.setY(150);
		r.setStroke(Color.BLACK);

		Line l1 = new Line(200, 150, 150, 100);
		Line l2 = new Line(100, 150, 150, 100);
		grp.getChildren().addAll(r, l1, l2);

		Scene scene = new Scene(grp, 580, 400);
		stage.setTitle("Graphic User Interface - MyPanel");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
