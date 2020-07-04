package fxeventos;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class JogoSimples extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Pane root = new Pane();
		Circle circle = new Circle(20);
		circle.setFill(Color.BLUE);
		circle.relocate(300, 200);
		root.getChildren().add(circle);
		
		Scene scene = new Scene(root, 640, 480);
	
		scene.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.UP) {
				circle.setLayoutY(circle.getLayoutY() - 10);
			} else if(event.getCode() == KeyCode.DOWN) {
				circle.setLayoutY(circle.getLayoutY() + 10);
			} else if(event.getCode() == KeyCode.LEFT) {
				circle.setLayoutX(circle.getLayoutX() - 10);
			} else if(event.getCode() == KeyCode.RIGHT) {
				circle.setLayoutX(circle.getLayoutX() + 10);
			}
			
			if (circle.getLayoutX() < 20) {
				circle.setLayoutX(20);
			} else if (circle.getLayoutX() > 620) {
				circle.setLayoutX(620);
			}
			
			if (circle.getLayoutY() < 20) {
				circle.setLayoutY(20);
			} else if (circle.getLayoutY() > 460) {
				circle.setLayoutY(460);
			}
		});
		
		stage.setTitle("Jogo Simples");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
