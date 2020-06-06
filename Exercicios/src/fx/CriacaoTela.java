package fx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CriacaoTela extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		VBox root = new VBox();
		root.setPrefSize(580, 180);
		root.setPadding(new Insets(8));
		root.setSpacing(10);

		Label phoneLabel = new Label("Enter your phone number: ");
		TextField phoneTextField = new TextField();
		phoneTextField.setPrefWidth(300);
		root.getChildren().add(new FlowPane(10, 10, phoneLabel, phoneTextField));

		Label nameLabel = new Label("Enter your name: ");
		TextField nameTextField = new TextField();
		nameTextField.setPrefSize(300, 80);
		root.getChildren().add(new FlowPane(nameLabel, nameTextField));

		Button okButton = new Button("Ok");
		okButton.setPrefWidth(230);
		Button cancelButton = new Button("Cancel");
		cancelButton.setPrefWidth(230);
		root.getChildren().addAll(new FlowPane(10, 10, okButton, cancelButton));

		Scene scene = new Scene(root);
		stage.setTitle("Graphic User Interface Test");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
