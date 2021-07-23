package com.github.juliocesarscheidt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.github.juliocesarscheidt.data.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	// JPQL (JPA query language)
	@Query("SELECT u FROM User u WHERE u.userName = :username")
	User findByUserName(@Param("username") String username);
}
