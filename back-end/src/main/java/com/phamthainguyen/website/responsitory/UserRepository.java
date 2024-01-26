package com.phamthainguyen.website.responsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phamthainguyen.website.model.entity.User;



public interface UserRepository extends JpaRepository<User, Long>{
    User findByEmail(String email);
}
