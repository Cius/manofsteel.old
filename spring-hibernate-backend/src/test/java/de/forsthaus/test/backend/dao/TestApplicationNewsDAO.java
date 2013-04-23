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

import de.forsthaus.backend.bean.ResultObject;
import de.forsthaus.backend.dao.ApplicationNewsDAO;
import de.forsthaus.backend.model.ApplicationNews;
import de.forsthaus.testutils.BaseHibernateTest;

/**
 * EN: Unit tests for the methods of the applicationNewsDAO.<br>
 * DE: Unit Tests fuer die Methoden des applicationNewsDAO.<br>
 * 
 * @author Stephan Gerth
 */
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class TestApplicationNewsDAO extends BaseHibernateTest {

	@Autowired
	private ApplicationNewsDAO applicationNewsDAO;

	// ########################### Getter/Setter #############################

	public ApplicationNewsDAO getApplicationNewsDAO() {
		return applicationNewsDAO;
	}

	public void setApplicationNewsDAO(ApplicationNewsDAO applicationNewsDAO) {
		this.applicationNewsDAO = applicationNewsDAO;
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
	public void getAllApplicationNews() {
		List<ApplicationNews> list = getApplicationNewsDAO().getAllApplicationNews();
		Assert.assertTrue("Cannot be less than 0", (list.size() > -1));

	}

	@Test
	@Transactional
	public void getAllApplicationNews_paged() {
		ResultObject result = getApplicationNewsDAO().getAllApplicationNews(0, 5);
		Assert.assertTrue("Cannot be less than 0", (result.getList().size() > -1));

	}

	@Test
	@Transactional
	public void getCountAllApplicationNews() {
		int result = getApplicationNewsDAO().getCountAllApplicationNews();
		Assert.assertEquals("Not the expected result", 115, result);
	}

}
