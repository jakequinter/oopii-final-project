package com.jakequinter.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.jakequinter.dao.AddressDAO;
import com.jakequinter.dao.ContactDAO;
import com.jakequinter.models.Address;
import com.jakequinter.models.Contact;

class AddressTest {

	@Test
	void newAddressTest() {
		Address address = new Address(1, "test", "test", "test", "test", "test");
		
		address.setFkAddressContactId(2);
		address.setAddressLine1("line1");
		address.setAddressLine2("line2");
		address.setCity("city");
		address.setState("state");
		address.setPostalCode("postalcode");
		
		ContactDAO contactDao = new ContactDAO();
		contactDao.getContact(1);
		
		assertEquals(address.getFkAddressContactId(), 2);
		assertEquals(address.getAddressLine1(), "line1");
		assertEquals(address.getAddressLine2(), "line2");
		assertEquals(address.getCity(), "city");
		assertEquals(address.getState(), "state");
		assertEquals(address.getPostalCode(), "postalcode");
	}
	
	@Test
	void getAddresstByIdTest() {
		AddressDAO addressDao = new AddressDAO();
		Address address = addressDao.getAddress(10, 2);
		
		assertEquals(address.getAddressLine1(), "address line 1");
		assertEquals(address.getAddressLine2(), "address line 2");
		assertEquals(address.getCity(), "city");
		assertEquals(address.getState(), "state");
		assertEquals(address.getPostalCode(), "12345");
		
	}
	
	@Test
	void updateContactTest() throws Exception {
		AddressDAO addressDao = new AddressDAO();
		Address address = addressDao.getAddress(10, 2);
		address.setAddressLine1("update address line 1");
		address.setAddressLine2("update address line 2");
		address.setCity("update city");
		address.setState("update state");
		address.setPostalCode("12345-0000");
		address.setFkAddressContactId(2);
		
		addressDao.updateAddress(address);
		
		assertEquals(address.getAddressLine1(), "update address line 1");
		assertEquals(address.getAddressLine2(), "update address line 2");
		assertEquals(address.getCity(), "update city");
		assertEquals(address.getState(), "update state");
		assertEquals(address.getPostalCode(), "12345-0000");
		
		address.setAddressLine1("address line 1");
		address.setAddressLine2("address line 2");
		address.setCity("city");
		address.setState("state");
		address.setPostalCode("12345");
		address.setFkAddressContactId(2);
		
		addressDao.updateAddress(address);
		
		assertEquals(address.getAddressLine1(), "address line 1");
		assertEquals(address.getAddressLine2(), "address line 2");
		assertEquals(address.getCity(), "city");
		assertEquals(address.getState(), "state");
		assertEquals(address.getPostalCode(), "12345");
		
	}

}
