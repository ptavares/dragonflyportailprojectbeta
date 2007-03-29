package fr.umlv.dragonflyBdd.utils;

import org.hibernate.CacheMode;
import org.hibernate.FlushMode;
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
	public static final ThreadLocal session = new ThreadLocal(); 
	
	
	
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
	 * Open a session to access database
	 * @return Returns the hibernate's session opened.
	 * @throws HibernateException
	 */
	public static Session openSession() throws HibernateException {
		
		Session s = sessionFactory.openSession();
		s.setFlushMode(FlushMode.COMMIT);
		s.setCacheMode(CacheMode.IGNORE);
		return s;
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
