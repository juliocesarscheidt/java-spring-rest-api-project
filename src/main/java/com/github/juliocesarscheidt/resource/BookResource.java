package com.github.juliocesarscheidt.resource;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.juliocesarscheidt.data.dto.BookDTO;
import com.github.juliocesarscheidt.exception.ServerErrorException;
import com.github.juliocesarscheidt.service.BookService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Book Endpoint", tags = {"Book"})
@RestController
@RequestMapping("/v1/book")
public class BookResource {

	@Autowired
	private BookService bookService;
	
	private Logger logger = LoggerFactory.getLogger(BookResource.class);
	
	private void addLinkTo(BookDTO dto, Long id) {
		try {
			dto.add(linkTo(methodOn(BookResource.class).findOne(id)).withSelfRel());
	
	    } catch (Exception e) {
	      logger.error("Error caught " + e.getMessage());
	      throw new ServerErrorException("Internal Server Error");
	    }
	}

	@ApiOperation(value = "Find All")
	@GetMapping
	  @ResponseStatus(code = HttpStatus.OK)
	  public List<BookDTO> find() throws Exception {
	    List<BookDTO> books = bookService.find();
	    books.stream()
	      .forEach(book -> addLinkTo(book, book.getUniqueId()));
	
	    return books;
	  }
	
	@ApiOperation(value = "Create")
	  @PostMapping
	  @ResponseStatus(code = HttpStatus.CREATED)
	  public BookDTO create(@RequestBody BookDTO book) throws Exception {
		  BookDTO dto = bookService.create(book);
		  addLinkTo(dto, dto.getUniqueId());
	
		  return dto;
	  }
	
	@ApiOperation(value = "Find One")
	  @GetMapping("/{id}")
	  @ResponseStatus(code = HttpStatus.OK)
	  public BookDTO findOne(@PathVariable("id") Long id) throws Exception {
		  BookDTO dto = bookService.findOne(id);
		  addLinkTo(dto, id);
	
		  return dto;
	  }
	
	@ApiOperation(value = "Update")
	  @PutMapping("/{id}")
	  @ResponseStatus(code = HttpStatus.ACCEPTED)
	  public BookDTO update(@PathVariable("id") Long id, @RequestBody BookDTO book) throws Exception {
		  BookDTO dto = bookService.update(id, book);
		  addLinkTo(dto, id);
	
		  return dto;
	  }
	
	@ApiOperation(value = "Delete")
	  @DeleteMapping("/{id}")
	  @ResponseStatus(code = HttpStatus.NO_CONTENT)
	  public ResponseEntity<?> delete(@PathVariable("id") Long id) throws Exception {
		  bookService.delete(id);
	
	    return ResponseEntity.noContent().build();
	  }
}
