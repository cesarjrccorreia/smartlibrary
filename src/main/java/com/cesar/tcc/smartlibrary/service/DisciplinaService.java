package com.cesar.tcc.smartlibrary.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesar.tcc.smartlibrary.entity.Disciplina;
import com.cesar.tcc.smartlibrary.idao.IDisciplinaDao;
import com.cesar.tcc.smartlibrary.iservice.IDisciplinaService;

@Service(value = "disciplinaService")
@Transactional
public class DisciplinaService implements IDisciplinaService
{
	@Autowired
	private IDisciplinaDao disciplinaDao;

	@Override
	public void persist(final Disciplina entity)
	{
		disciplinaDao.persist(entity);

	}

	@Override
	public void delete(final Disciplina entity)
	{
		disciplinaDao.delete(entity);

	}

	@Override
	public List<Disciplina> findAll()
	{
		final List<Disciplina> disciplinas = disciplinaDao.findAll();

		return disciplinas;
	}

	@Override
	public List<Disciplina> findaAllByUser(final String username)
	{
		final List<Disciplina> disciplinas = disciplinaDao.findaAllByUser(username);

		return disciplinas;
	}

	@Override
	public void update(final Disciplina disciplina)
	{
		disciplinaDao.update(disciplina);
	}

	@Override
	public Disciplina getByKey(final Integer id)
	{
		final Disciplina disciplina = disciplinaDao.getByKey(id);

		return disciplina;
	}

	@Override
	public Disciplina findByName(final String name)
	{
		final Disciplina disciplina = disciplinaDao.findByName(name);

		return disciplina;
	}

	@Override
	public boolean isNameUnique(final Integer id, final String name)
	{
		final Disciplina disciplina = findByName(name);
		return (disciplina == null || ((id != null && (disciplina.getId() == id))));
	}

}
