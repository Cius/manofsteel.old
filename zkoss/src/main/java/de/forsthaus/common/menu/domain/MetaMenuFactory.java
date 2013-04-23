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
package de.forsthaus.common.menu.domain;

import java.lang.ref.SoftReference;
import java.net.URISyntaxException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public abstract class MetaMenuFactory implements RootMenuDomainFactory {

	static private SoftReference<RootMenuDomain> referenceRootMenuDomain = new SoftReference<RootMenuDomain>(null);
	private static String menuXMLRootPath = "/";

	static public RootMenuDomain geRootMenuDomain_OldWay() {
		RootMenuDomain rootMenuDomain = referenceRootMenuDomain.get();
		if (rootMenuDomain == null) {
			try {
				Unmarshaller unmarshaller = createUnmarshaller();
				rootMenuDomain = (RootMenuDomain) unmarshaller.unmarshal(MetaMenuFactory.class.getResource(menuXMLRootPath + "mainmenu.xml"));
				referenceRootMenuDomain = new SoftReference<RootMenuDomain>(rootMenuDomain);

				// final Log LOGGER = LogFactory.getLog(MetaMenuFactory.class);
				// if (LOGGER.isDebugEnabled()) {
				// LOGGER.debug("Menu-Metamodel is successfully loaded. ");
				// }

			} catch (JAXBException e) {
				throw new RuntimeException(e);
			}
		}
		return rootMenuDomain;
	}

	public static void main(String[] args) throws URISyntaxException {
		System.out.println(MetaMenuFactory.class.getResource(menuXMLRootPath + "mainmenu.xml"));
	}

	protected static Unmarshaller createUnmarshaller() throws JAXBException {
		return JAXBContext.newInstance(RootMenuDomain.class).createUnmarshaller();
	}

	@Override
	public RootMenuDomain getRootMenuDomain() {
		try {
			return createRootMenuDomain();
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	abstract protected RootMenuDomain createRootMenuDomain() throws JAXBException;

	public MetaMenuFactory() {
	}

}
