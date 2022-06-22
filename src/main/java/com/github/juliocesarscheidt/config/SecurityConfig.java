package com.github.juliocesarscheidt.config;

import com.github.juliocesarscheidt.security.jwt.JwtTokenFilter;
import com.github.juliocesarscheidt.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired private JwtTokenProvider jwtTokenProvider;

  public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
    this.jwtTokenProvider = jwtTokenProvider;
  }

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

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring()
        .antMatchers(
            "/v1/auth/**",
            "/actuator/**",
            "/v2/api-docs/**",
            "/swagger-resources/**",
            "/swagger-ui/**");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    JwtTokenFilter customFilter = new JwtTokenFilter(jwtTokenProvider);

    http.httpBasic()
        .disable()
        .csrf()
        .disable()
        .addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class)
        .authorizeRequests()
        .antMatchers(
            "/v1/auth/**",
            "/actuator/**",
            "/v2/api-docs/**",
            "/swagger-resources/**",
            "/swagger-ui/**")
        .permitAll()
        .anyRequest()
        .authenticated();
  }
}
