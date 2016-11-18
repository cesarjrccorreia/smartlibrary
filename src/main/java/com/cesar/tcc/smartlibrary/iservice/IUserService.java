package com.cesar.tcc.smartlibrary.iservice;

import java.util.List;

import com.cesar.tcc.smartlibrary.entity.User;

public interface IUserService {

	User findById(int id);

	User findByUsername(String username);

	void saveUser(User user);

	void updateUser(User user);

	void deleteUserByUsername(String username);

	List<User> findAllUsers();

	boolean isUsernameUnique(Integer id, String username);

}
