package br.com.trabalho02.entidade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Diretorio extends Entidade {

	private static final long serialVersionUID = 1L;

	@Column(name = "nome")
	private String nome;

	@OneToMany()
	private List<Diretorio> subdiretorios;

	@OneToMany(mappedBy = "diretorio")
	private List<Arquivo> arquivos;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USUARIO_ID")
	private Usuario dono;

	@ManyToOne
	private Diretorio pai;

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
	
	public Usuario getDono() {
		return dono;
	}

	public void setDono(Usuario dono) {
		this.dono = dono;
	}

	public void setSubdiretorios(List<Diretorio> subdiretorios) {
		this.subdiretorios = subdiretorios;
	}

	public void setArquivos(List<Arquivo> arquivos) {
		this.arquivos = arquivos;
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