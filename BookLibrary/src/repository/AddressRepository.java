package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Address;

public class AddressRepository {
	private PreparedStatement insertAddress;
	private PreparedStatement selectAllAddresses;
	private PreparedStatement updateAddress;
	private PreparedStatement deleteAddress;
	
	public AddressRepository() {
		try {
			Connection connection = Database.getConnection();
			insertAddress = connection.prepareStatement(
					"INSERT INTO Address " +
					"(Street, Number, Complement, PostalCode) " +
					"VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			
			updateAddress = connection.prepareStatement(
					"UPDATE Address " +
					"SET Street = ?, Number = ?, Complement = ?, PostalCode = ? " +
					"WHERE ID = ?");
			
			deleteAddress = connection.prepareStatement(
					"DELETE FROM Address " + 
					"WHERE ID = ?");
			
			selectAllAddresses = connection.prepareStatement(
					"SELECT * FROM Address " +
					"WHERE ID = ?");
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public int addAddress(String street, String number, String complement, 
			String postalCode) {
		try {
			insertAddress.setString(1, street);
			insertAddress.setString(2, number);
			insertAddress.setString(3, complement);
			insertAddress.setString(4, postalCode);
			
			insertAddress.executeUpdate();
			
			ResultSet keysResultSet = insertAddress.getGeneratedKeys();
			
			keysResultSet.next();
			
			return keysResultSet.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int updateAddress(String street, String number, String complement, 
			String postalCode, int id) {
		try {
			updateAddress.setString(1, street);
			updateAddress.setString(2, number);
			updateAddress.setString(3, complement);
			updateAddress.setString(4, postalCode);
			updateAddress.setInt(5, id);
			return updateAddress.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int deleteAddress(String id) {
		try {
			deleteAddress.setString(1, id);
			return deleteAddress.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public List<Address> getAllAddresses() {
		try(ResultSet resultSet = selectAllAddresses.executeQuery()) {
			List<Address> address = new ArrayList<Address>();
			
			while(resultSet.next()) {
				address.add(new Address(
						resultSet.getInt(1),
						resultSet.getString(2),
						resultSet.getInt(3),
						resultSet.getString(4),
						resultSet.getString(5)));
			}
			
			return address;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
