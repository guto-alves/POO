package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Employee;
import model.Role;
import repository.EmployeeRepository;

public class EmployeeController {
	private static final String DEFAULT_PASSWORD = "12345678";
	
	private TextField nameTextField;
	private TextField phoneTextField; 
	private ComboBox<Role> rolesComboBox;
	private TextField emailTextField;
	private TextField passwordTextField;
	private Button saveButton;
	private Button cancelButton;
	
	private TextField filtedTextField;
	private TableView<Employee> tableView;
	private ObservableList<Employee> employeesList;
	private Employee employeeSelected;
	
	private final EmployeeRepository employeeRepository;

	public EmployeeController(TextField nameTextField, TextField phoneTextField, 
			ComboBox<Role> rolesComboBox, TextField emailTextField, 
			TextField passwordTextField, Button cancelButton, Button saveButton, 
			TextField filtedTextField, TableView<Employee> tableView) {
		this.nameTextField = nameTextField;
		this.phoneTextField = phoneTextField;
		this.rolesComboBox = rolesComboBox;
		this.emailTextField = emailTextField;
		this.passwordTextField = passwordTextField;
		this.cancelButton = cancelButton;
		this.saveButton = saveButton;
		this.filtedTextField = filtedTextField;
		this.tableView = tableView;
		employeeRepository = new EmployeeRepository();
		initialize();
	}

	private void initialize() {
		TableColumn<Employee, String> idColumn = new TableColumn<>("ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		TableColumn<Employee, String> nameColumn = new TableColumn<>("Nome");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn<Employee, String> phoneColumn = new TableColumn<>("Telefone");
		phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
		
		TableColumn<Employee, String> emailColumn = new TableColumn<>("Email");
		emailColumn .setCellValueFactory(new PropertyValueFactory<>("email"));
		
		TableColumn<Employee, String> roleColumn = new TableColumn<>("Cargo");
		roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
		
		tableView.getColumns().setAll(idColumn, nameColumn, phoneColumn,
				emailColumn, roleColumn);
		
		tableView.widthProperty().addListener((observable, oldValue, newValue) -> {
			double width = newValue.doubleValue();
			idColumn.setPrefWidth(width * 0.1);
			nameColumn.setPrefWidth(width * 0.225);
			phoneColumn.setPrefWidth(width * 0.225);
			emailColumn.setPrefWidth(width * 0.225);
			roleColumn.setPrefWidth(width * 0.225);
		});
		
		tableView.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> {
					employeeSelected = newValue;
					if (employeeSelected == null) {
						nameTextField.setText("");
						phoneTextField.setText("");
						rolesComboBox.getSelectionModel().clearSelection();
						emailTextField.setText("");
						passwordTextField.setText(DEFAULT_PASSWORD);
						saveButton.setText("Adicionar");
					} else {
						nameTextField.setText(employeeSelected.getName());
						phoneTextField.setText(employeeSelected.getPhone());
						rolesComboBox.getSelectionModel()
								.select(employeeSelected.getRole());
						emailTextField.setText(employeeSelected.getEmail());
						passwordTextField.setText(employeeSelected.getPassword());
						saveButton.setText("Atualizar");
					}
				});
		
		MenuItem excluirMenuItem = new MenuItem("Excluir");
		excluirMenuItem.setOnAction(event -> {
			if (employeeSelected == null) {
				return;
			}
			
			deleteEmployee();
		});
		tableView.setContextMenu(new ContextMenu(excluirMenuItem));

		employeesList = FXCollections.observableArrayList();
		FilteredList<Employee> filteredList = new FilteredList<>(employeesList);
		filtedTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredList.setPredicate(employee -> {
				if (newValue == null || newValue.isBlank()) {
					return true;
				}

				String text = newValue.toLowerCase();

				if (String.valueOf(employee.getId()).toLowerCase().contains(text) ||
						employee.getName().toLowerCase().contains(text) || 
						employee.getPhone().toLowerCase().contains(text) ||
						employee.getEmail().toLowerCase().contains(text) ||
						employee.getRole().getName().toLowerCase().contains(text)) {
					return true;
				}

				return false; 
			});
		});
		SortedList<Employee> sortedList = new SortedList<Employee>(filteredList);
		sortedList.comparatorProperty().bind(tableView.comparatorProperty());
		tableView.setItems(sortedList);
		getAllEmployees();

		rolesComboBox.setItems(FXCollections.observableArrayList(Role.values()));
		
		passwordTextField.setText(DEFAULT_PASSWORD);
		
		cancelButton.setOnAction(event -> {
			tableView.getSelectionModel().clearSelection();
		});
		
		saveButton.setOnAction(event -> {
			if (hasInvalidFields()) {
				System.out.println("Preencha os campos obrigatórios");
				return;
			}
			
			if (saveButton.getText().contains("Adicionar")) {
				addEmployee();	
			} else {
				updateEmployee();
			}
		});
	}
	
	private boolean hasInvalidFields() {
		return nameTextField.getText().isBlank() || 
				emailTextField.getText().isBlank() ||
				passwordTextField.getText().isBlank() ||
				rolesComboBox.getSelectionModel().isEmpty();
	}

	private void addEmployee() {
		int result = employeeRepository.addEmployee(
				nameTextField.getText(), phoneTextField.getText(),
				rolesComboBox.getSelectionModel().getSelectedItem().name(),
				emailTextField.getText(), passwordTextField.getText());

		if (result == 1) {
			displayAlert(AlertType.INFORMATION, "Funcionário Adicionado", 
					"Funcionário adicionado com sucesso!");
		} else {
			displayAlert(AlertType.ERROR, "Funcionário Não Adicionado",
					"Erro ao adicionar o funcionário!");
		}

		getAllEmployees();
		tableView.getSelectionModel().selectLast();
	}
	
	private void updateEmployee() {
		int result = employeeRepository.updateEmployee(
				nameTextField.getText(), phoneTextField.getText(), 
				rolesComboBox.getSelectionModel().getSelectedItem().name(),
				emailTextField.getText(), passwordTextField.getText(),
				employeeSelected.getId());

		if (result == 1) {
			displayAlert(AlertType.INFORMATION, "Funcionário Atualizado",
					"Funcionário atualizado com sucesso!");
		} else {
			displayAlert(AlertType.ERROR, "Funcionário Não Atualizado",
					"Erro ao atualizar o funcionário!");
		}

		getAllEmployees();
	}
	
	private void deleteEmployee() {
		int result = employeeRepository.deleteEmployee(
				employeeSelected.getId());
		
		if (result == 1) {
			displayAlert(AlertType.INFORMATION, "Funcionário Excluído",
					"Funcionário excluído com sucesso!");
		} else {
			displayAlert(AlertType.ERROR, "Funcionário Não Excluído", 
					"Não foi possível excluir a funcionário selecionado!");
		}
		
		employeesList.remove(tableView.getSelectionModel().getSelectedIndex());
	}

	private void getAllEmployees() {
		employeesList.setAll(employeeRepository.getAllEmployees());
	}
	
	private void displayAlert(AlertType alertType, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setContentText(message);
		alert.show();
	}
	
}
