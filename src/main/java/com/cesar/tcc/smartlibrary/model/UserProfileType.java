package com.cesar.tcc.smartlibrary.model;

import java.io.Serializable;

public enum UserProfileType implements Serializable {

	Aluno("Aluno"), Professor("Professor"), Bibliotecaria("Bibliotecaria");

	String userProfileType;

	private UserProfileType(final String userProfileType) {
		this.userProfileType = userProfileType;
	}

	public String getUserProfileType() {
		return userProfileType;
	}

}
