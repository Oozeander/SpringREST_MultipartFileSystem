package com.oozeander.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer {
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();

		// ctx.register(JavaConfig.class);
		ctx.setConfigLocation("com.oozeander.config");

		servletContext.addListener(new ContextLoaderListener(ctx));

		ServletRegistration.Dynamic myServlet = servletContext.addServlet("dispatcherServlet",
				new DispatcherServlet(new GenericWebApplicationContext()));
		myServlet.addMapping("/");
		myServlet.setLoadOnStartup(1);
	}
}