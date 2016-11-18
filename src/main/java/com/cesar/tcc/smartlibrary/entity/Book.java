package com.cesar.tcc.smartlibrary.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "livro")
public class Book implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Editora editora;

	private String isbn;

	private String name;

	private Integer ano;

	private String edicao;

	private String sumario;

	private String imagem;

	private Integer quantidade;

	private Double classificacao;

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId()
	{
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the editora
	 */
	@ManyToOne
	@JoinColumn(name = "editora_id")
	public Editora getEditora()
	{
		return editora;
	}

	/**
	 * @param editora
	 *            the editora to set
	 */
	public void setEditora(final Editora editora)
	{
		this.editora = editora;
	}

	/**
	 * @return the isbn
	 */
	@Column(name = "isbn")
	@NotNull
	public String getIsbn()
	{
		return isbn;
	}

	/**
	 * @param isbn
	 *            the isbn to set
	 */
	public void setIsbn(final String isbn)
	{
		this.isbn = isbn;
	}

	/**
	 * @return the name
	 */
	@Column(name = "nome")
	@NotNull
	@NotBlank
	public String getName()
	{
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name)
	{
		this.name = name;
	}

	/**
	 * @return the ano
	 */
	@Column(name = "ano")
	@NotNull
	public Integer getAno()
	{
		return ano;
	}

	/**
	 * @param ano
	 *            the ano to set
	 */
	public void setAno(final Integer ano)
	{
		this.ano = ano;
	}

	/**
	 * @return the edicao
	 */
	@Column(name = "edicao")
	@NotNull
	@NotBlank
	public String getEdicao()
	{
		return edicao;
	}

	/**
	 * @param edicao
	 *            the edicao to set
	 */
	public void setEdicao(final String edicao)
	{
		this.edicao = edicao;
	}

	/**
	 * @return the sumario
	 */
	@Column(name = "sumario")
	@NotBlank
	@NotNull
	public String getSumario()
	{
		return sumario;
	}

	/**
	 * @param sumario
	 *            the sumario to set
	 */
	public void setSumario(final String sumario)
	{
		this.sumario = sumario;
	}

	/**
	 * @return the imagem
	 */
	@Column(name = "imagem")
	@NotBlank
	@NotNull
	public String getImagem()
	{
		return imagem;
	}

	/**
	 * @param imagem
	 *            the imagem to set
	 */
	public void setImagem(final String imagem)
	{
		this.imagem = imagem;
	}

	/**
	 * @return the quantidade
	 */
	@Column(name = "quantidade")
	@NotNull
	@NotBlank
	public Integer getQuantidade()
	{
		return quantidade;
	}

	/**
	 * @param quantidade
	 *            the quantidade to set
	 */
	public void setQuantidade(final Integer quantidade)
	{
		this.quantidade = quantidade;
	}

	/**
	 * @return the classificacao
	 */
	@Column(name = "classificacao")
	@NotBlank
	@NotNull
	public Double getClassificacao()
	{
		return classificacao;
	}

	/**
	 * @param classificacao
	 *            the classificacao to set
	 */
	public void setClassificacao(final Double classificacao)
	{
		this.classificacao = classificacao;
	}

}
