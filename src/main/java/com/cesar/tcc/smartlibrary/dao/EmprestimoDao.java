package com.cesar.tcc.smartlibrary.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.cesar.tcc.smartlibrary.entity.Emprestimo;
import com.cesar.tcc.smartlibrary.idao.IEmprestimoDao;

@Repository(value = "emprestimoDao")
public class EmprestimoDao extends AbstractDao<Integer, Emprestimo> implements IEmprestimoDao
{

	@Override
	public List<Emprestimo> findAll()
	{
		final Criteria criteria = createEntityCriteria().addOrder(Order.asc("name"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		@SuppressWarnings("unchecked")
		final List<Emprestimo> emprestimos = criteria.list();

		return emprestimos;
	}

	@Override
	public Emprestimo findById(final Integer id)
	{
		final Emprestimo emprestimo = getByKey(id);

		return emprestimo;
	}

	@Override
	public List<Emprestimo> findAllByUser(final String name)
	{
		final StringBuilder builder = new StringBuilder();
		builder.append("FROM Emprestimo e");
		builder.append("INNER JOIN User u");
		builder.append("ON e.user.id = u.id AND u.name = :name ");

		final Query query = getSession().createQuery(builder.toString());

		@SuppressWarnings("unchecked")
		final List<Emprestimo> emprestimos = query.list();
		return emprestimos;
	}

}
