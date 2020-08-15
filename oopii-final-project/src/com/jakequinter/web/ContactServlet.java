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

import com.jakequinter.dao.AddressDAO;
import com.jakequinter.dao.ContactDAO;
import com.jakequinter.dao.EmailDAO;
import com.jakequinter.dao.PhoneNumberDAO;
import com.jakequinter.models.Address;
import com.jakequinter.models.Contact;
import com.jakequinter.models.Email;
import com.jakequinter.models.PhoneNumber;


@WebServlet("/")
public class ContactServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected ContactDAO contactDao;
	protected AddressDAO addressDao;
	protected PhoneNumberDAO phoneNumberDao;
	protected EmailDAO emailDao;
	
	public void init() {
		contactDao = new ContactDAO();
		addressDao = new AddressDAO();
		phoneNumberDao = new PhoneNumberDAO();
		emailDao = new EmailDAO();
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
			// contact routes
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
			// address routes
			case "/insert-address":
				addAddress(request, response);
				break;
			case "/update-address":
				updateAddress(request, response);
				break;
			case "/delete-address":
				deleteAddress(request, response);
				break;
			case "/new-address":
				showNewAddressForm(request, response);
				break;
			case "/edit-address":
				showEditAddressForm(request, response);
				break;
			// phonenumber routes
			case "/insert-phonenumber":
				addPhoneNumber(request, response);
				break;
			case "/update-phonenumber":
				updatePhoneNumber(request, response);
				break;
			case "/delete-phonenumber":
				deletePhoneNumber(request, response);
				break;
			case "/new-phonenumber":
				showNewPhoneNumberForm(request, response);
				break;
			case "/edit-phonenumber":
				showEditPhoneNumberForm(request, response);
				break;
			// email routes
			case "/insert-email":
				addEmail(request, response);
				break;
			case "/update-email":
				updateEmail(request, response);
				break;
			case "/delete-email":
				deleteEmail(request, response);
				break;
			case "/new-email":
				showNewEmailForm(request, response);
				break;
			case "/edit-email":
				showEditEmailForm(request, response);
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
	
	// =============================================================================================================================================================================================
	// contact routes
	// =============================================================================================================================================================================================
	
	/**
	 * ROUTE: /contacts
	 * shows the contact list
	 */
	private void contactsList(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Contact> contacts = contactDao.getContacts();
		
		request.setAttribute("contacts", contacts);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/contact-list.jsp");
		dispatcher.forward(request, response);
	}
	
	/**
	 * ROUTE: /new 
	 * shows contact form with empty fields to add new contact
	 */
	private void addContact(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		
		Contact newContact = new Contact(firstName, lastName);
		contactDao.addContact(newContact);
		response.sendRedirect("contacts");
	}
	
	/**
	 * ROUTE: /update 
	 * update contact
	 */
	private void updateContact(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int contactId = Integer.parseInt(request.getParameter("contactId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");

		Contact contact = new Contact(contactId, firstName, lastName);
		
		try {
			contactDao.updateContact(contact);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ContactServlet: catch block updateContact");
		}
		response.sendRedirect("contacts");
	}
	
	/**
	 * ROUTE: /delete 
	 * delete contact
	 */
	private void deleteContact(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int contactId = Integer.parseInt(request.getParameter("contactId"));
		try {
			contactDao.deleteContact(contactId);
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("/contact-form.jsp");
		dispatcher.forward(request, response);
	}
	
	/** 
	 * shows edit form (populated fields)
	 */
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int contactId = Integer.parseInt(request.getParameter("contactId"));
		
		Contact existingContact = contactDao.getContact(contactId);
		List<Address> addresses = addressDao.getContactAddresses(contactId);
		List<PhoneNumber> phoneNumbers = phoneNumberDao.getContactPhoneNumbers(contactId);
		List<Email> emails = emailDao.getContactEmails(contactId);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/contact-form.jsp");
		request.setAttribute("contact", existingContact);
		request.setAttribute("addresses", addresses);
		request.setAttribute("phoneNumbers", phoneNumbers);
		request.setAttribute("emails",  emails);
		dispatcher.forward(request, response);

	}
	
	// =============================================================================================================================================================================================
	// address routes
	// =============================================================================================================================================================================================
	
	/**
	 * ROUTE: /insert-address 
	 * inserts new address into address table and assigns it to current contact 
	 */
	private void addAddress(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int contactId = Integer.parseInt(request.getParameter("contactId"));
		String addressLine1 = request.getParameter("addressLine1");
		String addressLine2 = request.getParameter("addressLine2");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String postalCode = request.getParameter("postalCode");
		Address newAddress = new Address(contactId, addressLine1, addressLine2, city, state, postalCode);
		addressDao.addAddress(newAddress);
		response.sendRedirect("edit?contactId=" + contactId);
	}
	
	/**
	 * ROUTE: /update-address
	 * updates address
	 */
	private void updateAddress(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int addressId = Integer.parseInt(request.getParameter("addressId"));
		int fkAddressContactId = Integer.parseInt(request.getParameter("fkAddressContactId"));
		String addressLine1 = request.getParameter("addressLine1");
		String addressLine2 = request.getParameter("addressLine2");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String postalCode = request.getParameter("postalCode");


		Address address = new Address(addressId, fkAddressContactId, addressLine1, addressLine2, city, state, postalCode);
		try {
			addressDao.updateAddress(address);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("catch block update contact");
		}
		response.sendRedirect("edit?contactId=" + fkAddressContactId);
	}
	
	/**
	 * ROUTE: /delete-address 
	 * delete an address
	 */
	private void deleteAddress(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int addressId = Integer.parseInt(request.getParameter("addressId"));
		int contactId = Integer.parseInt(request.getParameter("contactId"));
		try {
			addressDao.deleteAddress(addressId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("edit?contactId=" + contactId);

	}
	
	/**
	 * shows new address form (empty fields)
	 */
	private void showNewAddressForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int contactId = Integer.parseInt(request.getParameter("contactId"));
		Contact existingContact = contactDao.getContact(contactId);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/new-address-form.jsp");
		request.setAttribute("contact", existingContact);
		dispatcher.forward(request, response);
	}
	
	/** 
	 * shows edit address form (populated fields)
	 */
	private void showEditAddressForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int addressId = Integer.parseInt(request.getParameter("addressId"));
		int fkAddressContactId = Integer.parseInt(request.getParameter("fkAddressContactId"));
		Contact existingContact = contactDao.getContact(fkAddressContactId);
		
		Address existingAddress = addressDao.getAddress(addressId, fkAddressContactId);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/edit-address-form.jsp");
		request.setAttribute("address", existingAddress);
		request.setAttribute("contact", existingContact);
		dispatcher.forward(request, response);

	}
	
	
	// =============================================================================================================================================================================================
	// phonenumber routes
	// =============================================================================================================================================================================================
	
	/**
	 * ROUTE: /insert-phonenumber
	 * inserts new phonenumber into phonenumber table and assigns it to current contact 
	 */
	private void addPhoneNumber(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int contactId = Integer.parseInt(request.getParameter("contactId"));
		String phoneNumber = request.getParameter("phoneNumber");
		String type = request.getParameter("type");
		
		PhoneNumber newPhoneNumber = new PhoneNumber(contactId, phoneNumber, type);
		phoneNumberDao.addPhoneNumber(newPhoneNumber);
		response.sendRedirect("edit?contactId=" + contactId);
	}
	
	/**
	 * ROUTE: /update-phonenumber
	 * updates phonenumber
	 */
	private void updatePhoneNumber(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int phoneNumberId = Integer.parseInt(request.getParameter("phoneNumberId"));
		int fkPhoneNumberContactId = Integer.parseInt(request.getParameter("fkPhoneNumberContactId"));
		String number = request.getParameter("phoneNumber");
		String type = request.getParameter("type");


		PhoneNumber phoneNumber = new PhoneNumber(phoneNumberId, fkPhoneNumberContactId, number, type);
		try {
			phoneNumberDao.updatePhoneNumber(phoneNumber);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("catch block update contact");
		}
		response.sendRedirect("edit?contactId=" + fkPhoneNumberContactId);
	}
	
	/**
	 * ROUTE: /delete-phonenumber 
	 * delete a phonenumber
	 */
	private void deletePhoneNumber(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int phoneNumberId = Integer.parseInt(request.getParameter("phoneNumberId"));
		int contactId = Integer.parseInt(request.getParameter("contactId"));
		try {
			phoneNumberDao.deletePhoneNumber(phoneNumberId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("edit?contactId=" + contactId);

	}
	
	/**
	 * shows new phonenumber form (empty fields)
	 */
	private void showNewPhoneNumberForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int contactId = Integer.parseInt(request.getParameter("contactId"));
		Contact existingContact = contactDao.getContact(contactId);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/new-phonenumber-form.jsp");
		request.setAttribute("contact", existingContact);
		dispatcher.forward(request, response);
	}
	
	/** 
	 * shows edit phonenumber form (populated fields)
	 */
	private void showEditPhoneNumberForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int phoneNumberId = Integer.parseInt(request.getParameter("phoneNumberId"));
		int fkPhoneNumberContactId = Integer.parseInt(request.getParameter("fkPhoneNumberContactId"));
		Contact existingContact = contactDao.getContact(fkPhoneNumberContactId);
		
		PhoneNumber existingPhoneNumber = phoneNumberDao.getPhoneNumber(phoneNumberId, fkPhoneNumberContactId);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/edit-phonenumber-form.jsp");
		request.setAttribute("contact",  existingContact);
		request.setAttribute("phoneNumber", existingPhoneNumber);
		dispatcher.forward(request, response);

	}
	
	// =============================================================================================================================================================================================
	// email routes
	// =============================================================================================================================================================================================
	
	/**
	 * ROUTE: /insert-email
	 * inserts new email into email table and assigns it to current contact 
	 */
	private void addEmail(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int contactId = Integer.parseInt(request.getParameter("contactId"));
		String email = request.getParameter("email");
		String type = request.getParameter("type");
		
		Email newEmail = new Email(contactId, email, type);
		emailDao.addEmail(newEmail);
		response.sendRedirect("edit?contactId=" + contactId);
	}
	
	/**
	 * ROUTE: /update-email
	 * updates email
	 */
	private void updateEmail(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int emailId = Integer.parseInt(request.getParameter("emailId"));
		int fkEmailContactId = Integer.parseInt(request.getParameter("fkEmailContactId"));
		String e_mail = request.getParameter("email");
		String type = request.getParameter("type");


		Email email = new Email(emailId, fkEmailContactId, e_mail, type);
		try {
			emailDao.updateEmail(email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("catch block update contact");
		}
		response.sendRedirect("edit?contactId=" + fkEmailContactId);
	}
	
	/**
	 * ROUTE: /delete-email 
	 * delete an email
	 */
	private void deleteEmail(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int emailId = Integer.parseInt(request.getParameter("emailId"));
		int contactId = Integer.parseInt(request.getParameter("contactId"));
		try {
			emailDao.deleteEmail(emailId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("edit?contactId=" + contactId);

	}
	
	/**
	 * shows new email form (empty fields)
	 */
	private void showNewEmailForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int contactId = Integer.parseInt(request.getParameter("contactId"));
		Contact existingContact = contactDao.getContact(contactId);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/new-email-form.jsp");
		request.setAttribute("contact", existingContact);
		dispatcher.forward(request, response);
	}
	
	/** 
	 * shows edit email form (populated fields)
	 */
	private void showEditEmailForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int emailId = Integer.parseInt(request.getParameter("emailId"));
		int fkEmailContactId = Integer.parseInt(request.getParameter("fkEmailContactId"));
		Contact existingContact = contactDao.getContact(fkEmailContactId);
		
		Email existingEmail = emailDao.getEmail(emailId, fkEmailContactId);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/edit-email-form.jsp");
		request.setAttribute("contact",  existingContact);
		request.setAttribute("email", existingEmail);
		dispatcher.forward(request, response);

	}
}
