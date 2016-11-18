package com.cesar.tcc.smartlibrary.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "informe")
public class Informe implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private User user;

	private String text;

	/**
	 * @return
	 */
	@Id
	@Column(name = "id")
	public Integer getId()
	{
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(final Integer id)
	{
		this.id = id;
	}

	/**
	 * @return
	 */
	@OneToMany(mappedBy = "id")
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
	@Column(name = " texto ")
	@NotNull
	public String getText()
	{
		return text;
	}

	/**
	 * @param text
	 */
	public void setText(final String text)
	{
		this.text = text;
	}

}
