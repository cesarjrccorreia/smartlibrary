package com.cesar.tcc.smartlibrary.controller;

import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cesar.tcc.smartlibrary.entity.Book;
import com.cesar.tcc.smartlibrary.entity.Disciplina;
import com.cesar.tcc.smartlibrary.entity.Emprestimo;
import com.cesar.tcc.smartlibrary.entity.Informe;
import com.cesar.tcc.smartlibrary.entity.Reserva;
import com.cesar.tcc.smartlibrary.entity.User;
import com.cesar.tcc.smartlibrary.iservice.IBookService;
import com.cesar.tcc.smartlibrary.iservice.IEmprestimoService;
import com.cesar.tcc.smartlibrary.iservice.IInformeService;
import com.cesar.tcc.smartlibrary.iservice.IReservaService;
import com.cesar.tcc.smartlibrary.iservice.IUserService;
import com.cesar.tcc.smartlibrary.utilities.Constants;

@Controller(value = "mainController")
@RequestMapping(value = "/")
public class MainController extends AppController
{
	@Autowired
	private IInformeService informeService;

	@Autowired
	private IBookService bookService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IEmprestimoService emprestimoService;

	@Autowired
	private IReservaService reservaService;

	@RequestMapping
	public String startMainPage(final ModelMap modelMap)
	{
		findEmprestimoAVencer();

		final String username = (String) getPrincipal();
		modelMap.addAttribute("loggedinuser", username);

		final User user = userService.findByUsername(username);
		final List<Disciplina> disciplinas = user.getDisciplinas();
		modelMap.addAttribute("disciplinas", disciplinas);

		final List<Emprestimo> emprestimos = emprestimoService.findAllByUser(username);
		modelMap.addAttribute("emprestimos", emprestimos);

		final List<Reserva> reservas = reservaService.findByUser(username);
		modelMap.addAttribute("reservas", reservas);

		final List<Informe> informes = informeService.findLastInform(Constants.LIMIT_ROWS);
		modelMap.addAttribute("informes", informes);

		final List<Book> livros = bookService.recommender(disciplinas);
		modelMap.addAttribute("books", livros);

		return Constants.MAIN_PAGE;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String searchBook(final ModelMap modelMap, @RequestParam final String search)
	{
		final List<Book> book = bookService.search(search);

		modelMap.addAttribute("books", book);
		modelMap.addAttribute("search", search);
		modelMap.addAttribute("loggedinuser", getPrincipal());

		return Constants.SEARCH_PAGE;
	}

	@RequestMapping(value = "/informe/new")
	public String addInforme(final ModelMap modelMap)
	{
		final Informe informe = new Informe();
		modelMap.addAttribute("informe", informe);
		return Constants.FORM_INFORME;
	}

	@RequestMapping(value = "/informe/new", method = RequestMethod.POST)
	public String saveInforme(@Valid final Informe informe, final BindingResult result,
			final RedirectAttributes redirect)
	{
		if (result.hasErrors())
		{
			return Constants.FORM_INFORME;
		}

		final String userName = (String) getPrincipal();
		final User user = userService.findByUsername(userName);
		informe.setUser(user);
		informeService.persist(informe);

		final String[] parameters = new String[] { "informe" };
		final Locale locale = Locale.getDefault();
		redirect.addFlashAttribute("success", messageSource.getMessage("msg.saved", parameters, locale));

		final String redirectMsg = String.format("redirect:%s", "/");

		return redirectMsg;
	}

	protected void findEmprestimoAVencer()
	{
		final List<Emprestimo> emprestimos = emprestimoService.findEmprestimosAVencer();

		if (emprestimos.isEmpty())
		{
			return;
		}

		sendEmail(emprestimos);

	}

	protected void sendEmail(final List<Emprestimo> emprestimos)
	{
		final String from = "smartlibraryteste@gmail.com";
		final String username = from;
		final String password = "SmartLibrary2712";
		final Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");

		final Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication(username, password);
			}
		});

		try
		{
			for (final Emprestimo emprestimo : emprestimos)
			{
				final User user = emprestimo.getUser();
				final String to = user.getEmail();
				final Message message = new MimeMessage(session);
				final InternetAddress internetAddressFrom = new InternetAddress(from);
				message.setFrom(internetAddressFrom);

				final InternetAddress internetAddressTo = new InternetAddress(to);
				message.addRecipient(Message.RecipientType.TO, internetAddressTo);

				message.setSubject("SmartLibrary - Empréstimo próximo a vencer");

				final Book book = emprestimo.getBook();
				final String bookName = book.getName();
				final String limitDate = emprestimo.getLimitDateString();
				final String text = String.format("O emprestimo do livro %s vencerá em %s", bookName, limitDate);
				message.setText(text);

				Transport.send(message);
			}

		}

		catch (final Exception e)
		{
			e.printStackTrace();
		}

	}

}
