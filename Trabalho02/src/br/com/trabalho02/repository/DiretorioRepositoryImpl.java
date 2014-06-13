package br.com.trabalho02.repository;

import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Repository;

import br.com.trabalho02.entidade.Diretorio;

@Repository("diretorioRepository")
public class DiretorioRepositoryImpl extends RepositoryImpl<Diretorio>
	implements DiretorioRepository{

	private static final long serialVersionUID = 8061212594262214460L;

	@Override
	public CriteriaQuery<?> montaCriteriaPesquisa(Diretorio diretorio) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
