package br.com.trabalho02.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import br.com.trabalho02.entidade.Produto;


public class Sessao {

	public static void main(String[] args) {
		AnnotationConfiguration configuration = new AnnotationConfiguration();
		configuration.configure();
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();
		
		Produto produto = new Produto();
		produto.setNome("Playstation 3");
		produto.setDescricao("Melhor videogame do mundo!!!");
		produto.setPreco(1090.50);
		Transaction tx = session.beginTransaction();
		session.save(produto);
		tx.commit();


	}
	
}
