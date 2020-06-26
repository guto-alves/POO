package controller;

import java.util.stream.Collectors;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert.AlertType;
import model.Book;
import model.EmployeeOn;
import model.Loan;
import repository.LoanRepository;

public class LoanController {
	private StringProperty loanDate = new SimpleStringProperty();
	private StringProperty returnDate = new SimpleStringProperty();
	private StringProperty dateReturned = new SimpleStringProperty();
	private StringProperty customerRg = new SimpleStringProperty();
	private StringProperty customerName = new SimpleStringProperty();
	private StringProperty employeeName = new SimpleStringProperty();
	private ObservableList<Book> books = FXCollections.observableArrayList();

	private ObservableList<Loan> loans = FXCollections.observableArrayList();

	private ObjectProperty<Loan> loanSelected = new SimpleObjectProperty<>();
	
	private final LoanRepository loanRepository;
	
	private boolean insertionMode = true;
	
	private ObjectProperty<Object[]> warningInfo = new SimpleObjectProperty<>();

	public LoanController() {
		loanRepository = new LoanRepository();
		employeeName.set(EmployeeOn.employee.getName());
		getAllLoans();
	}

	public void addLoan() {
		int result = loanRepository.addLoan(
				loanDate.get(), returnDate.get(), customerRg.get(),
				EmployeeOn.employee.getId(),
				books.stream()
					.map(Book::getIsbn)
					.collect(Collectors.toList()));

		if (result == 1) {
			getAllLoans();
			loanSelected.set(null);
			warningInfo.set(new Object[] { AlertType.INFORMATION, 
					"Empréstimo Registrado", "Empréstimo registrado com sucesso!" });
		} else {
			warningInfo.set(new Object[] { AlertType.INFORMATION, 
					"Erro", 
					"Não foi possível registrar o empréstimo!" });
		}
	}

	public void updateLoan() {
		int result = 0;

		if (result == 1) {
			getAllLoans();
			warningInfo.set(new Object[] { AlertType.INFORMATION, 
					"Empréstimo Atualizado", 
					"Empréstimo atualizado com sucesso!" });
		} else {
			warningInfo.set(new Object[] { AlertType.ERROR, 
					"Empréstimo Não Atuazado",
					"Não foi possível atualizar o empréstimo selecionado!" });
		}
	}

	private void getAllLoans() {
		loans.setAll(loanRepository.getAllLoans());
	}

	public void setLoanSelected(Loan newValue) {
		insertionMode = newValue == null;
		loanSelected.set(newValue);
	}
	
	public ObservableList<Book> getBooks() {
		return books;
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

	public StringProperty getDateReturned() {
		return dateReturned;
	}

	public StringProperty getCustomerName() {
		return customerName;
	}

	public StringProperty getEmployeeName() {
		return employeeName;
	}

	public Property<String> getCustomerRg() {
		return customerRg;
	}
	
	public ObjectProperty<Object[]> getWarningInfo() {
		return warningInfo;
	}

}
