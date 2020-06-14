package com.project;

import java.util.Arrays;

import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.project.config.CustomUserDetails;
import com.project.model.Korisnik;
import com.project.repository.KorisnikRepository;
import com.project.service.KorisnikService;

@EnableTransactionManagement
@EnableJpaRepositories
@SpringBootApplication
public class ClinicApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClinicApplication.class, args);
	}
	/*
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder, KorisnikRepository repository, KorisnikService userService) throws Exception {
		if (repository.count()==0)
			userService.save(new Korisnik("milos@gmail.com","milosm","Milos", "Milosevic","Adresa 7","0600000006","AKC"));
			
		builder.userDetailsService(userDetailsService(repository)).passwordEncoder(passwordEncoder);
		System.out.println("hgdsjhgshfgshfgsk");
	}

	
	private UserDetailsService userDetailsService(final KorisnikRepository repository) {
		return username -> new CustomUserDetails(repository.findByEmail(username));
	}
	*/
}
