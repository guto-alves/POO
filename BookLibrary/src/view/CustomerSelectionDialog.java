package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import model.Customer;
import repository.CustomerRepository;

public class CustomerSelectionDialog {

	public interface CustomerSelectionDialogListener {
		void onDialogClosed(Customer customer);
	}

	public void show() {
		dialog.show();
	}
	
	private Dialog<Customer> dialog;
	private CustomerRepository customerRepository = new CustomerRepository();
	
	public CustomerSelectionDialog(CustomerSelectionDialogListener listener) {
		TextField filtedTextField = new TextField();
		
		TableView<Customer> tableView = new TableView<>();
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

		ObservableList<Customer> customersList = FXCollections.observableArrayList();
		tableView.setItems(customersList);
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
		
		customersList.addAll(customerRepository.getAllCustomers());
		
		Button selectButton = new Button("Selecionar");
		selectButton.setOnAction(event -> {
			listener.onDialogClosed(tableView.getSelectionModel().getSelectedItem());
			dialog.hide();
			dialog.close();
		});
		BorderPane.setAlignment(selectButton, Pos.CENTER_RIGHT);

		FlowPane flowPane = new FlowPane(8, 8, new Label("Filtrar por"), filtedTextField);
		flowPane.setPadding(new Insets(16));
		BorderPane borderPane = new BorderPane();
		borderPane.setPadding(new Insets(8));
		borderPane.setTop(flowPane);
		borderPane.setCenter(tableView);
		borderPane.setBottom(selectButton);

		dialog = new Dialog<>();
		dialog.setGraphic(borderPane);
		dialog.setTitle("Selecione um Cliente");
	}
	
}
