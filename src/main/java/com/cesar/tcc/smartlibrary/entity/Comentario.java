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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "comentario")
public class Comentario implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String title;

	private String comment;

	private Integer rating;

	private User user;

	private Book book;

	/**
	 * @return the title
	 */
	@NotNull
	@NotEmpty
	@Column(name = "titulo")
	public String getTitle()
	{
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(final String title)
	{
		this.title = title;
	}

	/**
	 * @return the comment
	 */
	@NotNull
	@NotEmpty
	@Column(name = "comentario")
	public String getComment()
	{
		return comment;
	}

	/**
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(final String comment)
	{
		this.comment = comment;
	}

	/**
	 * @return the rating
	 */
	@NotNull
	@Max(value = 7)
	@Min(value = 1)
	@Column(name = "classificacao")
	public Integer getRating()
	{
		return rating;
	}

	/**
	 * @param rating
	 *            the rating to set
	 */
	public void setRating(final Integer rating)
	{
		this.rating = rating;
	}

	/**
	 * @return the user
	 */
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	@NotNull
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
	@JoinColumn(name = "livro_id", nullable = false)
	@NotNull
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

}
