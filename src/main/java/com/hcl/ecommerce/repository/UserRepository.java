package com.hcl.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.ecommerce.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserIdAndPassword(String userId, String password);

	User findByUserId(String userId);
}
