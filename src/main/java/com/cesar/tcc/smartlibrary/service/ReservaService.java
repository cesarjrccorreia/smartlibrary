package com.cesar.tcc.smartlibrary.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesar.tcc.smartlibrary.entity.Reserva;
import com.cesar.tcc.smartlibrary.idao.IReservaDao;
import com.cesar.tcc.smartlibrary.iservice.IReservaService;

@Service(value = "reservaService")
@Transactional
public class ReservaService implements IReservaService
{
	@Autowired
	IReservaDao reservaDao;

	@Override
	public void persist(final Reserva entity)
	{
		reservaDao.persist(entity);

	}

	@Override
	public void delete(final Reserva entity)
	{
		reservaDao.delete(entity);

	}

	@Override
	public Reserva findById(final int id)
	{
		final Reserva reserva = reservaDao.findById(id);
		return reserva;
	}

	@Override
	public Reserva findByUser(final String name)
	{
		final Reserva reserva = reservaDao.findByUser(name);

		return reserva;
	}

	@Override
	public List<Reserva> findAll()
	{
		final List<Reserva> reservas = reservaDao.findAll();

		return reservas;
	}

}
