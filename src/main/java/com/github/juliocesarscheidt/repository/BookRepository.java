package com.github.juliocesarscheidt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.juliocesarscheidt.data.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {}
