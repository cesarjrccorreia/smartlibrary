package com.cesar.tcc.smartlibrary.idao;

import java.util.List;

import com.cesar.tcc.smartlibrary.entity.Author;

public interface IAuthorDao {

	Author findById(int id);

	void save(Author author);

	void deleteByName(String name);

	Author findByName(String name);

	List<Author> findAll();

}
