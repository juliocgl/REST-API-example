package com.example.api.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.api.security.JwtAuthenticationFilter;
import com.example.api.security.JwtLoginFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String ROLE_ADMIN = "ADMIN";
	private static final String ROLE_USER = "USER";

	private static final String ACCOUNT_ADMIN = "admin@admin.com";
	private static final String ACCOUNT_USER = "user@user.com";

	private static final String PASSWORD_ADMIN = "admin123";
	private static final String PASSWORD_USER = "user 123";

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser(ACCOUNT_ADMIN).password(PASSWORD_ADMIN).roles(ROLE_ADMIN);
		auth.inMemoryAuthentication().withUser(ACCOUNT_USER).password(PASSWORD_USER).roles(ROLE_USER);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http

				.csrf().disable()

				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

				.authorizeRequests().antMatchers("/", "/js/**", "/favicon.ico").permitAll()

				.antMatchers(HttpMethod.POST, "/login").permitAll()

				.anyRequest().authenticated().and()

				.addFilterBefore(new JwtLoginFilter("/login", authenticationManager()),
						UsernamePasswordAuthenticationFilter.class)

				.addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)

				.headers().cacheControl();
	}

}