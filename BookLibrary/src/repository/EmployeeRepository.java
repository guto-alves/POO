package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Employee;
import model.Role;

public class EmployeeRepository {
	private PreparedStatement insertEmployee;
	private PreparedStatement selectAllEmployees;
	private PreparedStatement updateEmployee;
	private PreparedStatement deleteEmployee;
	
	public EmployeeRepository() {
		try {
			Connection connection = Database.getConnection();
			insertEmployee = connection.prepareStatement(
					"INSERT INTO Employee " +
					"(Name, Phone, Role, Email, Password) " +
					"VALUES (?, ?, ?, ?, ?)");
			
			updateEmployee = connection.prepareStatement(
					"UPDATE Employee " +
					"SET Name = ?, Phone = ?, Role = ?, Email = ?, " + 
							"Password = ? " +
					"WHERE ID = ?");
			
			deleteEmployee = connection.prepareStatement(
					"DELETE FROM Employee " + 
					"WHERE ID = ?");
			
			selectAllEmployees = connection.prepareStatement(
					"SELECT * FROM Employee");
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public int addEmployee(String name, String phone, String role, 
			String email, String password) {
		try {
			insertEmployee.setString(1, name);
			insertEmployee.setString(2, phone);
			insertEmployee.setString(3, role);
			insertEmployee.setString(4, email);
			insertEmployee.setString(5, password);
			return insertEmployee.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int updateEmployee(String name, String phone, String role,
			String email, String password, int id) {
		try {
			updateEmployee.setString(1, name);
			updateEmployee.setString(2, phone);
			updateEmployee.setString(3, role);
			updateEmployee.setString(4, email);
			updateEmployee.setString(5, password);
			updateEmployee.setInt(6, id);
			return updateEmployee.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int deleteEmployee(int id) {
		try {
			deleteEmployee.setInt(1, id);
			return deleteEmployee.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public List<Employee> getAllEmployees() {
		try(ResultSet resultSet = selectAllEmployees.executeQuery()) {
			List<Employee> employees = new ArrayList<>();
			
			while(resultSet.next()) {
				employees.add(new Employee(
						resultSet.getInt(1),
						resultSet.getString(2),
						resultSet.getString(3),
						Role.valueOf(resultSet.getString(4)),
						resultSet.getString(5),
						resultSet.getString(6)));
			}
			
			return employees;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null ;
	}
}
