package com.cesar.tcc.smartlibrary.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//public class AppInitializer implements WebApplicationInitializer {
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { AppConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	// public void onStartup(final ServletContext container) throws
	// ServletException {
	//
	// final AnnotationConfigWebApplicationContext ctx = new
	// AnnotationConfigWebApplicationContext();
	// ctx.register(AppConfig.class);
	// ctx.setServletContext(container);
	//
	// final ServletRegistration.Dynamic servlet =
	// container.addServlet("dispathcer", new DispatcherServlet(ctx));
	// servlet.setLoadOnStartup(1);
	// servlet.addMapping("/");
	//
	// }

}
