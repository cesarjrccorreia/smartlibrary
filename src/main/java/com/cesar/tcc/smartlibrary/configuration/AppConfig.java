package com.cesar.tcc.smartlibrary.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.cesar.tcc.smartlibrary.converter.AuthorConverter;
import com.cesar.tcc.smartlibrary.converter.DisciplinaConverter;
import com.cesar.tcc.smartlibrary.converter.EditoraConverter;
import com.cesar.tcc.smartlibrary.converter.RoleToUserProfileConverter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.cesar.tcc.smartlibrary")
public class AppConfig extends WebMvcConfigurerAdapter
{

	@Autowired
	RoleToUserProfileConverter roleToUserProfileConverter;

	@Autowired
	AuthorConverter authorConverter;

	@Autowired
	EditoraConverter editoraConverter;

	@Autowired
	DisciplinaConverter disciplinaConverter;

	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry)
	{
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Override
	public void configureViewResolvers(final ViewResolverRegistry registry)
	{

		final InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/pages/");
		viewResolver.setSuffix(".jsp");
		registry.viewResolver(viewResolver);

	}

	@Bean
	public MessageSource messageSource()
	{
		final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}

	@Override
	public void addFormatters(final FormatterRegistry registry)
	{
		registry.addConverter(roleToUserProfileConverter);
		registry.addConverter(authorConverter);
		registry.addConverter(editoraConverter);
		registry.addConverter(disciplinaConverter);
	}

	@Override
	public void configurePathMatch(final PathMatchConfigurer configurer)
	{
		configurer.setUseRegisteredSuffixPatternMatch(true);
	}

}
