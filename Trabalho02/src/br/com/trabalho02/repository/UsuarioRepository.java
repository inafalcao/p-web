package br.com.trabalho02.repository;

import javax.persistence.criteria.CriteriaQuery;

import br.com.trabalho02.entidade.Usuario;

public interface UsuarioRepository extends Repository<Usuario> {
	
	public CriteriaQuery<?> montaCriteriaPesquisa(Usuario usuario);

}
