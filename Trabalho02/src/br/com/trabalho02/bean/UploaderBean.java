package br.com.trabalho02.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.trabalho02.entidade.Arquivo;
import br.com.trabalho02.entidade.Diretorio;
import br.com.trabalho02.repository.ArquivoRepository;
import br.com.trabalho02.repository.ArquivoRepositoryImpl;
import br.com.trabalho02.repository.DiretorioRepository;
import br.com.trabalho02.repository.DiretorioRepositoryImpl;
import br.com.trabalho02.repository.Repository;

@ManagedBean(name = "uploaderBean")
@ViewScoped
public class UploaderBean extends BaseControllerBean<Diretorio> {
	
	private static final long serialVersionUID = 1428374L;
	
	public Diretorio diretorio;
	public DiretorioRepository repository;
	
	public ArquivoRepository arquivoRepository;
	
	public Diretorio novoDiretorio;
	public Arquivo novoArquivo;
	
	private Diretorio deleteDiretorio;
	
	@PostConstruct
	public void inicializar() {
		try {
			repository = new DiretorioRepositoryImpl();
			arquivoRepository = new ArquivoRepositoryImpl();
			
			novoDiretorio = new Diretorio();
			novoArquivo = new Arquivo();
			
			diretorio = repository.obterPorCampo(Diretorio.class, "nome", "raiz");
			
			
			// Cria a raiz, se ela nao existir
			if(diretorio == null) {
				diretorio = new Diretorio();
				diretorio.setNome("raiz");
				repository.save(diretorio);
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
}
