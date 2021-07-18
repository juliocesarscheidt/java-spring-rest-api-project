package com.github.juliocesarscheidt.config;

import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.micrometer.core.instrument.MeterRegistry;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
      .allowedOrigins("*")
      .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT")
      .maxAge(3600);
  }

  // @Override
  // public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
  //   configurer
  //   .favorParameter(false)
  //   .ignoreAcceptHeader(true)
  //   .useRegisteredExtensionsOnly(false)
  //   .defaultContentType(MediaType.APPLICATION_JSON)
  //   .mediaType("json", MediaType.APPLICATION_JSON)
  //   .mediaType("xml", MediaType.APPLICATION_XML);
  // }

  @Bean
  MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
    return registry -> registry.config().commonTags("application", "SpringBoot REST API");
  }
}
