/**
 * 
 */
package com.cesar.tcc.smartlibrary.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cesar.tcc.smartlibrary.entity.Disciplina;
import com.cesar.tcc.smartlibrary.idao.IDisciplinaDao;

/**
 * @author cesar
 *
 */
@Repository(value = "disciplinaDao")
public class DisciplinaDao extends AbstractDao<Integer, Disciplina> implements IDisciplinaDao
{

	@Override
	public List<Disciplina> findAll()
	{
		final Criteria criteria = createEntityCriteria().addOrder(Order.asc("name"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		@SuppressWarnings("unchecked")
		final List<Disciplina> disciplinas = criteria.list();

		return disciplinas;
	}

	@Override
	public Disciplina findByName(final String name)
	{
		final Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("name", name));

		final Disciplina disciplina = (Disciplina) crit.uniqueResult();

		return disciplina;
	}

	@Override
	public List<Disciplina> findaAllByUser(final String username)
	{
		final StringBuilder builder = new StringBuilder();
		builder.append("FROM Disciplina d ");
		builder.append("INNER JOIN User u ");
		builder.append("ON u.id = d.user.id ");
		builder.append("AND u.name = :name ");

		final Query query = getSession().createQuery(builder.toString());
		query.setParameter("name", username);

		@SuppressWarnings("unchecked")
		final List<Disciplina> disciplinas = query.list();

		return disciplinas;
	}

}
