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

import com.cesar.tcc.smartlibrary.entity.Book;
import com.cesar.tcc.smartlibrary.idao.IBookDao;

/**
 * @author cesar
 *
 */
@Repository(value = "bookDao")
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
		crit.add(Restrictions.eq("name", name));

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
		crit.add(Restrictions.eq("name", name));
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

	@Override
	public void update(final Book book)
	{
		update(book);
	}

	@Override
	public List<Book> search(final String search)
	{
		final StringBuilder builder = new StringBuilder();
		builder.append("FROM Book b ");
		builder.append("WHERE lower(b.name) like :search ");
		builder.append("OR lower(b.summary) like :search ");
		builder.append("ORDER BY b.rating DESC");

		final Query query = getSession().createQuery(builder.toString());
		query.setParameter("search", "%" + search.toLowerCase() + "%");

		@SuppressWarnings("unchecked")
		final List<Book> books = query.list();

		return books;
	}

	@Override
	public List<Book> recommender()
	{
		final List<Book> books = findAll();
		return books;
	}

}
