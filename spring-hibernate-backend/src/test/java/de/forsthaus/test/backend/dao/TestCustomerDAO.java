/**
 * Copyright 2010 the original author or authors.
 * 
 * This file is part of Zksample2. http://zksample2.sourceforge.net/
 *
 * Zksample2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Zksample2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Zksample2.  If not, see <http://www.gnu.org/licenses/gpl.html>.
 */
package de.forsthaus.test.backend.dao;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import de.forsthaus.backend.dao.BrancheDAO;
import de.forsthaus.backend.dao.CustomerDAO;
import de.forsthaus.backend.dao.OfficeDAO;
import de.forsthaus.backend.dao.OrderDAO;
import de.forsthaus.backend.model.Branche;
import de.forsthaus.backend.model.Customer;
import de.forsthaus.backend.model.Order;
import de.forsthaus.testutils.BaseHibernateTest;

/**
 * EN: Unit tests for the methods of the customerDAO.<br>
 * DE: Unit Tests fuer die Methoden des customerDAO.<br>
 * 
 * @author Stephan Gerth
 */
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class TestCustomerDAO extends BaseHibernateTest {

	@Autowired
	private CustomerDAO customerDAO;
	@Autowired
	private OrderDAO orderDAO;
	@Autowired
	private BrancheDAO brancheDAO;
	@Autowired
	private OfficeDAO officeDAO;

	// ########################### Getter/Setter #############################

	public OfficeDAO getOfficeDAO() {
		return officeDAO;
	}

	public void setOfficeDAO(OfficeDAO officeDAO) {
		this.officeDAO = officeDAO;
	}

	public BrancheDAO getBrancheDAO() {
		return brancheDAO;
	}

	public void setBrancheDAO(BrancheDAO brancheDAO) {
		this.brancheDAO = brancheDAO;
	}

	public OrderDAO getOrderDAO() {
		return orderDAO;
	}

	public void setOrderDAO(OrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}

	public CustomerDAO getCustomerDAO() {
		return customerDAO;
	}

	public void setCustomerDAO(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

	// ############################ Init / Clean #############################

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	// ############################## Tests ##################################

	@Test
	@Transactional
	public void getNewCustomer() {
		Customer obj = getCustomerDAO().getNewCustomer();
		Assert.assertEquals("Not the expected result", Long.MIN_VALUE, obj.getId());
	}

	@Test
	@Transactional
	public void getAllCustomers() {
		List<Customer> result = getCustomerDAO().getAllCustomers();
		Assert.assertEquals("Not the expected result", 27, result.size());
	}

	@Test
	@Transactional
	public void getAllCustomers_paged() {
		List<Customer> result = getCustomerDAO().getAllCustomers(0, 5, "kunName1", true);
		Assert.assertEquals("Cannot be less than 0", 5, result.size());
	}

	@Test
	@Transactional
	public void getCountAllCustomers() {
		int result = getCustomerDAO().getCountAllCustomers();
		Assert.assertEquals("Not the expected result", 27, result);

	}

	@Test
	@Transactional
	public void getCustomerByID() {
		Customer obj = getCustomerDAO().getCustomerByID(new Long(20));
		Assert.assertEquals("Not the expected result", "MUELLER", obj.getKunMatchcode());
	}

	@Test
	@Transactional
	public void getCustomerByKunNr() {
		Customer obj = getCustomerDAO().getCustomerByKunNr("20");
		Assert.assertEquals("Not the expected result", "MUELLER", obj.getKunMatchcode());
	}

	@Test
	@Transactional
	public void getCustomersLikeMatchcode() {
		List<Customer> result = getCustomerDAO().getCustomersLikeMatchcode("MU");
		Assert.assertEquals("Not the expected result", 1, result.size());
	}

	@Test
	@Transactional
	public void getCustomersLikeOrt() {
		List<Customer> result = getCustomerDAO().getCustomersLikeOrt("Berlin");
		Assert.assertEquals("Not the expected result", 3, result.size());
	}

	@Test
	@Transactional
	public void getCustomersLikeName1() {
		List<Customer> result = getCustomerDAO().getCustomersLikeName1("Bader GmbH");
		Assert.assertEquals("Not the expected result", 1, result.size());
	}

	@Test
	@Transactional
	public void getCustomersLikeName2() {
		List<Customer> result = getCustomerDAO().getCustomersLikeName2("Elektroinstallationen");
		Assert.assertEquals("Not the expected result", 13, result.size());
	}

	@Test
	@Transactional
	public void getCustomersByOfficeId() {
		List<Customer> result = getCustomerDAO().getCustomersByOfficeId(new Long(1));
		Assert.assertEquals("Not the expected result", 27, result.size());
	}

	@Test
	@Transactional
	public void getCustomerByOrder() {
		Order anOrder = getOrderDAO().getOrderById(new Long(40));
		Customer result = getCustomerDAO().getCustomerByOrder(anOrder);
		Assert.assertEquals("Not the expected result", "MUELLER", result.getKunMatchcode());
	}

	@Test
	@Transactional
	public void getCustomersByBranche() {
		Branche aBranche = getBrancheDAO().getBrancheByID(new Long(1000));
		List<Customer> result = getCustomerDAO().getCustomersByBranche(aBranche);
		Assert.assertEquals("Not the expected result", 15, result.size());
	}

	@Test
	@Transactional
	public void saveCustomer() {
		Customer entity = getCustomerDAO().getNewCustomer();
		entity.setOffice(getOfficeDAO().getOfficeById(new Long(1)));
		entity.setKunMatchcode("TEST-CUSTOMER");
		entity.setKunNr("TEST-123456");

		getCustomerDAO().save(entity);

		List<Customer> list = getCustomerDAO().getAllCustomers();
		for (Customer obj : list) {
			System.out.println(obj.getId() + " / " + obj.getKunMatchcode());
		}

		Customer obj2 = getCustomerDAO().getCustomerByKunNr("TEST-123456");
		Assert.assertEquals("Not the expected result", "TEST-CUSTOMER", obj2.getKunMatchcode());
	}

	@Test
	@Transactional
	public void deleteCustomer() {
		Customer entity = getCustomerDAO().getNewCustomer();
		entity.setOffice(getOfficeDAO().getOfficeById(new Long(1)));
		entity.setKunMatchcode("TEST-CUSTOMER");
		entity.setKunNr("TEST-123456");

		getCustomerDAO().save(entity);

		long id = entity.getId();

		List<Customer> list = getCustomerDAO().getAllCustomers();
		for (Customer obj : list) {
			System.out.println(obj.getId() + " / " + obj.getKunMatchcode());
		}

		Customer obj2 = getCustomerDAO().getCustomerByKunNr("TEST-123456");
		Assert.assertEquals("Not the expected result", "TEST-CUSTOMER", obj2.getKunMatchcode());

		getCustomerDAO().delete(obj2);
	}

	@Test
	@Transactional
	public void getMaxCustomerId() {
		int result = getCustomerDAO().getMaxCustomerId();
		Assert.assertEquals("Not the expected result", 2010, result);
	}

}
