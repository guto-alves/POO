package fx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class HelloWorld extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		FlowPane root = new FlowPane();
		root.setPrefSize(300, 200);
		root.setPadding(new Insets(8));
		
		root.getChildren().add(new Label("Hello World"));

		Scene scene = new Scene(root);
		stage.setTitle("Hello World");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
