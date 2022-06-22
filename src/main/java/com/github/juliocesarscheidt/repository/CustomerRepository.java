package com.github.juliocesarscheidt.repository;

import com.github.juliocesarscheidt.data.entity.Customer;
import java.sql.Timestamp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

  @Modifying
  @Query("UPDATE Customer c SET c.enabled = false, c.updatedAt = :ts WHERE c.id = :id")
  void disableCustomer(@Param("id") Long id, @Param("ts") Timestamp ts);

  @Modifying
  @Query("UPDATE Customer c SET c.enabled = true, c.updatedAt = :ts WHERE c.id = :id")
  void enableCustomer(@Param("id") Long id, @Param("ts") Timestamp ts);

  @Query(
      value =
          "SELECT * FROM customer WHERE MATCH (first_name) AGAINST (CONCAT('+', :firstName, '*') IN BOOLEAN MODE)",
      nativeQuery = true)
  Page<Customer> findByName(@Param("firstName") String firstName, Pageable pageable);
}
