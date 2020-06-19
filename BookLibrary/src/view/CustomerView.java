package view;

import controller.CustomerController;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.Customer;

public class CustomerView {
	private BorderPane root = new BorderPane();

	public CustomerView() {
		TableView<Customer> tableView = new TableView<>();
		TextField filtedTextField = new TextField();
		FlowPane flowPane = new FlowPane(8, 8, new Label("Filtrar por"), filtedTextField);
		flowPane.setPadding(new Insets(16));
		BorderPane borderPane = new BorderPane();
		borderPane.setPadding(new Insets(8));
		borderPane.setTop(flowPane);
		borderPane.setCenter(tableView);

		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		ColumnConstraints columnConstraints = new ColumnConstraints();
		columnConstraints.setHalignment(HPos.RIGHT);
		gridPane.getColumnConstraints().add(columnConstraints);

		TextField nameTextField = new TextField();
		nameTextField.setPrefWidth(200);
		TextField rgTextField = new TextField();
		TextField cpfTextField = new TextField();
		TextField emailTextField = new TextField();
		
		TextField phoneTextField = new TextField();
		Button addPhoneButton = new Button("+");
		ListView<String> phonesListView = new ListView<>();
		phonesListView.setPrefHeight(50);
		
		VBox addressVBox = new VBox(8);
		addressVBox.setAlignment(Pos.TOP_CENTER); 
		addressVBox.setBorder(new Border(new BorderStroke(
				Color.DEEPSKYBLUE, BorderStrokeStyle.SOLID, 
				CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		
		Text text = new Text("Endereço");
		text.setFill(Color.DEEPSKYBLUE);
		
		GridPane addressGridPane = new GridPane();
		addressGridPane.setPadding(new Insets(16));
		addressGridPane.setAlignment(Pos.CENTER);
		addressGridPane.setVgap(10);
		addressGridPane.setHgap(10); 
		GridPane.setColumnSpan(addressVBox, GridPane.REMAINING);
		TextField streetTextField = new TextField();
		TextField numberTextField = new TextField();
		TextField complementTextField = new TextField();
		TextField postalCodeTextField = new TextField();
		addressGridPane.addRow(0, new Label("Logradouro"), streetTextField);
		addressGridPane.addRow(1, new Label("Número"), numberTextField);
		addressGridPane.addRow(2, new Label("Compl."), complementTextField);
		addressGridPane.addRow(3, new Label("CEP"), postalCodeTextField);
		addressVBox.getChildren().addAll(text, addressGridPane);
		
		Button saveButton = new Button("Adicionar");
		Button cancelButton = new Button("Cancelar");
		
		Text warningText = new Text();
		warningText.setFill(Color.FIREBRICK);
		warningText.setText("Campo RG está vázio.");
		GridPane.setHalignment(warningText, HPos.CENTER);

		gridPane.addRow(0, new Label("Nome"), nameTextField);
		gridPane.addRow(1, new Label("RG"), rgTextField);
		gridPane.addRow(2, new Label("CPF"), cpfTextField);
		gridPane.addRow(3, new Label("Email"), emailTextField);
		gridPane.addRow(4, new Label("Telefone(s)"), 
				new VBox(8, new HBox(phoneTextField, addPhoneButton), phonesListView));
		gridPane.addRow(5, addressVBox);
		gridPane.add(new HBox(16, cancelButton, saveButton), 1, 6);
		gridPane.add(warningText, 1, 7);
		
		root.setCenter(new SplitPane(borderPane, gridPane));
		
		new CustomerController(nameTextField, rgTextField, cpfTextField, 
				emailTextField, phoneTextField, addPhoneButton,
				phonesListView, streetTextField, numberTextField, 
				complementTextField, postalCodeTextField, saveButton,
				cancelButton, warningText, filtedTextField, tableView);
	}

	public BorderPane getRoot() {
		return root;
	}
	
}
