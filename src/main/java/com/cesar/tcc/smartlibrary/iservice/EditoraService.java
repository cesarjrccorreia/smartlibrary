package com.cesar.tcc.smartlibrary.iservice;

import java.util.List;

import com.cesar.tcc.smartlibrary.entity.Editora;

public interface EditoraService {

	Editora findById(int id);

	Editora findByName(String name);

	void save(Editora editora);

	void deleteByName(String name);

	List<Editora> findAll();

	boolean isNameUnique(final Integer id, final String name);

	void update(final Editora editora);

}
