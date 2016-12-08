/**
 * 
 */
package com.cesar.tcc.smartlibrary.service;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesar.tcc.smartlibrary.entity.Book;
import com.cesar.tcc.smartlibrary.entity.Disciplina;
import com.cesar.tcc.smartlibrary.idao.IBookDao;
import com.cesar.tcc.smartlibrary.iservice.IBookService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

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

	@Override
	public List<Book> recommender(final List<Disciplina> disciplinas)
	{

		final List<Book> livros = new ArrayList<>();

		for (final Disciplina disciplina : disciplinas)
		{
			final String ementa = disciplina.getEmenta();
			final String textSearch[] = ementa.split("\\r\\n|\\n|\\r");

			for (final String search : textSearch)
			{
				final List<Book> listBookByDisciplina = bookDao.search(search);

				for (final Book book : listBookByDisciplina)
				{
					if (livros.contains(book))
					{
						continue;
					}

					livros.add(book);
				}
			}
		}

		return livros;
	}

	@Override
	public List<Book> search(final String search)
	{
		final List<Book> books = bookDao.search(search);
		return books;
	}

	@Override
	public void createQRCODE(final Book book)
	{
		final String codeText = book.toString();
		final ClassLoader classLoader = getClass().getClassLoader();
		final String path = classLoader.getResource("images/qrcode.png").getFile();
		final int size = 250;
		final String type = "png";

		final File file = new File(path);

		try
		{
			final Map<EncodeHintType, Object> hintMap = new EnumMap<>(EncodeHintType.class);
			hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");

			hintMap.put(EncodeHintType.MARGIN, 1);
			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

			final QRCodeWriter qrCodeWriter = new QRCodeWriter();
			final BitMatrix byteMatrix = qrCodeWriter.encode(codeText, BarcodeFormat.QR_CODE, size, size, hintMap);
			final int crunchifyWidth = byteMatrix.getWidth();
			final BufferedImage image = new BufferedImage(crunchifyWidth, crunchifyWidth, BufferedImage.TYPE_INT_RGB);
			image.createGraphics();

			final Graphics2D graphics = (Graphics2D) image.getGraphics();
			graphics.setColor(Color.WHITE);
			graphics.fillRect(0, 0, crunchifyWidth, crunchifyWidth);
			graphics.setColor(Color.BLACK);

			for (int i = 0; i < crunchifyWidth; i++)
			{
				for (int j = 0; j < crunchifyWidth; j++)
				{
					if (byteMatrix.get(i, j))
					{
						graphics.fillRect(i, j, 1, 1);
					}
				}
			}

			ImageIO.write(image, type, file);
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
	}

}
