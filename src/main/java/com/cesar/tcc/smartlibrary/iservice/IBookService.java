package com.cesar.tcc.smartlibrary.iservice;

import java.util.List;

import com.cesar.tcc.smartlibrary.entity.Book;

public interface IBookService
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
	 * @param book
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
	 * @param id
	 * @param name
	 * @return
	 */
	boolean isNameUnique(final Integer id, final String name);

	/**
	 * @param book
	 */
	void update(final Book book);

	/**
	 * @return
	 */
	List<Book> recommender();

	/**
	 * @param search
	 * @return
	 */
	List<Book> search(final String search);

}
