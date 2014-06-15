package br.com.trabalho02.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class Sessao {

	   private static Sessao instance = null;
	   private static Session session;
	   static SessionFactory factory;
	   
	   protected Sessao() {
	      // Exists only to defeat instantiation.
	   }
	   public static Sessao getInstance() {
	      if(instance == null) {
	         instance = new Sessao();
	         factory = HibernateSession.getSessionFactory();
	     	 session = factory.openSession();
	      }
	      return instance;
	   }
		public Session getSession() {
			return session;
		}
		public void setSession(Session session) {
			this.session = session;
		}
	   
}
