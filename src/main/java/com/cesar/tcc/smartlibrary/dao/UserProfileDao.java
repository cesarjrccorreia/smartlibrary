package com.cesar.tcc.smartlibrary.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cesar.tcc.smartlibrary.entity.UserProfile;
import com.cesar.tcc.smartlibrary.idao.IUserProfileDao;

@Repository("userProfileDao")
public class UserProfileDao extends AbstractDao<Integer, UserProfile> implements IUserProfileDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<UserProfile> findAll() {

		final Criteria crit = createEntityCriteria();
		crit.addOrder(Order.asc("type"));
		return crit.list();
	}

	@Override
	public UserProfile findByType(final String type) {

		final Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("type", type));
		return (UserProfile) crit.uniqueResult();
	}

	@Override
	public UserProfile findById(final int id) {

		return getByKey(id);
	}

}
