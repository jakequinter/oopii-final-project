package com.jakequinter.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.jakequinter.dao.EmailDAO;
import com.jakequinter.models.Email;

class EmailTest {

	@Test
	void newPhoneNumberTest() {
		Email email = new Email(1, "test", "test");
		
		email.setFkEmailContactId(2);
		email.setEmail("testemail@gmail.com");
		email.setType("work");

		assertEquals(email.getFkEmailContactId(), 2);
		assertEquals(email.getEmail(), "testemail@gmail.com");
		assertEquals(email.getType(), "work");
	}
	
	@Test
	void getEmailByIdTest() {
		EmailDAO emailDao = new EmailDAO();
		Email email = emailDao.getEmail(1, 	1);
		
		assertEquals(email.getEmail(), "defaultemail1@gmail.com");
		assertEquals(email.getType(), "Primary");
		
	}
	
	@Test
	void updateEmailTest() throws Exception {
		EmailDAO emailDao = new EmailDAO();
		Email email = emailDao.getEmail(1, 1);
		email.setEmail("updateemail@gmail.com");
		email.setType("Secondary");
		email.setFkEmailContactId(1);
		
		emailDao.updateEmail(email);
		
		assertEquals(email.getEmail(), "updateemail@gmail.com");
		assertEquals(email.getType(), "Secondary");
		
		email.setEmail("defaultemail1@gmail.com");
		email.setType("Primary");
		email.setFkEmailContactId(1);
		
		emailDao.updateEmail(email);

		assertEquals(email.getFkEmailContactId(), 1);
		assertEquals(email.getEmail(), "defaultemail1@gmail.com");
		assertEquals(email.getType(), "Primary");
	}

}
