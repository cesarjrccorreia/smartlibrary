package com.cesar.tcc.smartlibrary.dao;

import java.util.List;

import com.cesar.tcc.smartlibrary.model.User;

public interface UserDao {

	User findById(int id);

	User findByUsername(String username);

	void save(User user);

	void deleteByUsername(String username);

	List<User> findAllUsers();

}
