package com.cesar.tcc.smartlibrary.iservice;

import java.util.List;

import com.cesar.tcc.smartlibrary.entity.UserProfile;

public interface IUserProfileService {

	UserProfile findById(int id);

	UserProfile findByType(String type);

	List<UserProfile> findAll();

}
