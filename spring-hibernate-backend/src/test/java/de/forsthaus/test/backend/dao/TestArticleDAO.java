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

import java.math.BigDecimal;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import de.forsthaus.backend.dao.ArticleDAO;
import de.forsthaus.backend.model.Article;
import de.forsthaus.testutils.BaseHibernateTest;

/**
 * EN: Unit tests for the methods of the articleDAO.<br>
 * DE: Unit Tests fuer die Methoden des articleDAO.<br>
 * 
 * @author Stephan Gerth
 */
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class TestArticleDAO extends BaseHibernateTest {

	@Autowired
	private ArticleDAO articleDAO;

	// ########################### Getter/Setter #############################

	public ArticleDAO getArticleDAO() {
		return articleDAO;
	}

	public void setArticleDAO(ArticleDAO articleDAO) {
		this.articleDAO = articleDAO;
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
	public void getNewArticle() {
		Article obj = getArticleDAO().getNewArticle();
		Assert.assertEquals("Not the expected result", Long.MIN_VALUE, obj.getId());
	}

	@Test
	@Transactional
	public void getAllArticles() {
		List<Article> result = getArticleDAO().getAllArticles();
		Assert.assertEquals("Not the expected result", 13, result.size());
	}

	@Test
	@Transactional
	public void getCountAllArticles() {
		int result = getArticleDAO().getCountAllArticles();
		Assert.assertEquals("Not the expected result", 13, result);
	}

	@Test
	@Transactional
	public void getArticleById() {
		Article obj = getArticleDAO().getArticleById(new Long(3000));
		Assert.assertEquals("Not the expected result", "Kabelverschraubung DN 27", obj.getArtKurzbezeichnung());
	}

	@Test
	@Transactional
	public void getArticlesLikeArticleNumber() {
		List<Article> result = getArticleDAO().getArticlesLikeArticleNumber("KS");
		Assert.assertEquals("Not the expected result", 4, result.size());
	}

	@Test
	@Transactional
	public void getArticlesLikeName() {
		List<Article> result = getArticleDAO().getArticlesLikeName("Kabelverschraubung");
		Assert.assertEquals("Not the expected result", 4, result.size());
	}

	@Test
	@Transactional
	public void saveOrUpdateArticle() {
		Article entity = getArticleDAO().getNewArticle();
		entity.setId(new Long(1111111));
		entity.setArtKurzbezeichnung("TEST-ARTICLE");
		entity.setArtNr("4711");
		entity.setArtPreis(new BigDecimal("123.45"));

		getArticleDAO().saveOrUpdate(entity);

		Article article2 = getArticleDAO().getArticleById(new Long(1111111));
		Assert.assertEquals("Not the expected result", "TEST-ARTICLE", article2.getArtKurzbezeichnung());
	}

	@Test
	@Transactional
	public void saveArticle() {
		Article entity = getArticleDAO().getNewArticle();
		entity.setArtKurzbezeichnung("TEST-ARTICLE2");
		entity.setArtNr("4712");
		entity.setArtPreis(new BigDecimal("123.45"));

		getArticleDAO().save(entity);

		List<Article> list = getArticleDAO().getAllArticles();
		for (Article article : list) {
			System.out.println(article.getId() + " / " + article.getArtKurzbezeichnung() + " / " + article.getArtNr());
		}

		Article article2 = getArticleDAO().getArticleByNumber("4712");
		Assert.assertEquals("Not the expected result", "TEST-ARTICLE2", article2.getArtKurzbezeichnung());
	}

	@Test
	@Transactional
	public void deleteArticle() {
		Article entity = getArticleDAO().getNewArticle();
		entity.setArtKurzbezeichnung("TEST-ARTICLE2");
		entity.setArtNr("4712");
		entity.setArtPreis(new BigDecimal("123.45"));

		getArticleDAO().save(entity);

		List<Article> list = getArticleDAO().getAllArticles();
		for (Article article : list) {
			System.out.println(article.getId() + " / " + article.getArtKurzbezeichnung() + " / " + article.getArtNr());
		}

		Article article2 = getArticleDAO().getArticleByNumber("4712");
		Assert.assertEquals("Not the expected result", "TEST-ARTICLE2", article2.getArtKurzbezeichnung());

		getArticleDAO().delete(entity);
	}

}
