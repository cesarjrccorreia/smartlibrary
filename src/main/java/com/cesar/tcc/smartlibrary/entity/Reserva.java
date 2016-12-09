package com.cesar.tcc.smartlibrary.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author cesar
 *
 */
@Entity
@Table(name = "reserva")
public class Reserva implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer Id;

	private User user;

	private Book book;

	/**
	 * @return
	 */
	@NotNull
	@ManyToOne
	@JoinColumn(name = "user_id")
	public User getUser()
	{
		return user;
	}

	/**
	 * @param user
	 */
	public void setUser(final User user)
	{
		this.user = user;
	}

	/**
	 * @return
	 */
	@NotNull
	@ManyToOne
	@JoinColumn(name = "livro_id")
	public Book getBook()
	{
		return book;
	}

	/**
	 * @param book
	 */
	public void setBook(final Book book)
	{
		this.book = book;
	}

	/**
	 * @return
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId()
	{
		return Id;
	}

	/**
	 * @param id
	 */
	public void setId(final Integer id)
	{
		Id = id;
	}

}
