/**
 * 
 */
package com.cesar.tcc.smartlibrary.entity;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.cesar.tcc.smartlibrary.dao.AbstractDao;
import com.cesar.tcc.smartlibrary.idao.IBookDao;

/**
 * @author cesar
 *
 */
public class BookDao extends AbstractDao<Integer, Book> implements IBookDao
{

	@Override
	public Book findById(final int id)
	{
		final Book book = getByKey(id);

		return book;
	}

	@Override
	public Book findByName(final String name)
	{
		final Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("nome", name));

		final Book book = (Book) crit.uniqueResult();

		return book;
	}

	@Override
	public void save(final Book book)
	{
		persist(book);
	}

	@Override
	public void deleteByName(final String name)
	{
		final Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("nome", name));
		final Book book = (Book) crit.uniqueResult();

		delete(book);
	}

	@Override
	public List<Book> findAll()
	{
		final Criteria criteria = createEntityCriteria().addOrder(Order.asc("name"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		@SuppressWarnings("unchecked")
		final List<Book> books = criteria.list();

		return books;
	}

}
