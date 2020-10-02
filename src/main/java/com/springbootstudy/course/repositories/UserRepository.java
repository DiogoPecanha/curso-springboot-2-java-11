package com.springbootstudy.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootstudy.course.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
