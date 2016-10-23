package com.cesar.tcc.smartlibrary.service;

import java.util.List;

import com.cesar.tcc.smartlibrary.model.User;

public interface UserService {

	User findById(int id);

	User findByUsername(String username);

	void saveUser(User user);

	void updateUser(User user);

	void deleteUserByUsername(String username);

	List<User> findAllUsers();

	boolean isUsernameUnique(Integer id, String username);

}
