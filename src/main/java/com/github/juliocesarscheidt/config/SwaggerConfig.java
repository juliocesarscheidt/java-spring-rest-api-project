package com.github.juliocesarscheidt.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.TagsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
      .select()
      .apis(RequestHandlerSelectors.basePackage("com.github.juliocesarscheidt"))
      .paths(PathSelectors.any())
      .build()
      .apiInfo(apiInfo())
      .pathMapping("/")
      .tags(new Tag("Restful API", "Restful API with Spring Boot"));
  }

  private ApiInfo apiInfo() {
	// TODO Auto-generated method stub
	return new ApiInfo(
		"Restful API with Spring Boot",
		"Description",
		"v1",
		"terms Of Service Url",
		new Contact("Julio Cesar", "blackdevs.com.br", "julio@blackdevs.com.br"),
		"License",
		"License URL",
		Collections.emptyList()
	);
  }

  @Bean
  public UiConfiguration uiConfig() {
    return UiConfigurationBuilder.builder()
      .deepLinking(true)
      .displayOperationId(false)
      .displayRequestDuration(false)
      .docExpansion(DocExpansion.NONE)
      .filter(false)
      .maxDisplayedTags(null)
      .showExtensions(false)
      .showCommonExtensions(false)
      .tagsSorter(TagsSorter.ALPHA)
      .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
      .validatorUrl(null)
      .build();
  }
}
