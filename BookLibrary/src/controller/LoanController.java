package controller;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Book;
import model.Loan;
import repository.LoanRepository;

public class LoanController {
	private ObjectProperty<Loan> loanSelected = new SimpleObjectProperty<>();
	private StringProperty loanDate = new SimpleStringProperty();
	private StringProperty returnDate = new SimpleStringProperty();
	private StringProperty dateReturned = new SimpleStringProperty();
	private StringProperty customerRg = new SimpleStringProperty();
	private StringProperty customerName = new SimpleStringProperty();
	private StringProperty employeeName = new SimpleStringProperty();
	private ObservableList<Book> books;
	
	private ObservableList<Loan> loans;

	private final LoanRepository loanRepository;

	public LoanController() {
		loanRepository = new LoanRepository();
		loans = FXCollections.observableArrayList();
		employeeName.set("Maria Alves");
		getAllLoans();
	}

//	private boolean hasInvalidFields() {
//		return nameTextField.getText().isBlank() || emailTextField.getText().isBlank()
//				|| passwordTextField.getText().isBlank() || rolesComboBox.getSelectionModel().isEmpty();
//	}

	public void addLoan() {
		int result = 0;

		if (result == 1) {
			displayAlert(AlertType.INFORMATION, "Funcion�rio Adicionado", 
					"Funcion�rio adicionado com sucesso!");
		} else {
			displayAlert(AlertType.ERROR, "Funcion�rio N�o Adicionado",
					"Erro ao adicionar o funcion�rio!");
		}

		getAllLoans();
//		tableView.getSelectionModel().selectLast();
	}

	public void updateLoan() {
		int result = 0;

		if (result == 1) {
			displayAlert(AlertType.INFORMATION, "Funcion�rio Atualizado", "Funcion�rio atualizado com sucesso!");
		} else {
			displayAlert(AlertType.ERROR, "Funcion�rio N�o Atualizado", "Erro ao atualizar o funcion�rio!");
		}

		getAllLoans();
	}

	private void getAllLoans() {
		loans.setAll(loanRepository.getAllLoans());
	}

	private void displayAlert(AlertType alertType, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setContentText(message);
		alert.show();
	}

	public ObservableList<Loan> getLoans() {
		return loans;
	}

	public ObjectProperty<Loan> getLoanSelected() {
		return loanSelected;
	}

	public StringProperty getLoanDate() {
		return loanDate;
	}

	public StringProperty getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(StringProperty returnDate) {
		this.returnDate = returnDate;
	}

	public StringProperty getDateReturned() {
		return dateReturned;
	}

	public void setDateReturned(StringProperty dateReturned) {
		this.dateReturned = dateReturned;
	}

	public StringProperty getCustomerName() {
		return customerName;
	}

	public void setCustomerName(StringProperty customerName) {
		this.customerName = customerName;
	}

	public StringProperty getEmployeeName() {
		return employeeName;
	}

	public Property<String> getCustomerRg() {
		return customerRg;
	}

	public void setLoanSelected(Loan newValue) {
		loanSelected.set(newValue);
	}

}
