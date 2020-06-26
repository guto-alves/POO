package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Address;
import model.Customer;

public class CustomerRepository {
	private PreparedStatement insertNewCustomer;
	private PreparedStatement updateCustomer;
	private PreparedStatement deleteCustomer;
	private PreparedStatement selectAllCustomers;
	
	public CustomerRepository() {
		try {
			Connection connection = Database.getConnection();
			
			insertNewCustomer = connection.prepareStatement(
					"INSERT INTO Customer " +
					"(RG, CPF, Name, Email, AddressId) " +
					"VALUES (?, ?, ?, ?, ?)");
			
			updateCustomer = connection.prepareStatement(
					"UPDATE Customer " +
					"SET RG = ?, CPF = ?, Name = ?, Email = ?, AddressId = ? " +
					"WHERE RG = ?");
			
			deleteCustomer = connection.prepareStatement(
					"DELETE FROM Customer " +
					"WHERE RG = ?");
			
			selectAllCustomers = connection.prepareStatement(
					"SELECT Customer.RG, Customer.CPF, Customer.Name, Customer.Email, " +
					"Address.ID, Address.Street, Address.Number, Address.Complement, Address.PostalCode " + 
					"FROM Customer INNER JOIN Address " +
						"ON Customer.AddressID = Address.ID ");
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public int addCustomer(String rg, String cpf, String name,
			String email, int addressId) {
		try {
			insertNewCustomer.setString(1, rg);
			insertNewCustomer.setString(2, cpf);
			insertNewCustomer.setString(3, name);
			insertNewCustomer.setString(4, email);
			insertNewCustomer.setInt(5, addressId);
			
			return insertNewCustomer.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int updateCustomer(String rg, String cpf, String name, String email, 
			String currentRg) {
		try {
			updateCustomer.setString(1, rg);
			updateCustomer.setString(2, cpf);
			updateCustomer.setString(3, name);
			updateCustomer.setString(4, email);
			updateCustomer.setString(5, rg);
			updateCustomer.setString(6, currentRg);
			return updateCustomer.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int deleteCustomer(String rg) {
		try {
			deleteCustomer.setString(1, rg);
			return deleteCustomer.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public List<Customer> getAllCustomers() {
		try (ResultSet resultSet = selectAllCustomers.executeQuery()) {
			List<Customer> customers = new ArrayList<>();
		
			while (resultSet.next()) {
				Customer customer = new Customer(
						resultSet.getString(1),
						resultSet.getString(2),
						resultSet.getString(3),
						resultSet.getString(4));
				
				Address address = new Address(
						resultSet.getInt(5), 
						resultSet.getString(6), 
						resultSet.getInt(7),
						resultSet.getString(8),
						resultSet.getString(9));
				
				customer.setAddress(address);
				customers.add(customer);
			}
			
			return customers;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
