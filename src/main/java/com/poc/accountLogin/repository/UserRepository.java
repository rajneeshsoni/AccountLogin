package com.poc.accountLogin.repository;
import org.springframework.data.jpa.repository.JpaRepository;


import com.poc.accountLogin.model.Users;

public interface UserRepository extends JpaRepository<Users, Integer>{

	Users findByUsername(String username);

}
