package de.forsthaus.backend.nonwebrequestdbservice;

import org.springframework.orm.hibernate3.HibernateOperations;

import de.forsthaus.backend.model.SecUser;

/**
 * EN: Class for running database actions outside the spring AOP configured
 * transaction management of web requests. Othersides we became an error from
 * the loggingService who logs the performance of each database dependend web
 * request. The Transaction management is package name oriented so these class
 * is in an extra package.<br>
 * <br>
 * resetAdminPassword() is called from a scheduler who is started at application
 * starts.<br>
 * <br>
 * see: zkoss frontend sub project.<br>
 * de.forsthaus.InitApplicationWorkspace.java<br>
 * de.forsthaus.CleanDemoDataParsingJob.java<br>
 * <br>
 * DE: Diese Klasse erlaubt den Zugriff auf Datenbank Aktionen ausserhalb des
 * konfigurierten Transaction Managements durch spring AOP. Ansonsten gaebe es
 * Fehlermeldungen durch den ServiceLogger der jeden Datenbank Request
 * mitprotokolliert. Da das Transaktionsmanagement Package Namen orientiert
 * konfiguriert ist, liegt diese Klasse in einem gesonderten Package. <br>
 * resetAdminPassword() wird aus einem Scheduler aufgerufen der beim Serverstart
 * gestartet wird.<br>
 * <br>
 * siehe: zkoss frontend sub project.<br>
 * de.forsthaus.InitApplicationWorkspace.java<br>
 * de.forsthaus.CleanDemoDataParsingJob.java<br>
 * <br>
 * 
 * @author Stephan Gerth
 */
public class NonWebRequestDBAction {

	private HibernateOperations hibernateTemplate;

	protected HibernateOperations getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateOperations hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	/**
	 * Resets the admin password
	 */
	public void resetAdminPassword() {

		SecUser adminUser = getUserByID(new Long(11));

		System.out.println("###### ==> current admin password: " + adminUser.getUsrPassword() + "|" + adminUser.getUsrPassword());

		if (adminUser != null)
			if (!adminUser.getUsrPassword().equals("admin")) {
				adminUser.setUsrLoginname("admin");
				adminUser.setUsrFirstname("admin");
				adminUser.setUsrPassword("admin");
				adminUser.setUsrToken("");
				getHibernateTemplate().saveOrUpdate(adminUser);
				System.out.println("###### ==> new admin name/password: " + adminUser.getUsrPassword() + "|" + adminUser.getUsrPassword());
			}

	}

	public SecUser getUserByID(final Long usr_id) {
		return getHibernateTemplate().get(SecUser.class, usr_id);
	}

}
