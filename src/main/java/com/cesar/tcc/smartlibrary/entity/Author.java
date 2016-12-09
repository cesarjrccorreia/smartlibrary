package com.cesar.tcc.smartlibrary.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author cesar
 *
 */
@Entity
@Table(name = "Autor")
public class Author implements Serializable {

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

	@NotNull
	@Column(name = "name")
	private String name;

	/**
	 * @return
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param id
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

	/**
	 * @param name
	 */
	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(final Object obj) {

		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (!(obj instanceof User))
			return false;

		final Author other = (Author) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;

		return true;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Author [id = " + id + " Name = " + name + "]";
	}

}
