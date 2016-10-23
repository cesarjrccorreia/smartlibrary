package com.cesar.tcc.smartlibrary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesar.tcc.smartlibrary.dao.UserProfileDao;
import com.cesar.tcc.smartlibrary.model.UserProfile;

@Service("userProfileService")
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

	@Autowired
	UserProfileDao dao;

	@Override
	public UserProfile findById(final int id) {
		return dao.findById(id);
	}

	@Override
	public UserProfile findByType(final String type) {
		return dao.findByType(type);
	}

	@Override
	public List<UserProfile> findAll() {
		return dao.findAll();
	}

}
