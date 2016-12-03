package com.cesar.tcc.smartlibrary.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.cesar.tcc.smartlibrary.entity.Informe;
import com.cesar.tcc.smartlibrary.idao.IInformeDao;

@Repository(value = "informeDao")
public class InformeDao extends AbstractDao<Integer, Informe> implements IInformeDao
{

	@Override
	public Informe findById(final int id)
	{
		final Informe informe = getByKey(id);

		return informe;
	}

	@Override
	public List<Informe> findAllByUser(final String name)
	{
		final StringBuilder builder = new StringBuilder();
		builder.append("FROM Informe i");
		builder.append("INNER JOIN i.user u");
		builder.append("WHERE u.username like :name");

		final Query query = getSession().createQuery(builder.toString());
		query.setParameter("name", name);

		@SuppressWarnings("unchecked")
		final List<Informe> informes = query.list();

		return informes;
	}

	@Override
	public List<Informe> findAll()
	{
		final Criteria criteria = createEntityCriteria().addOrder(Order.desc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		@SuppressWarnings("unchecked")
		final List<Informe> informes = criteria.list();

		return informes;
	}

	@Override
	public List<Informe> findLastInform(final Integer limitRows)
	{
		final Criteria criteria = createEntityCriteria().addOrder(Order.desc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.setMaxResults(limitRows);

		@SuppressWarnings("unchecked")
		final List<Informe> informes = criteria.list();

		return informes;
	}

}
