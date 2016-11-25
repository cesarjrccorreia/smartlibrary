package com.cesar.tcc.smartlibrary.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesar.tcc.smartlibrary.entity.Emprestimo;
import com.cesar.tcc.smartlibrary.idao.IEmprestimoDao;
import com.cesar.tcc.smartlibrary.iservice.IEmprestimoService;

@Service(value = "emprestimoService")
@Transactional
public class EmprestimoService implements IEmprestimoService
{

	@Autowired
	IEmprestimoDao emprestimoDao;

	@Override
	public void persist(final Emprestimo emprestimo)
	{
		emprestimoDao.persist(emprestimo);

	}

	@Override
	public void delete(final Emprestimo emprestimo)
	{
		emprestimoDao.delete(emprestimo);

	}

	@Override
	public void update(final Emprestimo emprestimo)
	{
		emprestimoDao.update(emprestimo);
	}

	@Override
	public Emprestimo findById(final Integer id)
	{
		final Emprestimo emprestimo = emprestimoDao.findById(id);
		return emprestimo;
	}

	@Override
	public List<Emprestimo> findAll()
	{
		final List<Emprestimo> emprestimos = emprestimoDao.findAll();

		return emprestimos;
	}

	@Override
	public List<Emprestimo> findAllByUser(final String name)
	{
		final List<Emprestimo> emprestimos = emprestimoDao.findAllByUser(name);

		return emprestimos;
	}

}
