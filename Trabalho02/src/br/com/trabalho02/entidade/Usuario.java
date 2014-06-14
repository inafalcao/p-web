package br.com.trabalho02.entidade;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Usuario extends Entidade {

	private static final long serialVersionUID = 1L;

	private String login;
	private String senha;
	private double tamanhoCota;

	@OneToOne(mappedBy = "dono")
	private Diretorio raiz;
	
	@OneToMany()
	private List<Diretorio> shared;
	
	public Diretorio getRaiz() {
		return raiz;
	}

	public void setRaiz(Diretorio raiz) {
		this.raiz = raiz;
	}

	public List<Diretorio> getShared() {
		return shared;
	}

	public void setShared(List<Diretorio> shared) {
		this.shared = shared;
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
	public double getTamanhoCota() {
		return tamanhoCota;
	}
	public void setTamanhoCota(double tamanhoCota) {
		this.tamanhoCota = tamanhoCota;
	}
	
}
