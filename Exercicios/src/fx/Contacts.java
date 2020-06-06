package fx;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class Contacts extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		GridPane root = new GridPane();
		root.setPrefWidth(400);
		root.setPadding(new Insets(16));
		root.setHgap(10);
		root.setVgap(10);

		ColumnConstraints columnConstraints1 = new ColumnConstraints();
		columnConstraints1.setHalignment(HPos.RIGHT);
		columnConstraints1.setMinWidth(48);
		ColumnConstraints columnConstraints2 = new ColumnConstraints();
		columnConstraints2.setHalignment(HPos.CENTER);
		columnConstraints2.setPrefWidth(250);
		columnConstraints2.setMinWidth(10);
		columnConstraints2.setHgrow(Priority.SOMETIMES);
		root.getColumnConstraints().addAll(columnConstraints1, columnConstraints2);

		Label idLabel = new Label("Id");
		TextField idTextField = new TextField();
		root.addRow(0, idLabel, idTextField);

		Label nameLabel = new Label("Nome");
		TextField nameTextField = new TextField();
		root.addRow(1, nameLabel, nameTextField);

		Label phoneLabel = new Label("Telefone");
		TextField phoneTextField = new TextField();
		root.addRow(2, phoneLabel, phoneTextField);

		Button saveButton = new Button("Salvar");
		Button searchButton = new Button("Pesquisar");
		root.add(new HBox(16, saveButton, searchButton), 1, 3);

		Scene scene = new Scene(root);
		stage.setTitle("Cadastro de Contatos");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
