package com.cesar.tcc.smartlibrary.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = " disciplina ")
public class Disciplina implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer Id;

	private String name;

	private String ementa;

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

	/**
	 * @return
	 */
	@Column(name = "nome")
	@NotNull
	public String getName()
	{
		return name;
	}

	/**
	 * @param nome
	 */
	public void setName(final String name)
	{
		this.name = name;
	}

	/**
	 * @return
	 */
	@Column(name = " ementa ")
	@NotNull
	public String getEmenta()
	{
		return ementa;
	}

	/**
	 * @param ementa
	 */
	/**
	 * @param ementa
	 */
	public void setEmenta(final String ementa)
	{
		this.ementa = ementa;
	}

}
