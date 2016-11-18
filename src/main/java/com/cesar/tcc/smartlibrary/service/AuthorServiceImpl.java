package com.cesar.tcc.smartlibrary.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesar.tcc.smartlibrary.entity.Author;
import com.cesar.tcc.smartlibrary.idao.AuthorDao;
import com.cesar.tcc.smartlibrary.iservice.AuthorService;

@Service("authorService")
@Transactional
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	AuthorDao authorDao;

	@Override
	public Author findById(final int id) {

		final Author author = authorDao.findById(id);

		return author;
	}

	@Override
	public void save(final Author author) {
		authorDao.save(author);

	}

	@Override
	public void deleteByName(final String name) {
		authorDao.deleteByName(name);

	}

	@Override
	public List<Author> findAll() {
		final List<Author> list = authorDao.findAll();

		return list;
	}

	@Override
	public boolean isNameUnique(final Integer id, final String name) {
		final Author author = findByName(name);

		return (author == null || ((id != null && (author.getId() == id))));
	}

	@Override
	public void updateAuthor(final Author author) {

		final Author entity = authorDao.findById(author.getId());

		if (entity != null) {

			entity.setName(author.getName());

		}

	}

	@Override
	public Author findByName(final String name) {
		final Author author = authorDao.findByName(name);
		return author;
	}

}
