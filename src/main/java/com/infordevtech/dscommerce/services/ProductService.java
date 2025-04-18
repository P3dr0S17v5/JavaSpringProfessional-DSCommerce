package com.infordevtech.dscommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infordevtech.dscommerce.dto.ProductDTO;
import com.infordevtech.dscommerce.entities.Product;
import com.infordevtech.dscommerce.repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	/*
	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		Optional<Product> result = repository.findById(id);  //Recolher info da baseDados
		Product product = result.get();						// converter Optional para Produto
		ProductDTO dto = new ProductDTO(					// Criar DTO com os dados do produto criando 
				product.getId(), product.getName(), product.getDescription(),
				product.getPrice(), product.getImgUrl());
		return dto;
	}
	*/
	
	
	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		Optional<Product> result = repository.findById(id);
		Product product = result.get();
		ProductDTO dto = new ProductDTO(product);
		return dto;								 
	}
	
	/* Funcao resumida
	 * 
	 public ProductDTO findById(Long id) {

		Product product = repository.findById(id).get();						
		return ProductDTO dto = new ProductDTO(product);
	} 
	 */
	 
	public Page<ProductDTO> findAll(Pageable pageable) {
		Page<Product> result = repository.findAll(pageable);
		return result.map(x -> new ProductDTO(x));
	}

}
