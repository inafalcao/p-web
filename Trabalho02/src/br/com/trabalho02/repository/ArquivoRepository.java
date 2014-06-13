package br.com.trabalho02.repository;

import javax.persistence.criteria.CriteriaQuery;

import br.com.trabalho02.entidade.Arquivo;

public interface ArquivoRepository extends Repository<Arquivo> {
	
	public CriteriaQuery<?> montaCriteriaPesquisa(Arquivo arquivo);

}
