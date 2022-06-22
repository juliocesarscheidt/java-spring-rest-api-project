package com.github.juliocesarscheidt.repository;

import com.github.juliocesarscheidt.data.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
  @Query(
      value =
          "SELECT * FROM book WHERE MATCH (author) AGAINST (CONCAT('+', :author, '*') IN BOOLEAN MODE)",
      nativeQuery = true)
  Page<Book> findByAuthor(@Param("author") String author, Pageable pageable);
}
