package com.jakequinter.db;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Makes sure database and employees table exists. Runs at Web app startup, but
 * even that may be too often, so code has try/catch internally to prevent
 * problems if table already exists.
 * <p>
 * From <a href="http://courses.coreservlets.com/Course-Materials/">the
 * coreservlets.com tutorials on servlets, JSP, Struts, JSF, Ajax, GWT, Spring,
 * Hibernate/JPA, and Java programming</a>.
 */

@WebListener // This tells Tomcat to run this class on app startup/shutdown
public class DatabaseInitializer implements ServletContextListener {
	
	public void contextInitialized(ServletContextEvent event) {
		new EmbeddedDbCreator().createDatabase();
	}

	public void contextDestroyed(ServletContextEvent event) {
	}
}