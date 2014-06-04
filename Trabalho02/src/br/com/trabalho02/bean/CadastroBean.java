package br.com.trabalho02.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.trabalho02.entidade.Usuario;
import br.com.trabalho02.repository.Repository;
import br.com.trabalho02.repository.UsuarioRepository;
import br.com.trabalho02.repository.UsuarioRepositoryImpl;

@ManagedBean(name = "cadastroBean")
@ViewScoped
public class CadastroBean extends BaseControllerBean<Usuario> {
	
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
	
	@Override
	public void save() {
		Usuario usuario = repository.obterPorCampo(Usuario.class, "login", getEntity().getLogin());
		if(usuario != null){
			addMessageWarn("Já existe uma usuario cadastrada com esse login.");
			return;
		}
		super.save();
		usuario = new Usuario();
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
