package com.cesar.tcc.smartlibrary.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "PERSISTENT_LOGINS")
public class PersistentLogin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String series;

	@Column(name = "USERNAME", unique = true, nullable = false)
	private String username;

	@Column(name = "TOKEN", unique = true, nullable = false)
	private String token;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUsed;

	public String getSeries() {
		return series;
	}

	public String getUsername() {
		return username;
	}

	public String getToken() {
		return token;
	}

	public Date getLastUsed() {
		return lastUsed;
	}

	public void setSeries(final String series) {
		this.series = series;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public void setToken(final String token) {
		this.token = token;
	}

	public void setLastUsed(final Date lastUsed) {
		this.lastUsed = lastUsed;
	}
}
