package com.cesar.tcc.smartlibrary.idao;

import java.util.List;

import com.cesar.tcc.smartlibrary.entity.Book;

public interface IBookDao
{

	/**
	 * @param id
	 * @return
	 */
	Book findById(int id);

	/**
	 * @param name
	 * @return
	 */
	Book findByName(String name);

	/**
	 * @param user
	 */
	void save(Book book);

	/**
	 * @param name
	 */
	void deleteByName(String name);

	/**
	 * @return
	 */
	List<Book> findAll();

	/**
	 * @param book
	 */
	void update(Book book);

	/**
	 * @param search
	 * @return
	 */
	List<Book> search(final String search);

	/**
	 * @return
	 */
	List<Book> recommender();

}
