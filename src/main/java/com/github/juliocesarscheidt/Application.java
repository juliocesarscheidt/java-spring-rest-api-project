package com.github.juliocesarscheidt;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
@EnableSwagger2
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);

	// encrypts a password with bcrypt
	// BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
	// System.out.println(encoder.encode("password1234"));
  }

  @Bean
  public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
    return args -> {
      // System.out.println("Provided Beans from Spring Boot ::");
      String[] beanNames = ctx.getBeanDefinitionNames();
      Arrays.sort(beanNames);
      // for (String beanName : beanNames) {
      //   System.out.print(beanName + ' ');
      // }
    };
  }
}
