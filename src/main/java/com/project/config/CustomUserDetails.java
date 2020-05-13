package com.project.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.project.model.*;

/**
 * Provides a basic implementation of the UserDetails interface
 */
public class CustomUserDetails implements UserDetails {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Collection<? extends GrantedAuthority> authorities;
    private String password;
    private String username;

    public CustomUserDetails(Korisnik user) {
        this.username = user.getEmail();
        this.password = user.getLozinka();
        if(user instanceof Pacijent)
        	this.authorities = translate("P");
        else if( user instanceof Lekar)
        	this.authorities = translate("L");
        else if( user instanceof MedicinskaSestra)
        	this.authorities = translate("MS");
        else if( user instanceof AdminKlinike)
        	this.authorities = translate("AK");
        else
        	this.authorities = translate("AKC");

    }

    
    private Collection<? extends GrantedAuthority> translate(String role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        
            //Make sure that all roles start with "ROLE_"
        if (!role.startsWith("ROLE_"))
        	role = "ROLE_" + role;
        authorities.add(new SimpleGrantedAuthority(role));
        
        return authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
