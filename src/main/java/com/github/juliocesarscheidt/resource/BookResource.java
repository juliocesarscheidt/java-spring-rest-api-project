package com.github.juliocesarscheidt.resource;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.github.juliocesarscheidt.data.dto.BookDTO;
import com.github.juliocesarscheidt.exception.ServerErrorException;
import com.github.juliocesarscheidt.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Api(
    value = "Book Endpoint",
    tags = {"Book"})
@RestController
@RequestMapping("/v1/book")
public class BookResource extends BaseResource {

  @Autowired private BookService bookService;

  protected Logger logger = LoggerFactory.getLogger(BookResource.class);

  protected void addLinkToDto(RepresentationModel<?> dto, Long id) {
    try {
      dto.add(linkTo(methodOn(BookResource.class).findOne(id)).withSelfRel());
    } catch (Exception e) {
      logger.error("Error caught " + e.getMessage());
      throw new ServerErrorException("Internal Server Error");
    }
  }

  protected Consumer<BookDTO> addLinkConsumer =
      entity -> addLinkToDto(entity, entity.getUniqueId());

  @ApiOperation(value = "Find One")
  @GetMapping(
      value = "/{id}",
      produces = {"application/json"})
  @ResponseStatus(code = HttpStatus.OK)
  public BookDTO findOne(@PathVariable("id") Long id) throws Exception {
    BookDTO dto = bookService.findOne(id);
    addLinkToDto(dto, id);

    return dto;
  }

  @ApiOperation(value = "Find All")
  @GetMapping(produces = {"application/json"})
  @ResponseStatus(code = HttpStatus.OK)
  public ResponseEntity<Page<BookDTO>> find(
      @RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "size", defaultValue = "50") Integer size)
      throws Exception {
    Pageable pageable = PageRequest.of(page, size);
    Page<BookDTO> books = bookService.find(pageable);
    books.stream().forEach(this.addLinkConsumer);

    return ResponseEntity.ok().body(books);
  }

  @ApiOperation(value = "Find by Author")
  @GetMapping(
      value = "/findByAuthor/{author}",
      produces = {"application/json"})
  @ResponseStatus(code = HttpStatus.OK)
  public ResponseEntity<Page<BookDTO>> findByAuthor(
      @PathVariable("author") String author,
      @RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "size", defaultValue = "50") Integer size)
      throws Exception {
    Pageable pageable = PageRequest.of(page, size);
    Page<BookDTO> books = bookService.findByAuthor(author, pageable);
    books.stream().forEach(this.addLinkConsumer);

    return ResponseEntity.ok().body(books);
  }

  @ApiOperation(value = "Create")
  @PostMapping(produces = {"application/json"})
  @ResponseStatus(code = HttpStatus.CREATED)
  public BookDTO create(@RequestBody BookDTO book) throws Exception {
    BookDTO dto = bookService.create(book);
    addLinkToDto(dto, dto.getUniqueId());

    return dto;
  }

  @ApiOperation(value = "Update")
  @PutMapping(
      value = "/{id}",
      produces = {"application/json"})
  @ResponseStatus(code = HttpStatus.ACCEPTED)
  public BookDTO update(@PathVariable("id") Long id, @RequestBody BookDTO book) throws Exception {
    BookDTO dto = bookService.update(id, book);
    addLinkToDto(dto, id);

    return dto;
  }

  @ApiOperation(value = "Delete")
  @DeleteMapping(
      value = "/{id}",
      produces = {"application/json"})
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public ResponseEntity<?> delete(@PathVariable("id") Long id) throws Exception {
    bookService.delete(id);

    return ResponseEntity.noContent().build();
  }
}
