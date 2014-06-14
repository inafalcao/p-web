package br.com.trabalho02.strutscontroller;

import java.util.Map;

import javax.faces.context.FacesContext;

import org.apache.struts2.interceptor.SessionAware;

import br.com.trabalho02.entidade.Usuario;
import br.com.trabalho02.repository.UsuarioRepository;
import br.com.trabalho02.repository.UsuarioRepositoryImpl;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


public class NuvemController extends ActionSupport implements SessionAware {
    
	private static final long serialVersionUID = 1L;
	
	private String login;
    private String senha;
    private Usuario usuario;
	public UsuarioRepository repository;
    
	private Map session;
    
    public String login() throws Exception
    {
    	repository = new UsuarioRepositoryImpl();
    	usuario = repository.obterPorCampo(Usuario.class, "login", login);
        
    	// Se existe um usuário com este login
		if(usuario != null){
			// Se existe um usuário com esta senha
			if(usuario.getSenha().equals(senha)) {
				// Autenticação bem sucedida. 
				// 1. Usuário é redirecionado para a index.
				// 2. Usuário é 'guardado' na sessão.
				setUsuario(usuario);
	            // Coloca usuário na sessão
	            session = ActionContext.getContext().getSession();
	            session.put("usuario", usuario);
				
				return "success";
			} else {
				return "fail";
			}
		} else {
			return "fail";
		}
    	
    }

    

	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}
}
