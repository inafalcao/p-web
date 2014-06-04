package br.com.trabalho02.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Usuario extends Entidade {

	private static final long serialVersionUID = 1L;

	private String nome;
	private String login;
	private String senha;
	private double tamanhoCota;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
