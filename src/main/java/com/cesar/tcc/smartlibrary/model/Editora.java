package com.cesar.tcc.smartlibrary.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "editora")
public class Editora implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 
	 */
	@Column(name = "name")
	@NotNull
	private String name;

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @return
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

}
