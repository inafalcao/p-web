package br.com.trabalho02.bean;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.commons.io.IOUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

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
	
	private UploadedFile file;
	private StreamedContent fileDownload;
	
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
			repository.getSession().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void removerArquivo(Arquivo arquivo)
	{
		try {
			diretorio.getArquivos().remove(arquivo);
			arquivoRepository.remove(arquivo);
			repository.save(diretorio);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void carregarArquivo() {
        if(getFile() != null) {  
        	try {
        		novoArquivo.setConteudo(IOUtils.toByteArray(getFile().getInputstream()));
        		novoArquivo.setNome(getFile().getFileName());
        		adicionarArquivo();
        	} catch(IOException e) {
        		e.printStackTrace();
        	} catch(Exception e) {
        		e.printStackTrace();
        	}
        }
    }

	public void logout() throws IOException {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    ec.invalidateSession();
	    ec.redirect(ec.getRequestContextPath() + "/Login.action");
	}
	
	public void adicionarDiretorio() throws Exception {
		
		if(diretorio.getSubdiretorios() == null)
			diretorio.setSubdiretorios(new ArrayList<Diretorio>());
		if(diretorio.getArquivos() == null)
			diretorio.setArquivos(new ArrayList<Arquivo>());
		
		novoDiretorio.setPai(diretorio);
		repository.save(novoDiretorio);
		diretorio.getSubdiretorios().add(novoDiretorio);

		repository.save(diretorio);
		novoDiretorio = new Diretorio();
	}
	
	public void adicionarArquivo() throws Exception {
	
		novoArquivo.setDiretorio(diretorio);
		
		arquivoRepository.save(novoArquivo);
		diretorio.getArquivos().add(novoArquivo);
		repository.save(diretorio);
		
		novoArquivo = new Arquivo();
	
	}
	
	public void downloadArquivo(Arquivo arquivo) {        
		InputStream is = new ByteArrayInputStream(arquivo.getConteudo());
		fileDownload = new DefaultStreamedContent(is, null, arquivo.getNome());
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

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public StreamedContent getFileDownload() {
		return fileDownload;
	}

	public void setFileDownload(StreamedContent fileDownload) {
		this.fileDownload = fileDownload;
	}

}
