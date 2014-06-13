package br.com.trabalho02.servico;

import javax.persistence.criteria.CriteriaQuery;

import br.com.trabalho02.entidade.Diretorio;

public interface DiretorioService extends Service<Diretorio>{

	public CriteriaQuery<?> montaCriteriaPesquisa(Diretorio diretorio);
	
}
