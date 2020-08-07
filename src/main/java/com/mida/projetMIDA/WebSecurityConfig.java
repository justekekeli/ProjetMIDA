package com.mida.projetMIDA;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mida.projetMIDA.services.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserService();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth=new DaoAuthenticationProvider();
		auth.setPasswordEncoder(passwordEncoder());
		auth.setUserDetailsService(userDetailsService());
		return auth;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/Utilisateurs/**").hasAuthority("ADMIN")
			.antMatchers("/Clients/**").hasAnyAuthority("ADMIN","AGENT")
			.antMatchers("/Avocat/**").hasAnyAuthority("ADMIN","SECRETAIRE")
			.antMatchers("/Promesse/**").hasAnyAuthority("ADMIN","AGENT")
			.antMatchers("/Immeuble/**").hasAnyAuthority("ADMIN","SECRETAIRE")
			.antMatchers("/Appartement/**").hasAnyAuthority("ADMIN","SECRETAIRE")
			.antMatchers("/Visite/**").hasAnyAuthority("ADMIN","AGENT")
			.anyRequest().authenticated()
			.and()
			.formLogin().loginPage("/login").defaultSuccessUrl("/").failureUrl("/login?error").permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/403");
	}
	
	
}
