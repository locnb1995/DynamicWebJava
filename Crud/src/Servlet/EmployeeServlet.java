package Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.*;
import Model.*;

@WebServlet("/")
public class EmployeeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private EmployeeDAO empDAO;

	public void init() {
		empDAO = new EmployeeDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/":
				listUser(request, response);
				break;
			case "/addEmp":
				showAddForm(request, response);
				break;
			case "/insert":
				insertEmp(request, response);
				break;
			case "/editForm":
				showEditForm(request, response);
				break;
			case "/editEmp":
				editEmp(request, response);
				break;
			case "/deleteEmp":
				delEmp(request, response);
				break;
			default:
				listUser(request, response);
				break;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			throw new ServletException(e);
		}
	}

	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Employee> list = empDAO.selectAllUsers();
		request.setAttribute("listEmp", list);
		RequestDispatcher dispatcher = request.getRequestDispatcher("emp-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showAddForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("emp-form.jsp");
		dispatcher.forward(request, response);
	}

	private void insertEmp(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String name = request.getParameter("empname");
		String age = request.getParameter("empage");
		int empage = Integer.parseInt(age);
		String hometown = request.getParameter("emphome");
		empDAO.insertEmp(name, empage, hometown);
		response.sendRedirect("list");
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		Employee emp = empDAO.selectEmpById(id);
		request.setAttribute("emp", emp);
		RequestDispatcher dispatcher = request.getRequestDispatcher("edit-emp-form.jsp");
		dispatcher.forward(request, response);
	}
	
	private void editEmp(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String id = request.getParameter("empid");
		int empid = Integer.parseInt(id);
		String name = request.getParameter("empname");
		String age = request.getParameter("empage");
		int empage = Integer.parseInt(age);
		String hometown = request.getParameter("emphome");
		Employee emp = new Employee(empid, name, empage, hometown);
		empDAO.editEmp(emp);
		response.sendRedirect("list");
	}
	
	private void delEmp(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String id = request.getParameter("id");
		int empid = Integer.parseInt(id);
		empDAO.deleteEmp(empid);
		response.sendRedirect("list");
	}

}
