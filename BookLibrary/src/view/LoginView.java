package view;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Callback;
import model.Employee;
import repository.EmployeeRepository;

public class LoginView {
	private Dialog<Void> dialog;
	private Callback<Employee, Void> callback;
	private EmployeeRepository employeeRepository;

	public LoginView(Callback<Employee, Void> callback) {
		this.callback = callback;
		employeeRepository = new EmployeeRepository();
		dialog = new Dialog<>();
		dialog.setTitle("Biblioteca");
		dialog.setHeaderText("Bem-Vindo");

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextField email = new TextField();
		email.setPromptText("Email");
		PasswordField password = new PasswordField();
		password.setPromptText("Senha");
		Button loginButton = new Button("Entrar");
		Text warningText = new Text();
		warningText.setFill(Color.FIREBRICK);
		warningText.setFont(Font.font(16));
		
		grid.addRow(0, new Label("Email:"), email);
		grid.addRow(1, new Label("Senha:"), password);
		grid.add(loginButton, 1, 2);
		grid.addRow(3, warningText);

		// sem isso o dialog não fecha
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
				dialog.setHeaderText("Bem-Vindo, " + employee.getName().split(" ")[0]);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
					Thread.currentThread().interrupt(); 
				}
				
				dialog.close();
				callback.call(employee);
			} else {
				warningText.setText("Email ou senha inválidos");
			}
		});

		dialog.getDialogPane().setContent(grid);

		Platform.runLater(() -> email.requestFocus());
	}

	public void show() {
		dialog.showAndWait();
	}
}
