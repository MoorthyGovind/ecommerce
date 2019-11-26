package com.hcl.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.ecommerce.entity.StoreProduct;

public interface StoreProductRepository extends JpaRepository<StoreProduct, Long> {

	StoreProduct findByStoreIdMobileNoAndProductIdCode(String mobileNo, String code);
}
