package com.cesar.tcc.smartlibrary.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.cesar.tcc.smartlibrary.entity.Reserva;
import com.cesar.tcc.smartlibrary.idao.IReservaDao;

@Repository(value = "reservaDao")
public class ReservaDao extends AbstractDao<Integer, Reserva> implements IReservaDao
{

	@Override
	public Reserva findById(final int id)
	{
		final Reserva reserva = getByKey(id);
		return reserva;
	}

	@Override
	public List<Reserva> findByUser(final String name)
	{
		final StringBuilder builder = new StringBuilder();
		builder.append("FROM Reserva r ");
		builder.append("INNER JOIN r.user u ");
		builder.append("WHERE u.username like :name ");
		final Query query = getSession().createQuery(builder.toString());
		query.setParameter("name", name);

		@SuppressWarnings("unchecked")
		final List<Reserva> reservas = query.list();

		return reservas;

	}

	@Override
	public List<Reserva> findAll()
	{
		final Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		@SuppressWarnings("unchecked")
		final List<Reserva> reservas = criteria.list();

		return reservas;
	}

}
