package com.cesar.tcc.smartlibrary.service;

import java.util.List;

import com.cesar.tcc.smartlibrary.model.UserProfile;

public interface UserProfileService {

	UserProfile findById(int id);

	UserProfile findByType(String type);

	List<UserProfile> findAll();

}
