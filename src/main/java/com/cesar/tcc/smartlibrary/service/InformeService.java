package com.cesar.tcc.smartlibrary.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesar.tcc.smartlibrary.entity.Informe;
import com.cesar.tcc.smartlibrary.idao.IInformeDao;
import com.cesar.tcc.smartlibrary.iservice.IInformeService;

@Service(value = "informeService")
@Transactional
public class InformeService implements IInformeService
{
	@Autowired
	IInformeDao informeDao;

	@Override
	public void persist(final Informe entity)
	{
		informeDao.persist(entity);
	}

	@Override
	public void delete(final Informe entity)
	{
		informeDao.delete(entity);
	}

	@Override
	public Informe findById(final int id)
	{
		final Informe informe = informeDao.findById(id);

		return informe;
	}

	@Override
	public List<Informe> findAllByUser(final String name)
	{
		final List<Informe> informes = informeDao.findAllByUser(name);

		return informes;
	}

	@Override
	public List<Informe> findAll()
	{
		final List<Informe> informes = informeDao.findAll();

		return informes;
	}

	@Override
	public void update(final Informe informe)
	{
		informeDao.update(informe);
	}

	@Override
	public List<Informe> findLastInform(final Integer limitRows)
	{
		final List<Informe> informes = informeDao.findLastInform(limitRows);

		return informes;
	}

}
