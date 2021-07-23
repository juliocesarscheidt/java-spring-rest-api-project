package com.github.juliocesarscheidt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.github.juliocesarscheidt.security.jwt.JwtConfigurer;
import com.github.juliocesarscheidt.security.jwt.JwtTokenProvider;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		// creates a encoder for bcrypt passwords
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		return encoder;
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	protected void configure(HttpSecurity http) throws Exception {
		http
			.httpBasic().disable()
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.authorizeRequests()
				.antMatchers("/v1/auth/**", "/actuator/**", "/v2/api-docs/**", "/swagger-ui/**").permitAll()
				.antMatchers("/v1/customer/**", "/v1/book/**").authenticated()
			.and()
			.apply(new JwtConfigurer(jwtTokenProvider));
	}
}
