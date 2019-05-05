package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.*;

public class EmployeeDAO {

	private String jdbcURL = "jdbc:mysql://localhost:3306/nhanvien?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";

	private static final String SELECT_ALL_EMP = "select * from employee";

	private static final String INSERT_EMP_SQL = "INSERT INTO employee" + "  (name, age, hometown) VALUES "
			+ " (?, ?, ?);";
	
	private static final String SELECT_EMP_BY_ID = "select * from employee where id =?";
	
	private static final String UPDATE_EMP_SQL = "update employee set name = ?, age= ?, hometown =? where id = ?;";
	
	private static final String DELETE_EMP_SQL = "delete from employee where id = ?;";

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public List<Employee> selectAllUsers() throws SQLException {
		List<Employee> list = new ArrayList<Employee>();
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EMP);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			Employee emp = new Employee(rs.getInt("id"), rs.getString("name"), rs.getInt("age"),
					rs.getString("hometown"));
			list.add(emp);
		}
		return list;
	}

	public void insertEmp(String name , int age , String hometown) throws SQLException {
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMP_SQL)) {
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, age);
			preparedStatement.setString(3, hometown);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
	
	public Employee selectEmpById(int id) throws SQLException {
		Employee emp = null;
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMP_BY_ID);
		preparedStatement.setInt(1, id);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			emp = new Employee(rs.getInt("id"), rs.getString("name"), rs.getInt("age"),
					rs.getString("hometown"));
		}
		return emp;
	}
	
	public void editEmp(Employee emp) throws SQLException {
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMP_SQL)) {
			preparedStatement.setString(1, emp.getName());
			preparedStatement.setInt(2, emp.getAge());
			preparedStatement.setString(3, emp.getHometown());
			preparedStatement.setInt(4, emp.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	public void deleteEmp(int id) throws SQLException {
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMP_SQL)) {
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
}
