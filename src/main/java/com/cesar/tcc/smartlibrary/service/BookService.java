/**
 * 
 */
package com.cesar.tcc.smartlibrary.service;

import java.util.List;

import com.cesar.tcc.smartlibrary.entity.Book;
import com.cesar.tcc.smartlibrary.iservice.IBookService;

/**
 * @author cesar
 *
 */
public class BookService implements IBookService
{

	@Override
	public Book findById(final int id)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book findByName(final String name)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(final Book book)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteByName(final String name)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public List<Book> findAll()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isNameUnique(final Integer id, final String name)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update(final Book book)
	{
		// TODO Auto-generated method stub

	}

}
