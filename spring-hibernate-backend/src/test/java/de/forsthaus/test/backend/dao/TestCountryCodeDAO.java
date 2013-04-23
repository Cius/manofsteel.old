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

import de.forsthaus.backend.dao.CountryCodeDAO;
import de.forsthaus.backend.model.CountryCode;
import de.forsthaus.testutils.BaseHibernateTest;

/**
 * EN: Unit tests for the methods of the articleDAO.<br>
 * DE: Unit Tests fuer die Methoden des articleDAO.<br>
 * 
 * @author Stephan Gerth
 */
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class TestCountryCodeDAO extends BaseHibernateTest {

	@Autowired
	private CountryCodeDAO countryCodeDAO;

	// ########################### Getter/Setter #############################

	public CountryCodeDAO getCountryCodeDAO() {
		return countryCodeDAO;
	}

	public void setCountryCodeDAO(CountryCodeDAO countryCodeDAO) {
		this.countryCodeDAO = countryCodeDAO;
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
	public void getNewCountryCode() {
		CountryCode obj = getCountryCodeDAO().getNewCountryCode();
		Assert.assertEquals("Not the expected result", Long.MIN_VALUE, obj.getId());
	}

	@Test
	@Transactional
	public void getAllCountryCodes() {
		List<CountryCode> result = getCountryCodeDAO().getAllCountryCodes();
		Assert.assertEquals("Not the expected result", 250, result.size());
	}

	@Test
	@Transactional
	public void getCountAllCountryCodes() {
		int result = getCountryCodeDAO().getCountAllCountryCodes();
		Assert.assertEquals("Not the expected result", 250, result);
	}

	@Test
	@Transactional
	public void getCountryCodeById() {
		CountryCode obj = getCountryCodeDAO().getCountryCodeById(new Long(5));
		Assert.assertEquals("Not the expected result", "AD", obj.getCcdCode2());
	}

	@Test
	@Transactional
	public void getCountryCodeByCode2() {
		CountryCode obj = getCountryCodeDAO().getCountryCodeByCode2("DE");
		Assert.assertEquals("Not the expected result", "GERMANY", obj.getCcdName());
	}

	@Test
	@Transactional
	public void saveOrUpdateCountryCode() {
		CountryCode entity = getCountryCodeDAO().getNewCountryCode();
		entity.setCcdCode2("XX");
		entity.setCcdName("testcode");

		getCountryCodeDAO().saveOrUpdate(entity);

		List<CountryCode> list = getCountryCodeDAO().getAllCountryCodes();
		for (CountryCode obj : list) {
			System.out.println(obj.getId() + " / " + obj.getCcdName());
		}

		CountryCode obj2 = getCountryCodeDAO().getCountryCodeByCode2("xx");
		Assert.assertEquals("Not the expected result", "UNROUTABLE ADDRESS", obj2.getCcdName());
	}

	@Test
	@Transactional
	public void deleteCountryCode() {
		CountryCode entity = getCountryCodeDAO().getNewCountryCode();
		entity.setCcdCode2("XX");
		entity.setCcdName("testcode");

		getCountryCodeDAO().saveOrUpdate(entity);

		List<CountryCode> list = getCountryCodeDAO().getAllCountryCodes();
		for (CountryCode obj : list) {
			System.out.println(obj.getId() + " / " + obj.getCcdName());
		}

		CountryCode obj2 = getCountryCodeDAO().getCountryCodeByCode2("xx");
		Assert.assertEquals("Not the expected result", "UNROUTABLE ADDRESS", obj2.getCcdName());

		getCountryCodeDAO().delete(entity);
	}

	@Test
	@Transactional
	public void saveCountryCode() {
		CountryCode entity = getCountryCodeDAO().getNewCountryCode();
		entity.setCcdCode2("XX");
		entity.setCcdName("testcode");

		getCountryCodeDAO().saveOrUpdate(entity);

		List<CountryCode> list = getCountryCodeDAO().getAllCountryCodes();
		for (CountryCode obj : list) {
			System.out.println(obj.getId() + " / " + obj.getCcdName());
		}

		CountryCode obj2 = getCountryCodeDAO().getCountryCodeByCode2("xx");
		Assert.assertEquals("Not the expected result", "UNROUTABLE ADDRESS", obj2.getCcdName());
	}

}
