package br.com.trabalho02.repository;

import javax.persistence.criteria.CriteriaQuery;

import br.com.trabalho02.entidade.Diretorio;

public interface DiretorioRepository extends Repository<Diretorio> {
	
	public CriteriaQuery<?> montaCriteriaPesquisa(Diretorio diretorio);

}
