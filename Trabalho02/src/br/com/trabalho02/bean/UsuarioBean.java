package br.com.trabalho02.bean;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import br.com.trabalho02.entidade.Usuario;
import br.com.trabalho02.repository.Repository;
import br.com.trabalho02.repository.UsuarioRepository;
import br.com.trabalho02.repository.UsuarioRepositoryImpl;

@ManagedBean(name = "usuarioBean")
@SessionScoped
public class UsuarioBean extends BaseControllerBean<Usuario> {
	
	private static final long serialVersionUID = 1L;
	
	public Usuario usuario;
	public UsuarioRepository repository;
	
	@PostConstruct
	public void inicializar() {
		try {
			repository = new UsuarioRepositoryImpl();
			setEntity(new Usuario());
			usuario = new Usuario();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void autenticar() throws IOException {
		Usuario usuario = repository.obterPorCampo(Usuario.class, "login", getEntity().getLogin());
		
		// Se existe um usuário com este login
		if(usuario != null){
			// Se existe um usuário com esta senha
			if(usuario.getSenha().equals(getEntity().getSenha())) {
				// Autenticação bem sucedida. 
				// 1. Usuário é redirecionado para a index.
				// 2. Usuário é 'guardado' na sessão do bean.
				FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
				setEntity(usuario);
			} else {
				addMessageError("Senha inválida.");
				return;
			}
		} else {
			addMessageError("Login inválido.");
			return;
		}
	}
	
	@Override
	public Usuario getEntity() {
		return super.getEntity();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	protected Repository<Usuario> getRepository() {
		return this.repository;
	}

}
