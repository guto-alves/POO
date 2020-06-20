package view;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Callback;
import model.Employee;
import repository.EmployeeRepository;

public class LoginView {
	private Dialog<Void> dialog;
	private EmployeeRepository employeeRepository;

	public LoginView(Callback<Employee, Void> callback) {
		employeeRepository = new EmployeeRepository();

		dialog = new Dialog<>();
		dialog.setTitle("Biblioteca");
		dialog.getDialogPane().setBackground(new Background(
				new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		dialog.setResizable(true);

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 0, 25));

		ColumnConstraints columnConstraints1 = new ColumnConstraints(50, 50, 50);
		ColumnConstraints columnConstraints2 = new ColumnConstraints(50, 250, 250);
		columnConstraints2.setMaxWidth(Double.MAX_VALUE);
		columnConstraints2.setHgrow(Priority.ALWAYS);
		grid.getColumnConstraints().addAll(columnConstraints1, columnConstraints2);

		Text title = new Text("Bem-Vindo");
		title.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));

		TextField email = new TextField();
		email.setPromptText("Email");

		PasswordField password = new PasswordField();
		password.setPromptText("Senha");

		Button loginButton = new Button("Entrar");
		loginButton.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
		Text text = new Text("Entrar");
		text.setFont(Font.font("Serif", FontWeight.SEMI_BOLD, 16)); 
		text.setFill(Color.WHITE);
		loginButton.setGraphic(text);
		loginButton.setPadding(new Insets(5, 25, 5, 25));
		loginButton.setBackground(new Background(
				new BackgroundFill(Color.CORNFLOWERBLUE, new CornerRadii(5), Insets.EMPTY)));
		loginButton.setDisable(true);
		GridPane.setHalignment(loginButton, HPos.RIGHT);

		Text warningText = new Text();
		warningText.setFill(Color.FIREBRICK);
		warningText.setFont(Font.font(14));

		grid.add(title, 0, 0, 2, 1);
		grid.addRow(1, new Label("Email:"), email);
		grid.addRow(2, new Label("Senha:"), password);
		grid.add(loginButton, 1, 3);
		grid.add(warningText, 1, 4);

		ButtonType buttonType = new ButtonType("Entrar", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().add(buttonType);
		Node buttonTypeNode = dialog.getDialogPane().lookupButton(buttonType);
		buttonTypeNode.setDisable(true);
		buttonTypeNode.setManaged(false);
		buttonTypeNode.setVisible(false);

		email.textProperty().addListener((observable, oldValue, newValue) -> {
			loginButton.setDisable(newValue.trim().isBlank() || 
					password.getText().isBlank());
			warningText.setText("");
		});

		password.textProperty().addListener((observable, oldValue, newValue) -> {
			loginButton.setDisable(newValue.trim().isBlank() || 
					email.getText().isBlank());
			warningText.setText("");
		});

		loginButton.setOnAction(event -> {
			Employee employee = employeeRepository.getEmployee(
					email.getText(), password.getText());

			if (employee != null) {
				title.setText("Bem-Vindo, " + employee.getName().split(" ")[0]);
				warningText.setFill(Color.BLUE);
				warningText.setText("Carregando ...");

				Task<Void> close = new Task<>() {

					@Override
					protected Void call() throws Exception {
						Thread.sleep(2000);
						return null;
					}
				};
				new Thread(close).start();

				close.setOnSucceeded(value -> {
					dialog.close();
					callback.call(employee);
				});
			} else {
				warningText.setText("Email ou senha inválidos.");
			}
		});

		dialog.getDialogPane().setContent(grid);

		Platform.runLater(() -> email.requestFocus());
	}

	public void show() {
		dialog.showAndWait();
	}
}
