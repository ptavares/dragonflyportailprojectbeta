package fr.umlv.dragonflyBdd.utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 * This is a utility class to manage the DragonFly's (hibernate) sessions
 * 
 * @author Tavares Patrick
 *
 */
public class DragonFlyDBManager {

	private static final SessionFactory sessionFactory;

	private static final Configuration config;
	
	private static final ThreadLocal<Session> session = new ThreadLocal<Session>();
	
	/**
	 * Create only one SessionFactory
	 */
	static {
		try {
			config = new Configuration();
			sessionFactory = config.configure().buildSessionFactory();
		
		} catch (HibernateException he) {
			System.err.println("Configuration Error. Initial SessionFactory creation failed : "+he.getMessage());
			throw new ExceptionInInitializerError(he);
		}
	}
	
	/**
	 * Private Constructor
	 *
	 */
	private DragonFlyDBManager() {
		
	}
	
	/**
	 * Get the current hibernate session.
	 * 
	 * @return Returns the current hibernate session.
	 * @throws HibernateException
	 */
	public static Session getcurrentSession() throws HibernateException {
		Session s = (Session)session.get();
		//If this Thread have no connection, open one Session
		if(s == null) {
			s = sessionFactory.openSession();
			session.set(s);
		}
		return s;
	}

	/**
	 * Close the current hibernate session.
	 * @throws HibernateException
	 */
	public static void closeSession() throws HibernateException {
		Session s = (Session)session.get();
		session.set(null);
		if(s != null) {
			s.close();
		}
	}
	
	/**
	 * Create all databases tables from hibernate's configuration.
	 *
	 */
	public static void createTables() {
		new SchemaExport(config).create(true,true);
	}
	
	/**
	 * Drop all databases tables from hibernate's configuration.
	 *
	 */
	public static void dropTables() {
		new SchemaExport(config).drop(true,true);
	}
}
