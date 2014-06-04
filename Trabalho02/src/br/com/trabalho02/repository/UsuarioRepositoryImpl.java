package br.com.trabalho02.repository;

import javax.persistence.criteria.CriteriaQuery;
import br.com.trabalho02.entidade.Usuario;

import org.springframework.stereotype.Repository;

@Repository("usuarioRepository")
public class UsuarioRepositoryImpl extends RepositoryImpl<Usuario>
	implements UsuarioRepository{

	private static final long serialVersionUID = 8061212594262214460L;

	@Override
	public CriteriaQuery<?> montaCriteriaPesquisa(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
