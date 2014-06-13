package br.com.trabalho02.repository;

import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Repository;

import br.com.trabalho02.entidade.Arquivo;

@Repository("arquivoRepository")
public class ArquivoRepositoryImpl extends RepositoryImpl<Arquivo>
	implements ArquivoRepository{

	private static final long serialVersionUID = 8061212594262214460L;

	@Override
	public CriteriaQuery<?> montaCriteriaPesquisa(Arquivo arquivo) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
