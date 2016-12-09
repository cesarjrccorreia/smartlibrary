package com.cesar.tcc.smartlibrary.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cesar.tcc.smartlibrary.entity.Author;
import com.cesar.tcc.smartlibrary.entity.Book;
import com.cesar.tcc.smartlibrary.entity.Comentario;
import com.cesar.tcc.smartlibrary.entity.Editora;
import com.cesar.tcc.smartlibrary.entity.User;
import com.cesar.tcc.smartlibrary.iservice.IAuthorService;
import com.cesar.tcc.smartlibrary.iservice.IBookService;
import com.cesar.tcc.smartlibrary.iservice.IComentarioService;
import com.cesar.tcc.smartlibrary.iservice.IEditoraService;
import com.cesar.tcc.smartlibrary.iservice.IUserService;
import com.cesar.tcc.smartlibrary.utilities.Constants;

@Controller(value = "bookController")
@RequestMapping(value = "/books")
public class BookController extends AppController
{

	@Autowired
	IBookService bookService;

	@Autowired
	IAuthorService authorService;

	@Autowired
	IEditoraService editoraService;

	@Autowired
	IUserService userService;

	@Autowired
	IComentarioService comentarioService;

	@RequestMapping
	public String listAll(final ModelMap model)
	{
		final List<Book> books = bookService.findAll();
		model.addAttribute("books", books);
		model.addAttribute("loggedinuser", getPrincipal());

		return Constants.BOOK_PAGE;
	}

	@ModelAttribute("allAuthors")
	public List<Author> initializeAuthor()
	{
		return authorService.findAll();
	}

	@ModelAttribute("allEditoras")
	public List<Editora> initializeEditora()
	{
		return editoraService.findAll();
	}

	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String newBook(final ModelMap model)
	{

		final Book book = new Book();
		model.addAttribute("book", book);
		model.addAttribute("edit", false);

		return Constants.FORM_BOOK;
	}

	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String save(@Valid final Book book, final BindingResult result, final RedirectAttributes redirect,
			final ModelMap model)
	{

		if (result.hasErrors())
		{
			return Constants.FORM_BOOK;
		}

		final Locale locale = Locale.getDefault();
		if (!bookService.isNameUnique(book.getId(), book.getName()))
		{
			final FieldError nameError = new FieldError("book", "name",
					messageSource.getMessage("non.unique.name", new String[] { book.getName() }, locale));
			result.addError(nameError);

			return Constants.FORM_BOOK;
		}

		bookService.save(book);
		final String[] arguments = new String[] {};

		redirect.addFlashAttribute("success", messageSource.getMessage("msg.saved", arguments, locale));

		final String redirectMsg = String.format("redirect:/%s", Constants.BOOK_PAGE);
		return redirectMsg;
	}

	@RequestMapping(value = { "/edit-{name}" }, method = RequestMethod.GET)
	public String edit(@PathVariable final String name, final ModelMap model)
	{
		final Book book = bookService.findByName(name);
		model.addAttribute("book", book);
		model.addAttribute("edit", true);

		return Constants.FORM_BOOK;
	}

	@RequestMapping(value = { "/edit-{name}" }, method = RequestMethod.POST)
	public String updateBook(@Valid final Book book, final BindingResult result, final RedirectAttributes redirect)
	{

		if (result.hasErrors())
		{
			return Constants.FORM_BOOK;
		}

		bookService.update(book);

		final String[] parameters = new String[] { book.getName() };
		final Locale locale = Locale.getDefault();
		redirect.addFlashAttribute("success", messageSource.getMessage("msg.updated", parameters, locale));

		final String redirectMsg = String.format("redirect:/%s", Constants.BOOK_PAGE);
		return redirectMsg;
	}

	@RequestMapping(value = { "/delete-{name}" }, method = RequestMethod.GET)
	public String deleteAuthor(@PathVariable final String name, final RedirectAttributes redirect)
	{

		bookService.deleteByName(name);

		final Locale locale = Locale.getDefault();

		redirect.addFlashAttribute("success", messageSource.getMessage("msg.deleted", null, locale));

		final String redirectMsg = String.format("redirect:/%s", Constants.BOOK_PAGE);
		return redirectMsg;
	}

	@RequestMapping(value = "/reservar-{id}")
	public String reservar(@PathVariable final Integer id, final RedirectAttributes redirect)
	{
		final String[] parameters = new String[] {};
		final Locale locale = Locale.getDefault();
		redirect.addFlashAttribute("success", messageSource.getMessage("msg.reserved", parameters, locale));

		final String redirectMsg = String.format("redirect:/%s", "");

		return redirectMsg;
	}

	@RequestMapping(value = "/indicar-{id}")
	public String indicar(@PathVariable final Integer id, final RedirectAttributes redirect)
	{
		final String[] parameters = new String[] {};
		final Locale locale = Locale.getDefault();
		redirect.addFlashAttribute("success", messageSource.getMessage("msg.indicado", parameters, locale));

		final String redirectMsg = String.format("redirect:/%s", "");

		return redirectMsg;
	}

	@RequestMapping(value = "comentar-{id}")
	public String comentar(@PathVariable final Integer id, final ModelMap model)
	{
		final Comentario comentario = new Comentario();
		final Book book = bookService.findById(id);
		comentario.setBook(book);
		model.addAttribute("comment", comentario);
		model.addAttribute("loggedinuser", getPrincipal());

		return Constants.FORM_COMENTAR;
	}

	@RequestMapping(value = "comentar-{bookId}", method = RequestMethod.POST)
	public String saveComment(final Comentario comentario, @PathVariable final Integer bookId,
			final RedirectAttributes redirect)
	{
		final Book book = bookService.findById(bookId);
		comentario.setBook(book);
		final String username = (String) getPrincipal();
		final User user = userService.findByUsername(username);
		comentario.setUser(user);

		final String[] parameters = new String[] {};
		final Locale locale = Locale.getDefault();
		final String redirectMsg = String.format("redirect:/books/detail-%s", bookId);

		try
		{
			comentarioService.save(comentario);
			recalcularClassificacao(book);
			redirect.addFlashAttribute("success", messageSource.getMessage("msg.commented", parameters, locale));

		}
		catch (final Exception e)
		{
			e.printStackTrace();
			redirect.addFlashAttribute("error", messageSource.getMessage("msg.erro.commented", parameters, locale));
		}

		return redirectMsg;
	}

	private void recalcularClassificacao(final Book book)
	{
		final List<Comentario> comentarios = book.getComments();

		Double rating = book.getRating();
		for (final Comentario comentario : comentarios)
		{
			rating += comentario.getRating().doubleValue();
		}

		rating = rating / comentarios.size();

		book.setRating(rating);

		bookService.update(book);

	}

	@RequestMapping(value = "/detail-{id}")
	public String showDetail(@PathVariable final Integer id, final ModelMap model)
	{
		final Book book = bookService.findById(id);
		model.addAttribute("book", book);

		return Constants.DETAIL_BOOK;
	}

	@RequestMapping(value = "/qrcode")
	public String generateQrCode(final RedirectAttributes redirect)
	{
		final String[] parameters = new String[] {};
		final Locale locale = Locale.getDefault();
		final String redirectMsg = String.format("redirect:/%s", "");

		final Book book = bookService.findById(6);

		bookService.createQRCODE(book);
		redirect.addFlashAttribute("success", messageSource.getMessage("msg.commented", parameters, locale));

		return redirectMsg;
	}

}
