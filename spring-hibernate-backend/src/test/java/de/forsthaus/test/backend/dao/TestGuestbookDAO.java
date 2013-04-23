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

import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import de.forsthaus.backend.dao.GuestBookDAO;
import de.forsthaus.backend.model.GuestBook;
import de.forsthaus.testutils.BaseHibernateTest;

/**
 * EN: Unit tests for the methods of the guestbookDAO.<br>
 * DE: Unit Tests fuer die Methoden des guestbookDAO.<br>
 * 
 * @author Stephan Gerth
 */
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class TestGuestbookDAO extends BaseHibernateTest {

	@Autowired
	private GuestBookDAO guestBookDAO;

	// ########################### Getter/Setter #############################

	public GuestBookDAO getGuestBookDAO() {
		return guestBookDAO;
	}

	public void setGuestBookDAO(GuestBookDAO guestBookDAO) {
		this.guestBookDAO = guestBookDAO;
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
	public void getNewGuestBook() {
		GuestBook obj = getGuestBookDAO().getNewGuestBook();
		Assert.assertEquals("Not the expected result", Long.MIN_VALUE, obj.getId());
	}

	@Test
	@Transactional
	public void getCountAllGuestBooks() {
		int result = getGuestBookDAO().getCountAllGuestBooks();
		Assert.assertEquals("Not the expected result", 0, result);
	}

	@Test
	@Transactional
	public void saveOrUpdateGuestbook() {
		GuestBook entity = getGuestBookDAO().getNewGuestBook();
		entity.setGubSubject("TEST-ENTRY");
		entity.setGubDate(new Date());
		entity.setGubUsrname("testuser");

		getGuestBookDAO().saveOrUpdate(entity);

		GuestBook obj2 = getGuestBookDAO().getGuestBookByID(entity.getId());
		Assert.assertEquals("Not the expected result", "TEST-ENTRY", obj2.getGubSubject());
	}

	@Test
	@Transactional
	public void saveGuestbook() {
		GuestBook entity = getGuestBookDAO().getNewGuestBook();
		entity.setGubSubject("TEST-ENTRY");
		entity.setGubDate(new Date());
		entity.setGubUsrname("testuser");

		getGuestBookDAO().save(entity);

		GuestBook obj2 = getGuestBookDAO().getGuestBookByID(entity.getId());
		Assert.assertEquals("Not the expected result", "TEST-ENTRY", obj2.getGubSubject());
	}

	@Test
	@Transactional
	public void deleteGuestBook() {
		GuestBook entity = getGuestBookDAO().getNewGuestBook();
		entity.setGubSubject("TEST-ENTRY");
		entity.setGubDate(new Date());
		entity.setGubUsrname("testuser");

		getGuestBookDAO().saveOrUpdate(entity);

		GuestBook obj2 = getGuestBookDAO().getGuestBookByID(entity.getId());
		Assert.assertEquals("Not the expected result", "TEST-ENTRY", obj2.getGubSubject());

		getGuestBookDAO().delete(entity);
	}

}
