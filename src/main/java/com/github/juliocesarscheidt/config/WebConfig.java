package com.github.juliocesarscheidt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.http.MediaType;
//import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.micrometer.core.instrument.MeterRegistry;

import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
  @Bean
  MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
	  return registry -> registry.config().commonTags("application", "SpringBoot REST API");
  }
//  
//  @Override
//  public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//    configurer
//      .favorParameter(false)
//      .ignoreAcceptHeader(true)
//      .useRegisteredExtensionsOnly(true)
//      .defaultContentType(MediaType.APPLICATION_JSON);
//  }
}
