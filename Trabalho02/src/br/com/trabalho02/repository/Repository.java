package br.com.trabalho02.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import br.com.trabalho02.entidade.Entidade;

public interface Repository<E extends Entidade> extends Serializable {

    public E save(E entidade) throws Exception;

    public void remove(E entidade)throws Exception;

    public E findBy(Long id);

    public List<E> findAll();

    //List<E> buscarPorAtributosPreenchidos(E entidade);
    
	E obterPorCampo(Class cls,Serializable campo,Serializable valor) throws NoResultException;

    public Session getSession();
    
	//public List<E> readByQuery(CriteriaQuery<?> criteria, Integer initionPos, Integer quantResultados);
	
	//public Long totalOfQuery(CriteriaQuery<?> criteria);
	
	//void refresh(E entidade);

	List<E> obterPor(Class cls, Serializable campo, Serializable valor);


}
