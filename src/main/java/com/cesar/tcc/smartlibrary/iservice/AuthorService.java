package com.cesar.tcc.smartlibrary.iservice;

import java.util.List;

import com.cesar.tcc.smartlibrary.model.Author;

public interface AuthorService {

	Author findById(int id);

	Author findByName(String name);

	void save(Author author);

	void deleteByName(String name);

	List<Author> findAll();

	boolean isNameUnique(final Integer id, final String name);

	void updateUser(final Author author);

}
