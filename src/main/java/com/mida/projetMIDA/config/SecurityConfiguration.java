package com.mida.projetMIDA.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	protected void configure(HttpSecurity httpSecurity) throws Exception{
		
		httpSecurity.authorizeRequests().antMatchers(
					"/templates/**",
					"/js/**",
					"/css/**",
					"/assets/**"
				).permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login")
		.permitAll()
		.and()
		.logout()
		.invalidateHttpSession(true)
		.clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/login"))
		.logoutSuccessUrl("/").permitAll();
	}
}
