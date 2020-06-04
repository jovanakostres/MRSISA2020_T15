package com.project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;


@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
        		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/","/favicon.ico","/api/registracija","/registracijaPacijenta","/lekarPocetna","/pacijentPocetna","/akcPocetna","/Izvestaj/**","/izmena_izvestaja/**").permitAll()
                .antMatchers("/api/lekar/**").hasAuthority("ROLE_L")
                .antMatchers("/api/akc/**").hasAuthority("ROLE_AKC")
                .antMatchers("/api/potvrdiRegistraciju").hasAuthority("ROLE_AKC")
                .antMatchers("/api/odbijRegistraciju").hasAuthority("ROLE_AKC")
                .antMatchers("/api/pregled/**").hasAuthority("ROLE_L")
                .antMatchers("/api/dijagnoza/**").hasAnyAuthority("ROLE_L","ROLE_AKC")
                .antMatchers("/api/lek/**").hasAuthority("ROLE_AKC")
                .antMatchers("/api/izvestaj/**").hasAuthority("ROLE_L")
                .antMatchers("/api/pacijent/**").hasAuthority("ROLE_P")
        		.anyRequest()
        		.authenticated();
        		//antMatchers("/lekarPocetna/**").authenticated();
        		//.antMatchers("/lekarPocetna/**").hasAuthority("ROLE_L");
                //.antMatchers("/post").authenticated()
                //.antMatchers("/post/postComment").authenticated()
                //.antMatchers(HttpMethod.DELETE , "/post/**").hasAuthority("ROLE_ADMIN");
        
    }


}
