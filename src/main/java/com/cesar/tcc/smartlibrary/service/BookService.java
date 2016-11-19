/**
 * 
 */
package com.cesar.tcc.smartlibrary.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesar.tcc.smartlibrary.entity.Book;
import com.cesar.tcc.smartlibrary.idao.IBookDao;
import com.cesar.tcc.smartlibrary.iservice.IBookService;

/**
 * @author cesar
 *
 */
@Service(value = "bookService")
@Transactional
public class BookService implements IBookService
{
	@Autowired
	IBookDao bookDao;

	@Override
	public Book findById(final int id)
	{
		final Book book = bookDao.findById(id);

		return book;
	}

	@Override
	public Book findByName(final String name)
	{
		final Book book = bookDao.findByName(name);

		return book;
	}

	@Override
	public void save(final Book book)
	{
		bookDao.save(book);
	}

	@Override
	public void deleteByName(final String name)
	{
		bookDao.deleteByName(name);
	}

	@Override
	public List<Book> findAll()
	{
		final List<Book> books = bookDao.findAll();

		return books;
	}

	@Override
	public boolean isNameUnique(final Integer id, final String name)
	{
		final Book book = findByName(name);

		return (book == null || ((id != null && (book.getId() == id))));
	}

	@Override
	public void update(final Book book)
	{
		final Integer id = book.getId();
		final Book entity = bookDao.findById(id);

		if (entity != null)
		{
			entity.setAuthors(book.getAuthors());
			entity.setEdition(book.getEdition());
			entity.setEditora(book.getEditora());
			entity.setImage(book.getImage());
			entity.setIsbn(book.getIsbn());
			entity.setName(book.getName());
			entity.setQuantity(book.getQuantity());
			entity.setSummary(book.getSummary());
			entity.setYear(book.getYear());

		}
	}

}
