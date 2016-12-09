
package com.cesar.tcc.smartlibrary.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "APP_USER")
public class User implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String username;

	private String password;

	private String name;

	private String email;

	private Set<UserProfile> userProfiles = new HashSet<>();

	private List<Disciplina> disciplinas;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId()
	{
		return id;
	}

	public void setId(final Integer id)
	{
		this.id = id;
	}

	@NotEmpty
	@Column(name = "username", unique = true, nullable = false)
	public String getUsername()
	{
		return username;
	}

	public void setUsername(final String username)
	{
		this.username = username;
	}

	@NotEmpty
	@Column(name = "password", nullable = false)
	public String getPassword()
	{
		return password;
	}

	public void setPassword(final String password)
	{
		this.password = password;
	}

	@NotEmpty
	@Column(name = "name", nullable = false)
	public String getName()
	{
		return name;
	}

	public void setName(final String name)
	{
		this.name = name;
	}

	@NotEmpty
	@Column(name = "email", nullable = false)
	public String getEmail()
	{
		return email;
	}

	public void setEmail(final String email)
	{
		this.email = email;
	}

	@NotEmpty
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "USER_HAS_USER_PROFILE", joinColumns = { @JoinColumn(name = "USER_ID") },
			inverseJoinColumns = { @JoinColumn(name = "USER_PROFILE_ID") })
	public Set<UserProfile> getUserProfiles()
	{
		return userProfiles;
	}

	public void setUserProfiles(final Set<UserProfile> userProfiles)
	{
		this.userProfiles = userProfiles;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "disciplinas_matriculadas", joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "disciplina_id "))
	public List<Disciplina> getDisciplinas()
	{
		return disciplinas;
	}

	public void setDisciplinas(final List<Disciplina> disciplinas)
	{
		this.disciplinas = disciplinas;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());

		return result;
	}

	@Override
	public boolean equals(final Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (!(obj instanceof User))
		{
			return false;
		}

		final User other = (User) obj;
		if (id == null)
		{
			if (other.id != null)
			{
				return false;
			}
		} else if (!id.equals(other.id))
		{
			return false;
		}

		if (username == null)
		{
			if (other.username != null)
			{
				return false;
			}
		} else if (!username.equals(other.username))
		{
			return false;
		}

		return true;
	}

	@Override
	public String toString()
	{
		return "User [id=" + id + ", Username=" + username + ", Name=" + name + ", email=" + email + "]";
	}

	/**
	 * @return the periodo
	 */

}
