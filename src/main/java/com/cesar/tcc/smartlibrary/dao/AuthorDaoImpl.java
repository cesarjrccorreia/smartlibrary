package com.cesar.tcc.smartlibrary.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cesar.tcc.smartlibrary.idao.AuthorDao;
import com.cesar.tcc.smartlibrary.model.Author;

@Repository("authorDao")
public class AuthorDaoImpl extends AbstractDao<Integer, Author> implements AuthorDao {

	@Override
	public Author findById(final int id) {
		final Author author = getByKey(id);

		return author;
	}

	@Override
	public void save(final Author author) {
		persist(author);

	}

	@Override
	public void deleteByName(final String name) {
		final Author author = findByName(name);

		delete(author);

	}

	@Override
	public List<Author> findAll() {
		final Criteria criteria = createEntityCriteria().addOrder(Order.asc("name"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		@SuppressWarnings("unchecked")
		final List<Author> list = criteria.list();

		return list;
	}

	@Override
	public Author findByName(final String name) {

		final Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("name", name));

		final Author author = (Author) criteria.uniqueResult();

		return author;
	}

}
