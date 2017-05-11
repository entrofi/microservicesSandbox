package net.entrofi.microservices.sandbox.fms;

import net.entrofi.microservices.sandbox.fms.config.FMSInitializerService;
import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;


@SpringBootApplication
@EnableGlobalMethodSecurity
@EnableAuthorizationServer
@EnableResourceServer
public class FMSApplication implements CommandLineRunner {

    @Autowired
	private FMSInitializerService fmsInitializerService;

    public static void main(String[] args) {
		SpringApplication.run(FMSApplication.class, args);
	}

	@Bean
	ServletRegistrationBean h2servletRegistration(){
		ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
		registrationBean.addUrlMappings("/console/*");
		return registrationBean;
	}


	@Override
	public void run(String... strings) throws Exception {
        fmsInitializerService.createFlights(30);
	}
}


