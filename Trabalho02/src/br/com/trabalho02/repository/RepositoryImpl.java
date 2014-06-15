package br.com.trabalho02.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Expression;

import br.com.trabalho02.db.HibernateSession;
import br.com.trabalho02.db.Sessao;
import br.com.trabalho02.entidade.Entidade;

import java.lang.reflect.ParameterizedType;

public class RepositoryImpl<E extends Entidade> implements Repository<E> {

	private static final long serialVersionUID = 1L;

	protected Root<E> entityRoot;
	private Class<E> persistentClass = null;
	
	SessionFactory factory;
    Session session;
	
    RepositoryImpl() {
    	/*factory = HibernateSession.getSessionFactory();
    	session = factory.openSession();*/
    }
   
    public E save(E entidade)  throws Exception {

    	Transaction tx = Sessao.getInstance().getSession().beginTransaction();
    	Sessao.getInstance().getSession().save(entidade);
        tx.commit();
        
        return entidade;
    }

    @Override
    public void remove(E entidade)  throws Exception {
		Transaction tx = Sessao.getInstance().getSession().beginTransaction();
		Sessao.getInstance().getSession().delete(entidade);
		tx.commit();
    }

    @Override
    public E findBy(Long id) {
		return (E) Sessao.getInstance().getSession().get(getPersistentClass(), new Long(id));
    }

    @SuppressWarnings("unchecked")
    public Class<E> getPersistentClass() {
        if (persistentClass == null) {
            this.persistentClass = (Class<E>) ((ParameterizedType) getClass()
                    .getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return persistentClass;
    }

    @Override
    public List<E> findAll() {
    	Criteria criteria = Sessao.getInstance().getSession().createCriteria(getPersistentClass());
    	List<E> result = (List<E>) criteria.list();
        return result;
    }

    @Override
    public Session getSession() {
        return session;
    }

	@Override
	public E obterPorCampo(Class cls, Serializable campo, Serializable valor)
			throws NoResultException {
		
		Criteria criteria = Sessao.getInstance().getSession().createCriteria(getPersistentClass()); 
		criteria.add( Expression.like((String) campo, valor) );
		List<E> result = (List<E>) criteria.list();
		
		if(result.size() != 0) {
			return (E) result.get(0);
		}
		
		return null;
	}
	
	@Override
	public List<E> obterPor(Class cls, Serializable campo, Serializable valor){
		Criteria criteria = Sessao.getInstance().getSession().createCriteria(getPersistentClass());
		criteria.add( Expression.like((String) campo, valor) );
		List<E> result = (List<E>) criteria.list();
		return  result;
	}

	protected Root<E> getEntityRoot(){
        return entityRoot;
	}

}
