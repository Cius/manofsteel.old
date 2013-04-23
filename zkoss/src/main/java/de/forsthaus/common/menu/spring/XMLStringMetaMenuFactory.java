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
package de.forsthaus.common.menu.spring;

import java.io.StringReader;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import de.forsthaus.common.menu.domain.MetaMenuFactory;
import de.forsthaus.common.menu.domain.RootMenuDomain;

public class XMLStringMetaMenuFactory extends MetaMenuFactory {
	public XMLStringMetaMenuFactory() {
	}

	private String xmlString;

	public void setXmlString(String xmlString) {
		this.xmlString = xmlString;
	}

	@Override
	protected RootMenuDomain createRootMenuDomain() throws JAXBException {
		Unmarshaller unmarshaller = createUnmarshaller();
		return (RootMenuDomain) unmarshaller.unmarshal(new StringReader(xmlString));
	}
}
