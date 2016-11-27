package com.cesar.tcc.smartlibrary.idao;

import java.util.List;

import com.cesar.tcc.smartlibrary.entity.Disciplina;

public interface IDisciplinaDao
{

	void persist(Disciplina entity);

	void delete(Disciplina entity);

	List<Disciplina> findAll();

	Disciplina findByName(String name);

	List<Disciplina> findaAllByUser(String username);

	void update(Disciplina disciplina);

	Disciplina getByKey(Integer id);

}
