package com.cesar.tcc.smartlibrary.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesar.tcc.smartlibrary.idao.EditoraDao;
import com.cesar.tcc.smartlibrary.iservice.EditoraService;
import com.cesar.tcc.smartlibrary.model.Editora;

@Service(value = "editoraService")
@Transactional
public class EditoraServiceImpl implements EditoraService {

	@Autowired
	private EditoraDao editoraDao;

	@Override
	public Editora findById(final int id) {
		final Editora editora = editoraDao.findById(id);

		return editora;
	}

	@Override
	public Editora findByName(final String name) {
		final Editora editora = editoraDao.findByName(name);
		return editora;
	}

	@Override
	public void save(final Editora editora) {
		editoraDao.save(editora);
	}

	@Override
	public void deleteByName(final String name) {
		editoraDao.deleteByName(name);
	}

	@Override
	public List<Editora> findAll() {
		final List<Editora> editoras = editoraDao.findAll();

		return editoras;
	}

	@Override
	public boolean isNameUnique(final Integer id, final String name) {
		final Editora editora = findByName(name);

		return (editora == null || ((id != null && (editora.getId() == id))));
	}

	@Override
	public void update(final Editora editora) {
		final Integer id = editora.getId();
		final Editora entity = editoraDao.findById(id);

		if (entity != null) {

			entity.setId(id);

			final String nome = editora.getName();
			entity.setName(nome);
		}
	}

}
