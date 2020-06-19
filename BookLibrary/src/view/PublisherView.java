package view;

import controller.PublisherController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.Publisher;

public class PublisherView {
	private BorderPane root = new BorderPane();

	public PublisherView() {
		TableView<Publisher> tableView = new TableView<>();
		TextField filterTextField = new TextField();
		FlowPane flowPane = new FlowPane(8, 8, new Label("Filtrar por"), filterTextField);
		flowPane.setPadding(new Insets(16));
		BorderPane borderPane = new BorderPane();
		borderPane.setPadding(new Insets(8));
		borderPane.setTop(flowPane);
		borderPane.setCenter(tableView);

		TextField nameTextField = new TextField();
		TextField addressTextField = new TextField();
		TextField phoneTextField = new TextField();
		Button saveButton = new Button("Adicionar");
		Button cancelButton = new Button("Cancelar");
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.addRow(0, new Label("Nome"), nameTextField);
		gridPane.addRow(1, new Label("Endereço"), addressTextField);
		gridPane.addRow(2, new Label("Telefone"), phoneTextField);
		gridPane.add(new HBox(16, cancelButton, saveButton), 1, 3);

		root.setCenter(new SplitPane(borderPane, gridPane));
		
		new PublisherController(nameTextField, addressTextField, 
				phoneTextField, cancelButton, saveButton, 
				filterTextField, tableView);
	}

	public BorderPane getRoot() {
		return root;
	}
}
