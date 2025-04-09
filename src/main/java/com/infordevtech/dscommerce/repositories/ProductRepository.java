package com.infordevtech.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.infordevtech.dscommerce.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	

}
