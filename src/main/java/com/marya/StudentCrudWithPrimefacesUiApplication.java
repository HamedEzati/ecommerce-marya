package com.marya;

import org.primefaces.webapp.filter.FileUploadFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.ServletContextAware;

import javax.faces.webapp.FacesServlet;
import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;

@SpringBootApplication
@Configuration
@ComponentScan("com.marya.*")
@EnableJpaRepositories("com.marya.repository")
@EntityScan("com.marya.entity")
@EnableTransactionManagement
@EnableAsync
public class StudentCrudWithPrimefacesUiApplication implements ServletContextAware{

	public static void main(String[] args) {
		SpringApplication.run(StudentCrudWithPrimefacesUiApplication.class, args);
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		servletContext.setInitParameter("facelets.DEVELOPMENT", Boolean.TRUE.toString());
		servletContext.setInitParameter("javax.faces.DEFAULT_SUFFIX", ".xhtml");
		servletContext.setInitParameter("javax.faces.PROJECT_STAGE", "Development");
		servletContext.setInitParameter("javax.faces.FACELETS_REFRESH_PERIOD", "1");
		servletContext.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", Boolean.TRUE.toString());
		servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
		servletContext.setInitParameter("primefaces.CLIENT_SIDE_VALIDATION", Boolean.TRUE.toString());
		servletContext.setInitParameter("primefaces.THEME", "ui-lightness");
		servletContext.setInitParameter("primefaces.UPLOADER", "commons");
		servletContext.setInitParameter("primefaces.MOVE_SCRIPTS_TO_BOTTOM", Boolean.TRUE.toString());
	}

	@Bean
	public ServletRegistrationBean<FacesServlet> facesServletServletRegistrationBean() {
		ServletRegistrationBean<FacesServlet> servletRegistrationBean = new ServletRegistrationBean(new FacesServlet(), "*.xhtml");
		servletRegistrationBean.setLoadOnStartup(1);
		servletRegistrationBean.setName("Faces Servlet");
		return servletRegistrationBean;
	}

	@Bean
	public FilterRegistrationBean primeFacesFileUploadFilter(@Qualifier("facesServletServletRegistrationBean") @Autowired ServletRegistrationBean<FacesServlet> facesServletServletRegistrationBean) {
		FilterRegistrationBean registration = new FilterRegistrationBean(new FileUploadFilter(), facesServletServletRegistrationBean);
		registration.setName("primeFacesFileUploadFilter");
		registration.addUrlPatterns("/*");
		registration.setDispatcherTypes(DispatcherType.FORWARD, DispatcherType.REQUEST);
		return registration;
	}

	@Bean
	public FacesServlet facesServlet() {
		return new FacesServlet();
	}

	@Bean
	public ServletRegistrationBean<FacesServlet> facesServletServletRegistrationBean(@Autowired FacesServlet facesServlet) {
		ServletRegistrationBean<FacesServlet> servletRegistrationBean = new ServletRegistrationBean(facesServlet, "*.xhtml");
		servletRegistrationBean.setLoadOnStartup(1);
		servletRegistrationBean.setName("Faces Servlet");
		return servletRegistrationBean;
	}
}
