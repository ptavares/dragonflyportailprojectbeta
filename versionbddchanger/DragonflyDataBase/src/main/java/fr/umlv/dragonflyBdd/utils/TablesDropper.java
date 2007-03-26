package fr.umlv.dragonflyBdd.utils;

import org.hibernate.HibernateException;

public class TablesDropper {

	public static void main(String[] args) {
		try {
			DragonFlyDBManager.dropTables();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}
}
