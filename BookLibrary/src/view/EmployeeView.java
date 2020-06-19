package view;

import controller.EmployeeController;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Employee;
import model.Role;

public class EmployeeView {
	private BorderPane root = new BorderPane();

	public EmployeeView() {
		TableView<Employee> tableView = new TableView<>();
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
		TextField phoneTextField = new TextField();
		ComboBox<Role> rolesCheckBox = new ComboBox<>();
		TextField emailTextField = new TextField();
		PasswordField senhaPasswordField = new PasswordField();
		TextField passwordTextField = new TextField();
		Button saveButton = new Button("Adicionar");
		Button cancelButton = new Button("Cancelar");

		CheckBox mostrarSenhaCheckBox = new CheckBox();
		passwordTextField.managedProperty().bind(mostrarSenhaCheckBox.selectedProperty());
		passwordTextField.visibleProperty().bind(mostrarSenhaCheckBox.selectedProperty());
		senhaPasswordField.managedProperty().bind(mostrarSenhaCheckBox.selectedProperty().not());
		senhaPasswordField.visibleProperty().bind(mostrarSenhaCheckBox.selectedProperty().not());
		senhaPasswordField.textProperty().bindBidirectional(passwordTextField.textProperty());
		
		gridPane.addRow(0, new Label("Nome"), nameTextField);
		gridPane.addRow(1, new Label("Telefone"), phoneTextField);
		gridPane.addRow(2, new Label("Cargo"), rolesCheckBox);
		gridPane.addRow(3, new Label("Email"), emailTextField);
		gridPane.addRow(4, new Label("Senha"),
				new VBox(8, 
						new HBox(senhaPasswordField, passwordTextField), 
						new HBox(8, new Label("Mostrar Senha?"), mostrarSenhaCheckBox)));
		gridPane.add(new HBox(16, cancelButton, saveButton), 1, 5);

		root.setCenter(new SplitPane(borderPane, gridPane));
		
		new EmployeeController(nameTextField, phoneTextField, rolesCheckBox,
				emailTextField, passwordTextField, cancelButton,
				saveButton, filtedTextField, tableView);
	}

	public BorderPane getRoot() {
		return root;
	}
}
