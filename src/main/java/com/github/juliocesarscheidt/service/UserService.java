package com.github.juliocesarscheidt.service;

import com.github.juliocesarscheidt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService implements UserDetailsService {
  @Autowired UserRepository repository;

  public UserService(UserRepository repository) {
    this.repository = repository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    var user = repository.findByUserName(username);
    if (user != null) {
      return user;
    } else {
      throw new UsernameNotFoundException("User not found");
    }
  }
}
