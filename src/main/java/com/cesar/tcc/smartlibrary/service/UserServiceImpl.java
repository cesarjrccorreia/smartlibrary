package com.cesar.tcc.smartlibrary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesar.tcc.smartlibrary.dao.UserDao;
import com.cesar.tcc.smartlibrary.model.User;

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
	public User findBySSO(final String sso) {
		final User user = userDao.findBySSO(sso);

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
			entity.setSsoId(user.getSsoId());

			if (!user.getPassword().equals(entity.getPassword())) {
				entity.setPassword(passwordEncoder.encode(user.getPassword()));
			}

			entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
			entity.setEmail(user.getEmail());
			entity.setUserProfiles(user.getUserProfiles());

		}

	}

	@Override
	public void deleteUserBySSO(final String sso) {
		userDao.deleteBySSO(sso);
	}

	@Override
	public List<User> findAllUsers() {
		return userDao.findAllUsers();
	}

	@Override
	public boolean isUserSSOUnique(final Integer id, final String sso) {
		final User user = findBySSO(sso);
		return (user == null || ((id != null && (user.getId() == id))));
	}

}
