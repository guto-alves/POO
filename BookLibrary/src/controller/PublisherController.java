package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import model.Publisher;
import repository.PublisherRepository;

public class PublisherController {
	private TextField nameTextField;
	private TextField addressTextField;
	private TextField phoneTextField;
	private Button saveButton;
	private Button cancelButton;
	
	private TextField filterTextField;
	private TableView<Publisher> tableView;
	private ObservableList<Publisher> publishersList;
	private Publisher publisherSelected;
	
	private final PublisherRepository publisherRepository;

	public PublisherController(TextField nameTextField, TextField addressTextField, 
			TextField phoneTextField, Button cancelButton, Button saveButton, 
			TextField filterTextField, TableView<Publisher> tableView) {
		this.nameTextField = nameTextField;
		this.addressTextField = addressTextField;
		this.phoneTextField = phoneTextField;
		this.cancelButton = cancelButton;
		this.saveButton = saveButton;
		this.filterTextField = filterTextField;
		this.tableView = tableView;
		publisherRepository = new PublisherRepository();
		initialize();
	}

	private void initialize() {
		TableColumn<Publisher, String> nameColumn = new TableColumn<>("Nome");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn<Publisher, String> addressColumn = new TableColumn<>("Endereço");
		addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
		
		TableColumn<Publisher, String> phoneColumn = new TableColumn<>("Telefone");
		phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
		
		tableView.widthProperty().addListener((observable, oldValue, newValue) -> {
			nameColumn.setPrefWidth(tableView.getWidth() / 3);
			addressColumn.setPrefWidth(tableView.getWidth() / 3);
			phoneColumn.setPrefWidth(tableView.getWidth() / 3);
		});
		
		tableView.getColumns().addAll(nameColumn, addressColumn, phoneColumn);
		
		tableView.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> {
					publisherSelected = newValue;
					if (publisherSelected == null) {
						nameTextField.setText("");
						addressTextField.setText("");
						phoneTextField.setText("");
						saveButton.setText("Adicionar");
					} else {
						nameTextField.setText(newValue.getName());
						addressTextField.setText(newValue.getAddress());
						phoneTextField.setText(newValue.getAddress());
						saveButton.setText("Atualizar");
					}
				});
		
		MenuItem deleteMenuItem = new MenuItem("Excluir");
		deleteMenuItem.setOnAction(event -> deletePublisher());
		tableView.setContextMenu(new ContextMenu(deleteMenuItem));

		publishersList = FXCollections.observableArrayList();
		FilteredList<Publisher> filteredData = new FilteredList<>(publishersList);
		filterTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(publisher -> {
				if (newValue == null || newValue.isBlank()) {
					return true;
				}

				String text = newValue.toLowerCase();

				if (publisher.getName().toLowerCase().contains(text) || 
						publisher.getAddress().toLowerCase().contains(text)) {
					return true;
				}

				return false;
			});
		});
		SortedList<Publisher> sortedList = new SortedList<Publisher>(filteredData);
		sortedList.comparatorProperty().bind(tableView.comparatorProperty());
		tableView.setItems(sortedList);
		getAllPublishers();

		cancelButton.setOnAction(event -> {
			tableView.getSelectionModel().clearSelection();
		});
		
		saveButton.setOnAction(event -> {
			if (saveButton.getText().contains("Adicionar")) {
				addPublisher();	
			} else {
				updatePublisher();
			}
		});
	}

	private void addPublisher() {
		int result = publisherRepository.addPublisher(
				nameTextField.getText(), addressTextField.getText(),
				phoneTextField.getText());

		if (result == 1) {
			displayAlert(AlertType.INFORMATION, "Editora Adicionada", 
					"Editora adicionada com sucesso!");
		} else {
			displayAlert(AlertType.ERROR, "Editora Não Adicionada",
					"Erro ao adicionar o publisher!");
		}

		getAllPublishers();
		tableView.getSelectionModel().selectLast();
	}
	
	private void updatePublisher() {
		int result = publisherRepository.updatePublisher(
				nameTextField.getText(), addressTextField.getText(),
				phoneTextField.getText(), publisherSelected.getName());

		if (result == 1) {
			displayAlert(AlertType.INFORMATION, "Editora Atualizado",
					"Editora atualizado com sucesso!");
		} else {
			displayAlert(AlertType.ERROR, "Editora Não Atualizado",
					"Erro ao atualizar o publisher!");
		}

		getAllPublishers();
	}
	
	private void deletePublisher() {
		int result = publisherRepository.deletePublisher(publisherSelected.getName());
		
		if (result == 1) {
			displayAlert(AlertType.INFORMATION, "Editora Excluído",
					"Editora excluída com sucesso!");
		} else {
			displayAlert(AlertType.ERROR, "Editora Não Excluído", 
					"Não foi possível excluir a editora selecionada!");
		}
		
		publishersList.remove(tableView.getSelectionModel().getSelectedIndex());
	}

	private void getAllPublishers() {
		publishersList.clear();
		publishersList.addAll(publisherRepository.getAllPublishers());
	}
	
	private void displayAlert(AlertType alertType, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setContentText(message);
		alert.show();
	}

}
