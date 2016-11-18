package com.cesar.tcc.smartlibrary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesar.tcc.smartlibrary.entity.UserProfile;
import com.cesar.tcc.smartlibrary.idao.IUserProfileDao;
import com.cesar.tcc.smartlibrary.iservice.IUserProfileService;

@Service("userProfileService")
@Transactional
public class UserProfileService implements IUserProfileService {

	@Autowired
	IUserProfileDao dao;

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
