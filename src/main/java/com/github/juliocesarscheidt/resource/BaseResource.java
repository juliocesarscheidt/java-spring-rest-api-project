package com.github.juliocesarscheidt.resource;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.RepresentationModel;

import com.github.juliocesarscheidt.exception.ServerErrorException;

public class BaseResource {

  protected Logger logger = LoggerFactory.getLogger(BookResource.class);

  protected void addLinkTo(RepresentationModel<?> dto, Long id) {
    try {
      dto.add(linkTo(methodOn(CustomerResource.class).findOne(id)).withSelfRel());

    } catch (Exception e) {
      logger.error("Error caught " + e.getMessage());
      throw new ServerErrorException("Internal Server Error");
    }
  }
}
