package com.cesar.tcc.smartlibrary.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Emprestimo
{

	private Integer id;

	private User user;

	private Book book;

	private Date inicio;

	private Date fim;

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
	 * @return the user
	 */
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	public User getUser()
	{
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(final User user)
	{
		this.user = user;
	}

	/**
	 * @return the book
	 */
	@ManyToOne
	@JoinColumn(name = "livro_id")
	public Book getBook()
	{
		return book;
	}

	/**
	 * @param book
	 *            the book to set
	 */
	public void setBook(final Book book)
	{
		this.book = book;
	}

	/**
	 * @return the inicio
	 */
	@NotNull
	@DateTimeFormat
	public Date getInicio()
	{
		return inicio;
	}

	/**
	 * @param inicio
	 *            the inicio to set
	 */
	public void setInicio(final Date inicio)
	{
		this.inicio = inicio;
	}

	/**
	 * @return the fim
	 */
	@DateTimeFormat
	public Date getFim()
	{
		return fim;
	}

	/**
	 * @param fim
	 *            the fim to set
	 */
	public void setFim(final Date fim)
	{
		this.fim = fim;
	}

}
