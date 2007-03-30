/*
 * CreateAdmin.java
 *
 * Created on 30 mars 2007, 18:08
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fr.umlv.dragonflyBdd.utils;

import fr.umlv.dragonflyBdd.AccountManager;
import fr.umlv.dragonflyBdd.exception.DragonflyBddException;
import java.util.Date;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import fr.umlv.dragonflyBdd.tables.User;

/**
 *
 * @author J-Paul
 */
public class CreateAdmin {
    public static void main(String[] args) {
        System.out.println("CreateAccount");
        
//        Transaction tx = null;
//        Session session = null;
//        Date date = new Date(System.currentTimeMillis());
//        try{
//            session = DragonFlyDBManager.openSession();
//            tx = session.beginTransaction();
//            User user = new User("admin",date,"admin","c3a73bdf1fb174fc995a5b6072466cedcd32d922");
//            session.save(user);
//            tx.commit();
//        }catch(ConstraintViolationException e){
//        }catch (Exception e) {
//        } finally {
////            session.close();
//        }
        
        try {
            AccountManager ac = AccountManager.getAccountManagerInstance();
            if(ac == null)
                System.out.println("AccountManagerNull");
            else{
                ac.createAccount("admin@dragonfly.com","admin","c3a73bdf1fb174fc995a5b6072466cedcd32d922");
                ac.activateAccount("admin@dragonfly.com");
            }
        } catch (DragonflyBddException ex) {
            ex.printStackTrace();
        }
    }
    
}
