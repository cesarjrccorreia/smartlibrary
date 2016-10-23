package com.cesar.tcc.smartlibrary.model;

import java.io.Serializable;

public enum UserProfileType implements Serializable {

	USER("USER"), DBA("DBA"), ADMIN("ADMIN");

	String userProfileType;

	private UserProfileType(final String userProfileType) {
		this.userProfileType = userProfileType;
	}

	public String getUserProfileType() {
		return userProfileType;
	}

}
