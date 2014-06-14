package br.com.trabalho02.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.trabalho02.entidade.Arquivo;
import br.com.trabalho02.entidade.Diretorio;
import br.com.trabalho02.entidade.Usuario;
import br.com.trabalho02.repository.ArquivoRepository;
import br.com.trabalho02.repository.ArquivoRepositoryImpl;
import br.com.trabalho02.repository.DiretorioRepository;
import br.com.trabalho02.repository.DiretorioRepositoryImpl;
import br.com.trabalho02.repository.Repository;
import br.com.trabalho02.repository.UsuarioRepository;
import br.com.trabalho02.repository.UsuarioRepositoryImpl;

@ManagedBean(name = "uploaderBean")
@ViewScoped
public class UploaderBean extends BaseControllerBean<Diretorio> {
	
	private static final long serialVersionUID = 1428374L;
	
	public String usuarioShare;
	public Diretorio diretorioShare;
	
	public Usuario usuario;
	public UsuarioRepository usuarioRepository;
	
	public Diretorio diretorio;
	public DiretorioRepository repository;
	
	public ArquivoRepository arquivoRepository;
	
	public Diretorio novoDiretorio;
	public Arquivo novoArquivo;
	
	private Diretorio deleteDiretorio;
	
	@PostConstruct
	public void inicializar() {
		try {
			
			Map session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
			usuario = (Usuario) session.get("usuario");
			
			repository = new DiretorioRepositoryImpl();
			arquivoRepository = new ArquivoRepositoryImpl();
			usuarioRepository = new UsuarioRepositoryImpl();
			
			diretorioShare = new Diretorio();
			novoDiretorio = new Diretorio();
			novoArquivo = new Arquivo();
			
			// TEMPORÁRIO
			//diretorio = repository.obterPorCampo(Diretorio.class, "nome", "raiz");
			
			// USAR ESSA VERSÃO QUANDO RECUPERAR USUÁRIO DA SESSÃO
			if(usuario!=null && usuario.getRaiz() != null) {
				diretorio = usuario.getRaiz();
			} else {
				diretorio = new Diretorio();
				diretorio.setNome("raiz");
				diretorio.setDono(usuario);
				repository.save(diretorio);
				
				usuario.setRaiz(diretorio);
				usuarioRepository.save(usuario);
			}
			
			
			if(diretorio.getSubdiretorios() == null)
				diretorio.setSubdiretorios(new ArrayList<Diretorio>());
			if(diretorio.getArquivos() == null)
				diretorio.setArquivos(new ArrayList<Arquivo>());
			
			
			setEntity(new Diretorio());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removerDiretorio(Diretorio diretorio)
	{
		try {
			System.out.println("remover!");
			this.diretorio.getSubdiretorios().remove(diretorio);
			repository.save(this.diretorio);
			repository.remove(diretorio);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removerArquivo(Arquivo arquivo)
	{
		try {
			System.out.println("remover!");
			arquivoRepository.remove(arquivo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void adicionarDiretorio() throws Exception {
		
		// TODO: setar usuário no diretório
		
		novoDiretorio.setPai(new Diretorio());
		
		novoDiretorio.setPai(diretorio);
		repository.save(novoDiretorio);
		diretorio.getSubdiretorios().add(novoDiretorio);
		
		novoDiretorio = new Diretorio();
	}
	
	public void adicionarArquivo() throws Exception {
	
		novoArquivo.setDiretorio(diretorio);
		
		arquivoRepository.save(novoArquivo);
		diretorio.getArquivos().add(novoArquivo);
		repository.save(diretorio);
		
		novoArquivo = new Arquivo();
	
	}
	
	
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Diretorio getNovoDiretorio() {
		return novoDiretorio;
	}

	public void setNovoDiretorio(Diretorio novoDiretorio) {
		this.novoDiretorio = novoDiretorio;
	}

	public Arquivo getNovoArquivo() {
		return novoArquivo;
	}

	public void setNovoArquivo(Arquivo novoArquivo) {
		this.novoArquivo = novoArquivo;
	}

	@Override
	public Diretorio getEntity() {
		return super.getEntity();
	}
	
	public Diretorio getDiretorio() {
		return diretorio;
	}
	
	public List<Diretorio> getDiretorios() {
		return (List<Diretorio>) repository.obterPorCampo(Diretorio.class, "pai", diretorio);
	}

	public List<Arquivo> getArquivos() {
		return (List<Arquivo>) repository.obterPorCampo(Arquivo.class, "pai", diretorio);
	}
	
	public void setDiretorio(Diretorio diretorio) {
		this.diretorio = diretorio;
	}

	@Override
	protected Repository<Diretorio> getRepository() {
		return this.repository;
	}

	public Diretorio getDeleteDiretorio() {
		return deleteDiretorio;
	}

	public void setDeleteDiretorio(Diretorio deleteDiretorio) {
		this.deleteDiretorio = deleteDiretorio;
	}

	public String getUsuarioShare() {
		return usuarioShare;
	}

	public void setUsuarioShare(String usuarioShare) {
		this.usuarioShare = usuarioShare;
	}
	
	public void compartilhar() throws Exception {
		Usuario usuario = usuarioRepository.obterPorCampo(Usuario.class, "login", usuarioShare);
		
		if(usuario != null) {
			if(usuario.getShared()==null)
				usuario.setShared(new ArrayList<Diretorio>());
			usuario.getShared().add(diretorioShare);
			// Atualiza usuário
			usuarioRepository.save(usuario);
		}
	}

	public Diretorio getDiretorioShare() {
		return diretorioShare;
	}

	public void setDiretorioShare(Diretorio diretorioShare) {
		this.diretorioShare = diretorioShare;
	}
	
	
}
