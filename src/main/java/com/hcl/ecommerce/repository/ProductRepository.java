package com.hcl.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hcl.ecommerce.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	Product findByCode(String code);

	@Query("SELECT p FROM Product p WHERE p.code like :searchValue OR p.description like :searchValue")
	List<Product> findProductsBySearchValue(String searchValue);
}
