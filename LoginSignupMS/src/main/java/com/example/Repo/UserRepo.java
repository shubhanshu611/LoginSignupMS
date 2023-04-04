package com.example.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Entity.User;



@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    User findFirstByEmail(String email);

}
