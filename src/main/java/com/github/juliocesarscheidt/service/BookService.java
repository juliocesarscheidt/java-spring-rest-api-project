package com.github.juliocesarscheidt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.github.juliocesarscheidt.data.dto.BookDTO;
import com.github.juliocesarscheidt.data.entity.Book;
import com.github.juliocesarscheidt.data.mapper.DataMapper;
import com.github.juliocesarscheidt.exception.BadRequestException;
import com.github.juliocesarscheidt.exception.EntityNotFoundException;
import com.github.juliocesarscheidt.exception.ServerErrorException;
import com.github.juliocesarscheidt.repository.BookRepository;

@Service
public class BookService extends BaseService {
  @Autowired
  BookRepository repository;

  public BookDTO findOne(Long id) throws Exception {
    Book entity = repository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Book Not Found"));

    return DataMapper.parseObject(entity, BookDTO.class);
  }

  public List<BookDTO> find(Integer page, Integer size) throws Exception {
    Pageable pageable = PageRequest.of(page, size);
    List<Book> books = repository.findAll(pageable).getContent();

    try {
      return DataMapper.parseObjects(books, BookDTO.class);

    } catch (Exception e) {
      logger.error("Error caught " + e.getMessage());
      throw new ServerErrorException("Internal Server Error");
    }
  }

  public BookDTO create(BookDTO book) throws Exception {
    if (!book.validate()) {
      throw new BadRequestException("Invalid Attributes");
    }

    try {
      Book entity = DataMapper.parseObject(book, Book.class);
      
      entity.setCreatedAt(this.getTimestamp());
      
      System.out.println(entity.toString());
      
      return DataMapper.parseObject(repository.save(entity), BookDTO.class);

    } catch (Exception e) {
      logger.error("Error caught " + e.getMessage());
      throw new ServerErrorException("Internal Server Error");
    }
  }

  public BookDTO update(Long id, BookDTO book) throws Exception {
    if (!book.validate()) {
      throw new BadRequestException("Invalid Attributes");
    }

    Book entity = repository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Book Not Found"));

    if (book.getAuthor() != null) entity.setAuthor(book.getAuthor());
    if (book.getLaunchDate() != null) entity.setLaunchDate(book.getLaunchDate());
    if (book.getPrice() != null) entity.setPrice(book.getPrice());
    if (book.getTitle() != null) entity.setTitle(book.getTitle());
    
    entity.setUpdatedAt(this.getTimestamp());

    System.out.println(entity.toString());

    try {
      return DataMapper.parseObject(repository.save(entity), BookDTO.class);

    } catch (Exception e) {
      logger.error("Error caught " + e.getMessage());
      throw new ServerErrorException("Internal Server Error");
    }
  }

  public void delete(Long id) throws Exception {
    Book entity = repository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Book Not Found"));

    try {
      repository.delete(entity);

    } catch (Exception e) {
      logger.error("Error caught " + e.getMessage());
      throw new ServerErrorException("Internal Server Error");
    }
  }
}
