package br.com.trabalho02.servico;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.transaction.annotation.Transactional;

import br.com.trabalho02.entidade.Entidade;
import br.com.trabalho02.repository.Repository;


public class ServiceImpl<E extends Entidade> implements Service<E>  {
	
	private Repository<E> repository;

	public Repository<E> getRepository() {
		return repository;
	}


	@Override
	@Transactional(rollbackFor = Exception.class)
	public E save(E entidade) throws Exception{
		return getRepository().save(entidade);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void remove(E entidade) throws Exception {
		getRepository().remove(entidade);
		
	}

	@Override
	@Transactional()
	public E findBy(Long id) {
		return getRepository().findBy(id);
	}

	@Override
	@Transactional()
	public List<E> findAll() {
		return getRepository().findAll();
	}

	

	@Override
	@Transactional()
	public E obterPorCampo(Class cls, Serializable campo, Serializable valor)
			throws NoResultException {
		return getRepository().obterPorCampo(cls, campo, valor);
	}
	
	

	@Override
	public org.hibernate.Session getSession() {
		return getRepository().getSession();
	}




	@Override
	public List<E> obterPor(Class cls, Serializable campo, Serializable valor) {
		
		return getRepository().obterPor(cls, campo, valor);
	}


	@Override
	public List<E> buscarPorAtributosPreenchidos(E entidade) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<E> readByQuery(CriteriaQuery<?> criteria, int initionPos,
			int finalPos) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Long totalOfQuery(CriteriaQuery<?> criteria) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void refresh(E entidade) {
		// TODO Auto-generated method stub
		
	}


}
