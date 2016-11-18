package com.cesar.tcc.smartlibrary.dao;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cesar.tcc.smartlibrary.entity.PersistentLogin;

@Repository("tokenRepositoryDao")
@Transactional
public class HibernateTokenRepositoryImpl extends AbstractDao<String, PersistentLogin>
		implements PersistentTokenRepository {

	@Override
	public void createNewToken(final PersistentRememberMeToken token) {
		final PersistentLogin persistentLogin = new PersistentLogin();

		persistentLogin.setUsername(token.getUsername());
		persistentLogin.setSeries(token.getSeries());
		persistentLogin.setToken(token.getTokenValue());
		persistentLogin.setLastUsed(token.getDate());

		persist(persistentLogin);

	}

	@Override
	public PersistentRememberMeToken getTokenForSeries(final String seriesId) {
		final Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("series", seriesId));
		final PersistentLogin persistentLogin = (PersistentLogin) crit.uniqueResult();

		return new PersistentRememberMeToken(persistentLogin.getUsername(), persistentLogin.getSeries(),
				persistentLogin.getToken(), persistentLogin.getLastUsed());

	}

	@Override
	public void removeUserTokens(final String username) {

		final Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("username", username));
		final PersistentLogin persistentLogin = (PersistentLogin) crit.uniqueResult();

		if (persistentLogin != null) {
			delete(persistentLogin);
		}
	}

	@Override
	public void updateToken(final String seriesId, final String tokenValue, final Date lastUsed) {

		final PersistentLogin persistentLogin = getByKey(seriesId);
		persistentLogin.setToken(tokenValue);
		persistentLogin.setLastUsed(lastUsed);

		update(persistentLogin);
	}

}
