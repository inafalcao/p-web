package br.com.trabalho02.entidade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Diretorio extends Entidade {

	private static final long serialVersionUID = 1L;

	private String nome;

	@OneToMany
	private List<Diretorio> subdiretorios;

	@OneToMany(mappedBy = "diretorio")
	private List<Arquivo> arquivos;

	@ManyToOne
	private Diretorio pai;

	/*public Diretorio() {
		subdiretorios = new ArrayList<Diretorio>();
		arquivos = new ArrayList<Arquivo>();
		pai = new Diretorio();
	}*/

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Diretorio> getSubdiretorios() {
		return this.subdiretorios;
	}

	public void setSubdiretorios(ArrayList<Diretorio> subdiretorios) {
		this.subdiretorios = subdiretorios;
	}

	public List<Arquivo> getArquivos() {
		return this.arquivos;
	}

	public void setArquivos(ArrayList<Arquivo> arquivos) {
		this.arquivos = arquivos;
	}

	public Diretorio getPai() {
		return this.pai;
	}

	public void setPai(Diretorio pai) {
		this.pai = pai;
	}

}