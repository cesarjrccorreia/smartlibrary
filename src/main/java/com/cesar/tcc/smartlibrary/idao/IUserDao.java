package com.cesar.tcc.smartlibrary.idao;

import java.util.List;

import com.cesar.tcc.smartlibrary.entity.User;

public interface IUserDao
{

	/**
	 * @param id
	 * @return
	 */
	User findById(int id);

	/**
	 * @param username
	 * @return
	 */
	User findByUsername(String username);

	/**
	 * @param user
	 */
	void save(User user);

	/**
	 * @param user
	 */
	void update(User user);

	/**
	 * @param username
	 */
	void deleteByUsername(String username);

	/**
	 * @return
	 */
	List<User> findAllUsers();

}
