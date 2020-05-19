package com.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.project.repository.KorisnikRepository;
import com.project.service.KorisnikService;

@Configuration
@EnableWebSecurity
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class ServerSecurityConfig extends WebSecurityConfigurerAdapter {
   
	@Autowired
    private UserDetailsService userDetailsService;
    
	@Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Autowired
    private KorisnikRepository repository;
    
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	//KorisnikRepository rep;
    	auth.userDetailsService(userDetailsService(repository)).passwordEncoder(encoder());
        //auth.userDetailsService(userDetailsService(rep)).passwordEncoder(userPasswordEncoder);
    	//authenticationManager();
    }
    
    @Override
	public void configure(WebSecurity web) throws Exception {
		// TokenAuthenticationFilter ce ignorisati sve ispod navedene putanje
		web.ignoring().antMatchers(HttpMethod.GET, "/", "/webjars/**", "/*.html", "/favicon.ico", "/**/*.html",
				"/**/*.css", "/**/*.js");
	}
    
    @Bean
    public UserDetailsService userDetailsService(KorisnikRepository repository) {
		return username -> new CustomUserDetails(repository.findByEmail(username));
	}
   
}