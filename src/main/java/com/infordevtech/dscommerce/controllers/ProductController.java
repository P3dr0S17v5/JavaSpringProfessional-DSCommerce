package com.infordevtech.dscommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infordevtech.dscommerce.dto.ProductDTO;
import com.infordevtech.dscommerce.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@GetMapping(value = "/{id}")
	public ProductDTO finById(@PathVariable Long id) {
		return service.findById(id);	
	}
	
	@GetMapping
	public Page<ProductDTO> finAll(Pageable pageable) {
		return service.findAll(pageable);
		
	}
}
