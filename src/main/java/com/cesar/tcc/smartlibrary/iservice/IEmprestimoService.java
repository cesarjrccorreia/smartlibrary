package com.cesar.tcc.smartlibrary.iservice;

import java.util.List;

import com.cesar.tcc.smartlibrary.entity.Emprestimo;

public interface IEmprestimoService
{
	void persist(Emprestimo emprestimo);

	void delete(Emprestimo emprestimo);

	void update(Emprestimo emprestimo);

	Emprestimo findById(Integer id);

	List<Emprestimo> findAll();

	List<Emprestimo> findAllByUser(String name);

	List<Emprestimo> findEmprestimosAVencer();

}
