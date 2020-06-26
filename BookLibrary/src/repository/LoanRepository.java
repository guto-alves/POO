package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Loan;

public class LoanRepository {
	private PreparedStatement insertNewLoan;
	private PreparedStatement selectAllLoans;
	private PreparedStatement updateLoan;
	
	public LoanRepository() {
		try {
			Connection connection = Database.getConnection();
			
			insertNewLoan = connection.prepareStatement(
					"INSERT INTO Loan " +
					"(LoanDate, ReturnDate, CustomerRG, BookISBN, EmployeeId) " + 
					"VALUES (?, ?, ?, ?, ?)");
			
			updateLoan = connection.prepareStatement(
					"UPDATE Loan " + 
					"SET LoanDate = ?, ReturnDate = ?, DateReturned = ?, " + 
					"CustomerRG = ?, BookISBN = ?, EmployeeId = ? " + 
					"WHERE LoanDate = ? AND CustomerRG = ? AND BookISBN = ?");
			
			selectAllLoans = connection.prepareStatement(
					"SELECT LoanDate, ReturnDate, DateReturned, " + 
					"LoanDate, ReturnDate, DateReturned, " +
					"FROM Loan");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int addLoan(String loanDate, String returnDate, 
			String customerRg, int employeeId, List<String> isbns) {
		try {
			for (String isbn : isbns) {
				insertNewLoan.setString(1, loanDate);
				insertNewLoan.setString(2, returnDate);
				insertNewLoan.setString(3, customerRg);
				insertNewLoan.setString(4, isbn);
				insertNewLoan.setInt(5, employeeId);
				insertNewLoan.addBatch();
			}
			
			insertNewLoan.executeBatch();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int updateLoan(String loanDate, String dateReturn, String dateReturned,
			String customerRg, String employeeId, List<String> isbns, Loan currentLoan) {
		try {
			for (String isbn : isbns) {
				updateLoan.setString(1, loanDate);
				updateLoan.setString(2, dateReturn);
				updateLoan.setString(3, dateReturned);
				updateLoan.setString(4, customerRg);
				updateLoan.setString(5, isbn);
				updateLoan.setString(6, employeeId);
				updateLoan.setString(7, currentLoan.getLoanDate());
//				updateLoan.setString(8, currentLoan.getBooks().get);
				updateLoan.addBatch();
			}
			
			updateLoan.executeBatch();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public List<Loan> getAllLoans() {
		try (ResultSet resultSet = selectAllLoans.executeQuery()) {
			List<Loan> loans = new ArrayList<>();
			
			while (resultSet.next()) {
				Loan loan = new Loan(
						resultSet.getString(1), 
						resultSet.getString(2),
						resultSet.getString(3), 
						books,
						customer,
						employee);
			}
			
			return loans;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
