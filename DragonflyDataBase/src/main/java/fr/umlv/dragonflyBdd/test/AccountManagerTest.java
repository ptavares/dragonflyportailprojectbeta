package fr.umlv.dragonflyBdd.test;

import fr.umlv.dragonflyBdd.AccountManager;
import fr.umlv.dragonflyBdd.tables.User;

public class AccountManagerTest {


	public static void main(String[] args) {
		
		AccountManager manager = AccountManager.getAccountManagerInstance();
		
		manager.createAccount("ptavares@test.com", "pat", "test");
		
		User user = manager.getUser("ptavares@test.com");
		System.out.println(user.getNickname()+" "+user.getPassword());
		
		System.out.println("----- Change mot de passe --------");
		manager.changePassword("ptavares@test.com", "TGBIBI");
		user = manager.getUser("ptavares@test.com");
		System.out.println(user.getNickname()+" "+user.getPassword());
		
		System.out.println("----- Change pseudo  --------");
		manager.changeNickmane("ptavares@test.com", "patou");
		user = manager.getUser("ptavares@test.com");
		System.out.println(user.getNickname()+" "+user.getPassword());
		
//		System.out.println("----- Suppression -------");
//		manager.removeAccount("ptavares@test.com");
//		System.out.println("Ca doi etre null : "+manager.getUser("ptavares@test.com"));
		
	}

}
