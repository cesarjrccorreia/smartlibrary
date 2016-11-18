package com.cesar.tcc.smartlibrary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesar.tcc.smartlibrary.entity.User;
import com.cesar.tcc.smartlibrary.idao.UserDao;
import com.cesar.tcc.smartlibrary.iservice.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User findById(final int id) {
		return userDao.findById(id);
	}

	@Override
	public User findByUsername(final String username) {
		final User user = userDao.findByUsername(username);

		return user;
	}

	@Override
	public void saveUser(final User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		userDao.save(user);

	}

	@Override
	public void updateUser(final User user) {
		final User entity = userDao.findById(user.getId());

		if (entity != null) {
			entity.setUsername(user.getUsername());

			if (!user.getPassword().equals(entity.getPassword())) {
				entity.setPassword(passwordEncoder.encode(user.getPassword()));
			}

			entity.setName(user.getName());
			entity.setEmail(user.getEmail());
			entity.setUserProfiles(user.getUserProfiles());

		}

	}

	@Override
	public void deleteUserByUsername(final String username) {
		userDao.deleteByUsername(username);
	}

	@Override
	public List<User> findAllUsers() {
		return userDao.findAllUsers();
	}

	@Override
	public boolean isUsernameUnique(final Integer id, final String username) {
		final User user = findByUsername(username);
		return (user == null || ((id != null && (user.getId() == id))));
	}

}
