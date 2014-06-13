package br.com.trabalho02.entidade;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Arquivo extends Entidade {

	private static final long serialVersionUID = 1L;

	private String nome;

	private byte[] conteudo;

	/* Diretório ao qual o arquivo pertence */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="DIRETORIO_ID")
	private Diretorio diretorio;

	public Arquivo() {

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public byte[] getConteudo() {
		return this.conteudo;
	}

	public void setConteudo(byte[] conteudo) {
		this.conteudo = conteudo;
	}

}