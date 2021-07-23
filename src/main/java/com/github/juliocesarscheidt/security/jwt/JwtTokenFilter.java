package com.github.juliocesarscheidt.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.github.juliocesarscheidt.exception.InvalidJwtAuthenticationException;
import com.github.juliocesarscheidt.exception.ServerErrorException;

public class JwtTokenFilter extends GenericFilterBean {

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
    this.jwtTokenProvider = jwtTokenProvider;
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {

    try {
      String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
      System.out.println("Token :: " + token);

      if (token != null && jwtTokenProvider.validateToken(token)) {
        Authentication auth = jwtTokenProvider.getAuthentication(token);
        if (auth != null) {
          SecurityContextHolder.getContext().setAuthentication(auth);
        }
      } else {
        throw new InvalidJwtAuthenticationException("Invalid token");
      }

    } catch (Exception e) {
    throw new InvalidJwtAuthenticationException(e.getMessage());
  }

    try {
    chain.doFilter(request, response);

  } catch (IOException | ServletException e) {
    e.printStackTrace();
    throw new ServerErrorException("Internal server error");
  }
  }
}
