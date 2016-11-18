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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	@ManyToOne
	@JoinColumn(name = " usuario_id ")
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
