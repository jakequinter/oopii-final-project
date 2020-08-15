package com.jakequinter.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.jakequinter.dao.ContactDAO;
import com.jakequinter.models.Contact;

class ContactTest {
	
	
	@Test
	void newContactTest() {
		Contact contact = new Contact("test", "test");
		contact.setFirstName("first name");
		contact.setLastName("last name");
		
		assertEquals(contact.getFirstName(), "first name");
		assertEquals(contact.getLastName(), "last name");
		
	}
	
	@Test
	void getContactByIdTest() {
		ContactDAO contactDao = new ContactDAO();
		Contact contact = contactDao.getContact(1);
		
		assertEquals(contact.getFirstName(), "Jane");
		assertEquals(contact.getLastName(), "Doe");
		
	}
	
	@Test
	void updateContactTest() throws Exception {
		ContactDAO contactDao = new ContactDAO();
		Contact contact = contactDao.getContact(1);
		contact.setFirstName("update first name");
		contact.setLastName("update last name");
		contact.setContactId(1);
		
		contactDao.updateContact(contact);
		
		assertEquals(contact.getFirstName(), "update first name");
		assertEquals(contact.getLastName(), "update last name");
		
		contact.setFirstName("Jane");
		contact.setLastName("Doe");
		
		contactDao.updateContact(contact);
		
		assertEquals(contact.getFirstName(), "Jane");
		assertEquals(contact.getLastName(), "Doe");
		
	}

}
