package com.cesar.tcc.smartlibrary.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

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

	private Integer year;

	private Integer edition;

	private String summary;

	private String image;

	private Integer quantity;

	private Double rating;

	private Set<Author> authors;

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
	@JoinColumn(name = "editora_id", nullable = false)
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
	@NotEmpty
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
	@Min(value = 1900)
	public Integer getYear()
	{
		return year;
	}

	/**
	 * @param year
	 *            the ano to set
	 */
	public void setYear(final Integer year)
	{
		this.year = year;
	}

	/**
	 * @return the edicao
	 */
	@Column(name = "edicao")
	@NotNull
	@Min(value = 1)
	public Integer getEdition()
	{
		return edition;
	}

	/**
	 * @param edition
	 *            the edicao to set
	 */
	public void setEdition(final Integer edition)
	{
		this.edition = edition;
	}

	/**
	 * @return the sumario
	 */
	@Column(name = "sumario")
	@NotBlank
	@NotNull
	public String getSummary()
	{
		return summary;
	}

	/**
	 * @param summary
	 *            the sumario to set
	 */
	public void setSummary(final String summary)
	{
		this.summary = summary;
	}

	/**
	 * @return the imagem
	 */
	@Column(name = "imagem")
	public String getImage()
	{
		return image;
	}

	/**
	 * @param image
	 *            the imagem to set
	 */
	public void setImage(final String image)
	{
		this.image = image;
	}

	/**
	 * @return the quantidade
	 */
	@Column(name = "quantidade")
	@NotNull
	@Min(value = 1)
	public Integer getQuantity()
	{
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantidade to set
	 */
	public void setQuantity(final Integer quantity)
	{
		this.quantity = quantity;
	}

	/**
	 * @return the classificacao
	 */
	@Column(name = "classificacao")
	public Double getRating()
	{
		return rating;
	}

	/**
	 * @param rating
	 *            the classificacao to set
	 */
	public void setRating(final Double rating)
	{
		this.rating = rating;
	}

	/**
	 * @return
	 */
	@NotEmpty
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "autor_has_livro", joinColumns = @JoinColumn(name = "livro_id"),
			inverseJoinColumns = @JoinColumn(name = " autor_id "))
	public Set<Author> getAuthors()
	{
		return authors;
	}

	/**
	 * @param authors
	 */
	public void setAuthors(final Set<Author> authors)
	{
		this.authors = authors;
	}

}
