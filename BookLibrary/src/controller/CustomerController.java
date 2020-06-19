package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCombination;
import javafx.scene.text.Text;
import model.Customer;
import repository.AddressRepository;
import repository.CustomerRepository;

public class CustomerController {
	private TextField nameTextField;
	private TextField rgTextField;
	private TextField cpfTextField;
	private TextField emailTextField;
	private TextField phoneTextField;
	private Button addPhoneButton;
	private ListView<String> phonesListView;

	private TextField streetTextField;
	private TextField numberTextField;
	private TextField complementTextField;
	private TextField postalCodeTextField;

	private Button saveButton;
	private Button cancelButton;
	
	private Text warningText;

	private TextField filtedTextField;
	private TableView<Customer> tableView;
	private ObservableList<Customer> customersList;
	private Customer customerSelected;

	private final CustomerRepository customerRepository;
	private final AddressRepository addressRepository;
	
	public CustomerController(TextField nameTextField, TextField rgTextField, TextField cpfTextField,
			TextField emailTextField, TextField phoneTextField, Button addPhoneButton, ListView<String> phonesListView,
			TextField streetTextField, TextField numberTextField, TextField complementTextField,
			TextField postalCodeTextField, Button saveButton, Button cancelButton, Text warningText,
			TextField filtedTextField, TableView<Customer> tableView) {
		this.nameTextField = nameTextField;
		this.rgTextField = rgTextField;
		this.cpfTextField = cpfTextField;
		this.emailTextField = emailTextField;
		this.phoneTextField = phoneTextField;
		this.addPhoneButton = addPhoneButton;
		this.phonesListView = phonesListView;
		this.streetTextField = streetTextField;
		this.numberTextField = numberTextField;
		this.complementTextField = complementTextField;
		this.postalCodeTextField = postalCodeTextField;
		this.saveButton = saveButton;
		this.cancelButton = cancelButton;
		this.warningText = warningText;
		this.filtedTextField = filtedTextField;
		this.tableView = tableView;
		customerRepository = new CustomerRepository();
		addressRepository = new AddressRepository();
		initialize();
	}

