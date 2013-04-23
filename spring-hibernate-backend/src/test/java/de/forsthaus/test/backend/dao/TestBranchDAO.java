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
import de.forsthaus.backend.dao.BrancheDAO;
import de.forsthaus.backend.model.Branche;
import de.forsthaus.testutils.BaseHibernateTest;

/**
 * EN: Unit tests for the methods of the branchDAO.<br>
 * DE: Unit Tests fuer die Methoden des branchDAO.<br>
 * 
 * @author Stephan Gerth
 */
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class TestBranchDAO extends BaseHibernateTest {

	@Autowired
	private BrancheDAO brancheDAO;

	// ########################### Getter/Setter #############################

	public void setBrancheDAO(BrancheDAO brancheDAO) {
		this.brancheDAO = brancheDAO;
	}

	public BrancheDAO getBrancheDAO() {
		return brancheDAO;
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
	public void getNewBranche() {
		Branche obj = getBrancheDAO().getNewBranche();
		Assert.assertEquals("Not the expected result", Long.MIN_VALUE, obj.getId());
	}

	@Test
	@Transactional
	public void getAllBranches() {
		List<Branche> result = getBrancheDAO().getAllBranches();
		Assert.assertEquals("Not the expected result", 37, result.size());
	}

	@Test
	@Transactional
	public void getCountAllBranches() {
		int result = getBrancheDAO().getCountAllBranches();
		Assert.assertEquals("Not the expected result", 37, result);
	}

	@Test
	@Transactional
	public void getBrancheByID() {
		Branche obj = getBrancheDAO().getBrancheByID(new Long(1000));
		Assert.assertEquals("Not the expected result", "Elektro", obj.getBraBezeichnung());
	}

	@Test
	@Transactional
	public void getBrancheByName() {
		Branche obj = getBrancheDAO().getBrancheByName("Elektro");
		Assert.assertEquals("Not the expected result", 1000, obj.getId());
	}

	@Test
	@Transactional
	public void getBranchesLikeName() {
		List<Branche> result = getBrancheDAO().getBranchesLikeName("Elektro");
		Assert.assertEquals("Not the expected result", 1, result.size());
	}

	@Test
	@Transactional
	public void getBrancheSize() {
		int result = getBrancheDAO().getBrancheSize();
		Assert.assertEquals("Not the expected result", 37, result);
	}

	@Test
	@Transactional
	public void getAllBranches_paged() {
		ResultObject result = getBrancheDAO().getAllBranches(0, 5);
		Assert.assertTrue("Cannot be less than 0", (result.getList().size() > -1));
	}

	@Test
	@Transactional
	public void getAllBranchesLikeText_paged() {
		ResultObject result = getBrancheDAO().getAllBranchesLikeText("Elektro", 0, 5);
		Assert.assertTrue("Cannot be less than 0", (result.getList().size() > -1));
	}

	@Test
	@Transactional
	public void saveOrUpdateBranche() {
		Branche entity = getBrancheDAO().getNewBranche();
		entity.setBraBezeichnung("TEST-BRANCHE");

		getBrancheDAO().saveOrUpdate(entity);

		List<Branche> list = getBrancheDAO().getAllBranches();
		for (Branche obj : list) {
			System.out.println(obj.getId() + " / " + obj.getBraBezeichnung());
		}

		Branche obj2 = getBrancheDAO().getBrancheByName("TEST-BRANCHE");
		Assert.assertEquals("Not the expected result", "TEST-BRANCHE", obj2.getBraBezeichnung());
	}

	@Test
	@Transactional
	public void saveBranche() {
		Branche entity = getBrancheDAO().getNewBranche();
		entity.setBraBezeichnung("TEST-BRANCHE");

		getBrancheDAO().save(entity);

		List<Branche> list = getBrancheDAO().getAllBranches();
		for (Branche obj : list) {
			System.out.println(obj.getId() + " / " + obj.getBraBezeichnung());
		}

		Branche obj2 = getBrancheDAO().getBrancheByName("TEST-BRANCHE");
		Assert.assertEquals("Not the expected result", "TEST-BRANCHE", obj2.getBraBezeichnung());
	}

	@Test
	@Transactional
	public void deleteBranche() {
		Branche entity = getBrancheDAO().getNewBranche();
		entity.setBraBezeichnung("TEST-BRANCHE");

		getBrancheDAO().saveOrUpdate(entity);

		List<Branche> list = getBrancheDAO().getAllBranches();
		for (Branche obj : list) {
			System.out.println(obj.getId() + " / " + obj.getBraBezeichnung());
		}

		Branche obj2 = getBrancheDAO().getBrancheByName("TEST-BRANCHE");
		Assert.assertEquals("Not the expected result", "TEST-BRANCHE", obj2.getBraBezeichnung());

		getBrancheDAO().delete(obj2);
	}

}
