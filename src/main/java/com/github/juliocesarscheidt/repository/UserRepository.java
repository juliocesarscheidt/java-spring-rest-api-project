package com.github.juliocesarscheidt.repository;

import com.github.juliocesarscheidt.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  // JPQL (JPA query language)
  @Query("SELECT u FROM User u WHERE u.userName = :username")
  User findByUserName(@Param("username") String username);
}
