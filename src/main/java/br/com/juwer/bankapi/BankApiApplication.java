package br.com.juwer.bankapi;

import br.com.juwer.bankapi.config.security.JwtAuthenticationFilter;
import br.com.juwer.bankapi.domain.model.Role;
import br.com.juwer.bankapi.domain.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankApiApplication.class, args);
	}
}
