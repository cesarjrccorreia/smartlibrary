package com.cesar.tcc.smartlibrary.dao;

import java.util.List;

import com.cesar.tcc.smartlibrary.model.UserProfile;

public interface UserProfileDao {

	List<UserProfile> findAll();

	UserProfile findByType(String type);

	UserProfile findById(int id);
}
