package com.cesar.tcc.smartlibrary.idao;

import java.util.List;

import com.cesar.tcc.smartlibrary.entity.Editora;

public interface EditoraDao {

	Editora findById(int id);

	void save(Editora editora);

	void deleteByName(String name);

	Editora findByName(String name);

	List<Editora> findAll();

}
