package fr.umlv.dragonflyBdd.utils;

import org.hibernate.HibernateException;

public class TablesCreator {

	public static void main(String[] args) {
		try {
			DragonFlyDBManager.createTables();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}
}
