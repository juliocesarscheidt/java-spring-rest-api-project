package com.github.juliocesarscheidt.resource;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.juliocesarscheidt.repository.UserRepository;
import com.github.juliocesarscheidt.security.AccountCredentialsDTO;
import com.github.juliocesarscheidt.security.jwt.JwtTokenProvider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Auth Endpoint", tags = {"Auth"})
@RestController
@RequestMapping("/v1/auth")
public class AuthResource {

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Autowired
  UserRepository repository;

  @ApiOperation(value = "Signin")
  @PostMapping(value = "/signin", produces = {"application/json"})
  @ResponseStatus(code = HttpStatus.OK)
  public ResponseEntity<?> signin(@RequestBody AccountCredentialsDTO data) throws Exception {
    try {
      var username = data.getUsername();
      System.out.println("username :: " + username);

      var password = data.getPassword();
      System.out.println("password :: " + password);

      authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(username, password));

      var user = repository.findByUserName(username);
      var token = "";

      if (user != null) {
        token = jwtTokenProvider.createToken(username, user.getRoles());
      } else {
        throw new UsernameNotFoundException("Username + " + username + " not found");
      }

      Map<Object, Object> model = new HashMap<>();

      model.put("username", username);
      model.put("token", token);

      return ResponseEntity.ok(model);

    } catch (AuthenticationException e) {
      throw new BadCredentialsException("Invalid username or password");
    }
  }
}
