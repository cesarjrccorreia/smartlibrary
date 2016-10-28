package com.cesar.tcc.smartlibrary.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cesar.tcc.smartlibrary.idao.UserDao;
import com.cesar.tcc.smartlibrary.model.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	@Override
	public User findById(final int id) {

		final User user = getByKey(id);
		if (user != null) {
			Hibernate.initialize(user.getUserProfiles());
		}

		return user;
	}

	@Override
	public User findByUsername(final String username) {
		final Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("username", username));

		final User user = (User) crit.uniqueResult();
		if (user != null) {
			Hibernate.initialize(user.getUserProfiles());
		}

		return user;
	}

	@Override
	public void save(final User user) {
		persist(user);

	}

	@Override
	public void deleteByUsername(final String username) {
		final Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("username", username));
		final User user = (User) crit.uniqueResult();

		delete(user);

	}

	@Override
	public List<User> findAllUsers() {

		final Criteria criteria = createEntityCriteria().addOrder(Order.asc("name"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		@SuppressWarnings("unchecked")
		final List<User> users = criteria.list();

		return users;
	}

}