	private void initialize() {
		TableColumn<Customer, String> nameColumn = new TableColumn<>("Nome");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<Customer, String> rgColumn = new TableColumn<>("RG");
		rgColumn.setCellValueFactory(new PropertyValueFactory<>("rg"));

		TableColumn<Customer, String> cpfColumn = new TableColumn<>("CPF");
		cpfColumn.setCellValueFactory(new PropertyValueFactory<>("cpf"));

		TableColumn<Customer, String> emailColumn = new TableColumn<>("Email");
		emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

		tableView.getColumns().setAll(nameColumn, rgColumn, cpfColumn, emailColumn);

		tableView.widthProperty().addListener((observable, oldValue, newValue) -> {
			double width = newValue.doubleValue();
			nameColumn.setPrefWidth(width * 0.25);
			rgColumn.setPrefWidth(width * 0.25);
			cpfColumn.setPrefWidth(width * 0.25);
			emailColumn.setPrefWidth(width * 0.25);
		});

		tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			customerSelected = newValue;
			if (customerSelected == null) {
				nameTextField.setText("");
				rgTextField.setText("");
				cpfTextField.setText("");
				emailTextField.setText("");
				
				streetTextField.setText("");
				numberTextField.setText("");
				complementTextField.setText("");
				postalCodeTextField.setText("");
				
				saveButton.setText("Adicionar");
			} else {
				nameTextField.setText(customerSelected.getName());
				rgTextField.setText(customerSelected.getRg());
				cpfTextField.setText(customerSelected.getCpf());
				emailTextField.setText(customerSelected.getEmail());
				
				streetTextField.setText(customerSelected.getAddress().getStreet());
				numberTextField.setText(String.valueOf(
						customerSelected.getAddress().getNumber()));
				complementTextField.setText(customerSelected.getAddress().getComplement());
				postalCodeTextField.setText(customerSelected.getAddress().getPostalCode());
				
				saveButton.setText("Atualizar");
			}
		});

		MenuItem excluirMenuItem = new MenuItem("Excluir");
		excluirMenuItem.setAccelerator(KeyCombination.keyCombination("DELETE"));
		excluirMenuItem.setOnAction(event -> {
			if (customerSelected == null) {
				return;
			}

			deleteCustomer();
		});
		tableView.setContextMenu(new ContextMenu(excluirMenuItem));

		customersList = FXCollections.observableArrayList();
		FilteredList<Customer> filteredList = new FilteredList<>(customersList);
		filtedTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredList.setPredicate(customer -> {
				if (newValue == null || newValue.isBlank()) {
					return true;
				}

				String text = newValue.toLowerCase();

				if (customer.getRg().toLowerCase().contains(text)
						|| customer.getCpf().toLowerCase().contains(text)
						|| customer.getName().toLowerCase().contains(text)
						|| customer.getEmail().toLowerCase().contains(text)) {
					return true;
				}

				return false;
			});
		});
		SortedList<Customer> sortedList = new SortedList<Customer>(filteredList);
		sortedList.comparatorProperty().bind(tableView.comparatorProperty());
		tableView.setItems(sortedList);
		getAllCustomers();

		addPhoneButton.setOnAction(event -> {
			phonesListView.getItems().add(phoneTextField.getText());
			phoneTextField.setText("");
		});
		
		MenuItem removePhoneMenuItem = new MenuItem("Remover");
		removePhoneMenuItem.setOnAction(event -> {
			phonesListView.getItems().remove(
					phonesListView.getSelectionModel().getSelectedIndex());
		});
		phonesListView.setContextMenu(new ContextMenu(removePhoneMenuItem));
		
		cancelButton.setOnAction(event -> {
			tableView.getSelectionModel().clearSelection();
		});

		saveButton.setOnAction(event -> {
			if (hasInvalidFields()) {
				System.out.println("Preencha os campos obrigatórios");
				return;
			}

			if (saveButton.getText().contains("Adicionar")) {
				addCustomer();
			} else {
				updateCustomer();
			}
		});
	}

	private boolean hasInvalidFields() {
		return nameTextField.getText().isBlank() || 
				rgTextField.getText().isBlank() ||
				cpfTextField.getText().isBlank() ||
				emailTextField.getText().isBlank();
	}

	private void addCustomer() {
		int result = customerRepository.addCustomer(
				rgTextField.getText(), cpfTextField.getText(),
				nameTextField.getText(), emailTextField.getText(),
				phonesListView.getItems());

		if (result == 1) {
			displayAlert(AlertType.INFORMATION, "Cliente Adicionado", 
					"Cliente adicionado com sucesso!");
		} else {
			displayAlert(AlertType.ERROR, "Cliente Não Adicionado",
					"Erro ao adicionar o cliente!");
		}

		getAllCustomers();
		tableView.getSelectionModel().selectLast();
	}

	private void updateCustomer() {
		int result = customerRepository.updateCustomer(nameTextField.getText(), rgTextField.getText(),
				cpfTextField.getText(), emailTextField.getText(), customerSelected.getRg());

		if (result == 1) {
			displayAlert(AlertType.INFORMATION, "Cliente Atualizado", 
					"Cliente atualizado com sucesso!");
		} else {
			displayAlert(AlertType.ERROR, "Cliente Não Atualizado",
					"Erro ao atualizar o cliente!");
		}

		getAllCustomers();
	}

	private void deleteCustomer() {
		int result = customerRepository.deleteCustomer(
				customerSelected.getRg());

		if (result == 1) {
			displayAlert(AlertType.INFORMATION, "Cliente Excluído", "Cliente excluído com sucesso!");
		} else {
			displayAlert(AlertType.ERROR, "Cliente Não Excluído",
					"Não foi possível excluir o cliente selecionado!");
		}

		customersList.remove(tableView.getSelectionModel().getSelectedIndex());
	}

	private void getAllCustomers() {
		customersList.setAll(customerRepository.getAllCustomers());
	}

	private void displayAlert(AlertType alertType, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setContentText(message);
		alert.show();
	}
	
}
