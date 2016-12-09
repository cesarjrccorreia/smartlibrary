package com.cesar.tcc.smartlibrary.dao;

import java.util.Calendar;
import java.util.Date;
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
		builder.append("SELECT e ");
		builder.append("FROM Emprestimo e ");
		builder.append("INNER JOIN e.user u ");
		builder.append("WHERE u.username like :name ");
		builder.append("AND e.fim is null");

		final Query query = getSession().createQuery(builder.toString());
		query.setParameter("name", name);

		@SuppressWarnings("unchecked")
		final List<Emprestimo> emprestimos = query.list();
		return emprestimos;
	}

	@Override
	public List<Emprestimo> findEmprestimosAVencer()
	{
		final StringBuilder builder = new StringBuilder();
		builder.append("FROM Emprestimo e ");
		builder.append("WHERE e.fim is null ");

		final Query query = getSession().createQuery(builder.toString());

		@SuppressWarnings("unchecked")
		final List<Emprestimo> emprestimos = query.list();

		for (final Emprestimo emprestimo : emprestimos)
		{
			final Date limitDate = emprestimo.getLimitDate();
			final Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			final Date currentDate = calendar.getTime();

			if (!currentDate.equals(limitDate))
			{
				emprestimos.remove(emprestimo);
			}
		}

		return emprestimos;
	}

}
