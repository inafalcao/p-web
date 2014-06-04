package br.com.trabalho02.servico;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Service;

import br.com.trabalho02.entidade.Usuario;
import br.com.trabalho02.repository.Repository;
import br.com.trabalho02.repository.UsuarioRepository;
import br.com.trabalho02.servico.UsuarioService;

@Service("usuarioService")
public class UsuarioServiceImpl extends ServiceImpl<Usuario>
	implements UsuarioService {
	
	private static final long serialVersionUID = -7579434482940907523L;
	
	@Resource(name = "usuarioRepository")
	private UsuarioRepository usuarioRepository;
	
	@Override
	public Repository<Usuario> getRepository() {
		return usuarioRepository;
	}

	@Override
	public CriteriaQuery<?> montaCriteriaPesquisa(Usuario usuario) {
		return usuarioRepository.montaCriteriaPesquisa(usuario);
	}
	
}