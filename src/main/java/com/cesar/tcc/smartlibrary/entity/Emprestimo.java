package com.cesar.tcc.smartlibrary.entity;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Emprestimo
{

	private Integer id;

	private User user;

	private Book book;

	private Date inicio;

	private Date fim;

	private Integer periodo;

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId()
	{
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the user
	 */
	@ManyToOne
	@JoinColumn(name = "user_id")
	public User getUser()
	{
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(final User user)
	{
		this.user = user;
	}

	/**
	 * @return the book
	 */
	@ManyToOne
	@JoinColumn(name = "livro_id")
	public Book getBook()
	{
		return book;
	}

	/**
	 * @param book
	 *            the book to set
	 */
	public void setBook(final Book book)
	{
		this.book = book;
	}

	/**
	 * @return the inicio
	 */
	@DateTimeFormat
	public Date getInicio()
	{
		return inicio;
	}

	/**
	 * @param inicio
	 *            the inicio to set
	 */
	public void setInicio(final Date inicio)
	{
		this.inicio = inicio;
	}

	/**
	 * @return the fim
	 */
	@DateTimeFormat
	public Date getFim()
	{
		return fim;
	}

	/**
	 * @param fim
	 *            the fim to set
	 */
	public void setFim(final Date fim)
	{
		this.fim = fim;
	}

	/**
	 * @return the periodo
	 */
	@NotNull
	public Integer getPeriodo()
	{
		return periodo;
	}

	/**
	 * @param periodo
	 *            the periodo to set
	 */
	public void setPeriodo(final Integer periodo)
	{
		this.periodo = periodo;
	}

	@Transient
	public boolean isLate()
	{
		final LocalDateTime currentTime = LocalDateTime.now();
		final Date currentDate = new Date(currentTime.getNano());

		if (fim == null)
		{
			final Date limitDate = getLimitDate();

			if (limitDate.before(currentDate))
			{
				return true;
			}

		}

		return false;

	}

	@Transient
	public Date getLimitDate()
	{
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(inicio);
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		final Date limitDate = calendar.getTime();

		return limitDate;
	}

	@Transient
	public String getLimitDateString()
	{
		final Date limitDate = getLimitDate();
		final String format = new SimpleDateFormat("dd/MM/yyyy").format(limitDate);

		return format;
	}

	@Transient
	public String getInicioDateString()
	{
		final String format = new SimpleDateFormat("dd/MM/yyyy").format(inicio);

		return format;
	}

}
