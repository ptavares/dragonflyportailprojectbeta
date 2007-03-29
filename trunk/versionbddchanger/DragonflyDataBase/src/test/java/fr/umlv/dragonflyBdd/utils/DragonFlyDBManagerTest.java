/**
 * 
 */
package fr.umlv.dragonflyBdd.utils;

import org.hibernate.Session;

import junit.framework.TestCase;

/**
 * JUnit Test Class for {@link fr.umlv.dragonflyBdd.utils.DragonFlyDBManager}
 * @author Tavares Patrick 
 *
 */
public class DragonFlyDBManagerTest extends TestCase {

	/**
	 * Test method for {@link fr.umlv.dragonflyBdd.utils.DragonFlyDBManager#openSession()}.
	 */
	public void testOpenSession() {
		Session session = DragonFlyDBManager.openSession();
		assertNotNull(session);
		session.close();
	}

}
