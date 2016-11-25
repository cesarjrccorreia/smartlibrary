package com.cesar.tcc.smartlibrary.idao;

import java.util.List;

import com.cesar.tcc.smartlibrary.entity.Informe;

public interface IInformeDao
{
	/**
	 * @param entity
	 */
	void persist(Informe entity);

	void delete(Informe entity);

	/**
	 * @param id
	 * @return
	 */
	Informe findById(int id);

	/**
	 * @param name
	 * @return
	 */
	List<Informe> findAllByUser(String name);

	/**
	 * @return
	 */
	List<Informe> findAll();

	/**
	 * @param informe
	 */
	void update(Informe informe);

}
