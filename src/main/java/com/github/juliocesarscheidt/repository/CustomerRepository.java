package com.github.juliocesarscheidt.repository;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.github.juliocesarscheidt.data.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

  // JPQL (JPA query language)
  @Modifying
  @Query("UPDATE Customer c SET c.enabled = false, c.updatedAt = :ts WHERE c.id = :id")
  void disableCustomer(@Param("id") Long id, @Param("ts") Timestamp ts);

  @Modifying
  @Query("UPDATE Customer c SET c.enabled = true, c.updatedAt = :ts WHERE c.id = :id")
  void enableCustomer(@Param("id") Long id, @Param("ts") Timestamp ts);
}
