package br.com.trabalho02.entidade;

import java.io.File;
import java.util.List;
import javax.persistence.Entity;

@Entity
public class Diretorio extends Entidade {

	private static final long serialVersionUID = 1L;

	private String nome;
	
	private List<Diretorio> subdiretorios;
	
	private List<File> arquivos;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Diretorio> getSubdiretorios() {
		return subdiretorios;
	}
	public void setSubdiretorios(List<Diretorio> subdiretorios) {
		this.subdiretorios = subdiretorios;
	}
	public List<File> getArquivos() {
		return arquivos;
	}
	public void setArquivos(List<File> arquivos) {
		this.arquivos = arquivos;
	}
	
}
