package com.cesar.tcc.smartlibrary.iservice;

import java.util.List;

import com.cesar.tcc.smartlibrary.entity.Disciplina;

public interface IDisciplinaService
{

	void persist(Disciplina entity);

	void delete(Disciplina entity);

	List<Disciplina> findAll();

	List<Disciplina> findaAllByUser(String username);

	void update(Disciplina disciplina);

	Disciplina getByKey(Integer id);

	Disciplina findByName(String name);

	boolean isNameUnique(final Integer id, final String name);

}
