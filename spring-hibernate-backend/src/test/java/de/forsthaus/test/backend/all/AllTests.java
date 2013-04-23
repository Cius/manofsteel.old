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
package de.forsthaus.test.backend.all;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import de.forsthaus.test.backend.dao.TestApplicationNewsDAO;
import de.forsthaus.test.backend.dao.TestArticleDAO;
import de.forsthaus.test.backend.dao.TestBranchDAO;
import de.forsthaus.test.backend.dao.TestCountryCodeDAO;
import de.forsthaus.test.backend.dao.TestCustomerDAO;
import de.forsthaus.test.backend.dao.TestGuestbookDAO;

/**
 * @author bbruhns
 * @author Stephan Gerth
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({ TestApplicationNewsDAO.class, TestArticleDAO.class, TestBranchDAO.class, TestCountryCodeDAO.class, TestCustomerDAO.class, TestGuestbookDAO.class })
public class AllTests {

}
