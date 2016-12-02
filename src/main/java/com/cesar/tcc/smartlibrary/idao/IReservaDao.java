package com.cesar.tcc.smartlibrary.idao;

import java.util.List;

import com.cesar.tcc.smartlibrary.entity.Reserva;

public interface IReservaDao
{
	/**
	 * @param entity
	 */
	void persist(Reserva entity);

	/**
	 * @param entity
	 */
	void delete(Reserva entity);

	/**
	 * @param id
	 * @return
	 */
	Reserva findById(int id);

	/**
	 * @param name
	 * @return
	 */
	List<Reserva> findByUser(String name);

	/**
	 * @return
	 */
	List<Reserva> findAll();

}
