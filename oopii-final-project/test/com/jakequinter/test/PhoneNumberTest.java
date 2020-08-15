package com.jakequinter.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.jakequinter.dao.PhoneNumberDAO;
import com.jakequinter.models.PhoneNumber;

class PhoneNumberTest {

	@Test
	void newPhoneNumberTest() {
		PhoneNumber phoneNumber = new PhoneNumber(1, "test", "test");
		
		phoneNumber.setFkPhoneNumberContactId(2);
		phoneNumber.setPhoneNumber("(920)123-4567");
		phoneNumber.setType("mobile");

		assertEquals(phoneNumber.getFkPhoneNumberContactId(), 2);
		assertEquals(phoneNumber.getPhoneNumber(), "(920)123-4567");
		assertEquals(phoneNumber.getType(), "mobile");
	}
	
	@Test
	void getPhoneNumberByIdTest() {
		PhoneNumberDAO phoneNumberDao = new PhoneNumberDAO();
		PhoneNumber phoneNumber = phoneNumberDao.getPhoneNumber(2, 1);
		
		assertEquals(phoneNumber.getPhoneNumber(), "(111)111-1111");
		assertEquals(phoneNumber.getType(), "Mobile");
	}
	
	@Test
	void updatePhoneNumberTest() throws Exception {
		PhoneNumberDAO phoneNumberDao = new PhoneNumberDAO();
		PhoneNumber phoneNumber = phoneNumberDao.getPhoneNumber(2, 1);
		
		phoneNumber.setPhoneNumber("(000)000-0000");
		phoneNumber.setType("Home");
		phoneNumber.setFkPhoneNumberContactId(1);
		
		phoneNumberDao.updatePhoneNumber(phoneNumber);
		
		assertEquals(phoneNumber.getPhoneNumber(), "(000)000-0000");
		assertEquals(phoneNumber.getType(), "Home");
		
		phoneNumber.setPhoneNumber("(111)111-1111");
		phoneNumber.setType("Mobile");
		phoneNumber.setFkPhoneNumberContactId(1);
		
		phoneNumberDao.updatePhoneNumber(phoneNumber);
		
		assertEquals(phoneNumber.getPhoneNumber(), "(111)111-1111");
		assertEquals(phoneNumber.getType(), "Mobile");
		
		
	}

}
