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

import com.jakequinter.models.Contact;
import com.jakequinter.db.ContactDAO;

/**
 * First of three versions of servlets that print out the employees table. This
 * one has column names and database info hardcoded.
 * <p>
 * From <a href="http://courses.coreservlets.com/Course-Materials/">the
 * coreservlets.com tutorials on servlets, JSP, Struts, JSF, Ajax, GWT, Spring,
 * Hibernate/JPA, and Java programming</a>.
 */

@WebServlet("/")
public class ContactServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected ContactDAO contactDao;
	
	public void init() {
		contactDao = new ContactDAO();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getServletPath();

		try {
			switch (action) {
			case "/contacts":
				contactsList(request, response);
				break;
			case "/insert":
				addContact(request, response);
				break;
			case "/update":
				updateContact(request, response);
				break;
			case "/delete":
				deleteContact(request, response);
				break;
			case "/new":
				showNewForm(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			default:
				home(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	/**
	 * ROUTE: / (default)
	 * shows the contact list
	 */
	private void home(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}
	
	/**
	 * ROUTE: /contacts
	 * shows the contact list
	 */
	private void contactsList(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Contact> contacts = contactDao.getContacts();
		request.setAttribute("contacts", contacts);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/contact-list.jsp");
		dispatcher.forward(request, response);
	}
	
	/**
	 * ROUTE: /new 
	 * shows contact form with empty fields to add new contact
	 */
	private void addContact(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
//		int id = data.getContacts().size() + 1;
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String address = request.getParameter("address");
		Contact newContact = new Contact(firstName, lastName, address);
		contactDao.addContact(newContact);
		response.sendRedirect("contacts");
	}
	
	/**
	 * ROUTE: /update 
	 * update contact
	 */
	private void updateContact(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String address = request.getParameter("address");

		Contact contact = new Contact(id, firstName, lastName, address);
		try {
			contactDao.updateContact(contact);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("catch block update contact");
		}
		response.sendRedirect("contacts");
	}
	
	/**
	 * ROUTE: /delete 
	 * delete contact
	 */
	private void deleteContact(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			contactDao.deleteContact(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("contacts");

	}
	
	/**
	 * shows new form (empty fields)
	 */
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/contact-form.jsp");
		dispatcher.forward(request, response);
	}
	
	/** 
	 * shows edit form (populated fields)
	 */
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Contact existingUser = contactDao.getContact(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/contact-form.jsp");
		request.setAttribute("contact", existingUser);
		dispatcher.forward(request, response);

	}
}
