package com.cesar.tcc.smartlibrary.idao;

import java.util.List;

import com.cesar.tcc.smartlibrary.entity.Emprestimo;

public interface IEmprestimoDao
{
	void persist(Emprestimo emprestimo);

	void delete(Emprestimo emprestimo);

	void update(Emprestimo emprestimo);

	List<Emprestimo> findAll();

	Emprestimo findById(final Integer id);

	List<Emprestimo> findAllByUser(final String name);

	List<Emprestimo> findEmprestimosAVencer();

}
