package com.cesar.tcc.smartlibrary.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cesar.tcc.smartlibrary.entity.Editora;
import com.cesar.tcc.smartlibrary.idao.IEditoraDao;

@Repository(value = "editoraDao")
public class EditoraDao extends AbstractDao<Integer, Editora> implements IEditoraDao {

	@Override
	public Editora findById(final int id) {

		final Editora editora = getByKey(id);

		return editora;
	}

	@Override
	public void save(final Editora editora) {
		persist(editora);
	}

	@Override
	public void deleteByName(final String name) {
		final Editora editora = findByName(name);

		delete(editora);

	}

	@Override
	public Editora findByName(final String name) {
		final Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eqOrIsNull("name", name));

		final Editora editora = (Editora) criteria.uniqueResult();

		return editora;
	}

	@Override
	public List<Editora> findAll() {

		final Criteria criteria = createEntityCriteria().addOrder(Order.asc("name"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		@SuppressWarnings("unchecked")
		final List<Editora> editoras = criteria.list();

		return editoras;

	}

}
