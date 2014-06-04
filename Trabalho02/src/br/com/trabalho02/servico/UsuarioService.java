package br.com.trabalho02.servico;

import javax.persistence.criteria.CriteriaQuery;

import br.com.trabalho02.entidade.Usuario;

public interface UsuarioService extends Service<Usuario>{

	public CriteriaQuery<?> montaCriteriaPesquisa(Usuario usuario);
	
}
