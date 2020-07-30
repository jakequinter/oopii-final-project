package com.jakequinter.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jakequinter.models.Employee;
import com.jakequinter.db.EmployeeData;

/**
 * First of three versions of servlets that print out the employees table. This
 * one has column names and database info hardcoded.
 * <p>
 * From <a href="http://courses.coreservlets.com/Course-Materials/">the
 * coreservlets.com tutorials on servlets, JSP, Struts, JSF, Ajax, GWT, Spring,
 * Hibernate/JPA, and Java programming</a>.
 */

@WebServlet("/")
public class EmployeeController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected EmployeeData data;
	
	@Override
	public void init() throws ServletException {
		data = new EmployeeData("oopFinalProject", "employees2", "jakequinter", "password");
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		String action = request.getServletPath();
//
//		try {
//			switch (action) {
//			case "/new":
//				showNewForm(request, response);
//				break;
//			case "/insert":
////				insertUser(request, response);
//				break;
////			case "/delete":
////				deleteUser(request, response);
////				break;
//			case "/edit":
//				showEditForm(request, response);
//				break;
////			case "/update":
////				updateUser(request, response);
////				break;
//			default:
//				listUser(request, response);
//				break;
//			}
//		} catch (SQLException ex) {
//			throw new ServletException(ex);
//		}
		String page = "";
		String id = request.getParameter("id");
		
		if (id == null || id.isEmpty()) {
			page = "/WEB-INF/employeeList.jsp";
			List<Employee> employees = data.getEmployees();
			request.getSession().setAttribute("employees", employees);
		} else {
			page = "/WEB-INF/employeeDetail.jsp";
			Employee employee = data.getEmployee(id);
			request.getSession().setAttribute("employee", employee);			
		}
			
		request.getRequestDispatcher(page).forward(request, response);
	}
}
	
//	private void listUser(HttpServletRequest request, HttpServletResponse response)
//			throws SQLException, IOException, ServletException {
//		List<Employee> employee = data.getEmployees();
//		request.setAttribute("employee", employee);
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/employeeList.jsp");
//		dispatcher.forward(request, response);
//	}
	
//	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/user-form.jsp");
//		dispatcher.forward(request, response);
//	}
//	
//	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
//			throws SQLException, ServletException, IOException {
//		int id = Integer.parseInt(request.getParameter("id"));
//		Employee existingUser = data.getEmployee(id);
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/employeeDetail.jsp");
//		request.setAttribute("user", existingUser);
//		dispatcher.forward(request, response);
//
//	}
//}