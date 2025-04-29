package com.infordevtech.dscommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.infordevtech.dscommerce.DscommerceApplication;
import com.infordevtech.dscommerce.dto.ProductDTO;
import com.infordevtech.dscommerce.entities.Product;
import com.infordevtech.dscommerce.repositories.ProductRepository;
import com.infordevtech.dscommerce.services.exception.DatabaseException;
import com.infordevtech.dscommerce.services.exception.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

    private final DscommerceApplication dscommerceApplication;
	
	@Autowired
	private ProductRepository repository;

    ProductService(DscommerceApplication dscommerceApplication) {
        this.dscommerceApplication = dscommerceApplication;
    }
	
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
		Product product = result.orElseThrow(() -> new ResourceNotFoundException("Product not found!"));
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
	
	@Transactional(readOnly = true)
	public Page<ProductDTO> findAll(Pageable pageable) {
		Page<Product> result = repository.findAll(pageable);
		return result.map(x -> new ProductDTO(x));
	}
	
	@Transactional
	public ProductDTO insert(ProductDTO dto) {	
		Product entity = new Product();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);	
		return new ProductDTO(entity);
	}
	
	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		try {
			Product entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new ProductDTO(entity);
		}
		
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Product not found!"); 
		}
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		if(!repository.existsById(id)) {
			throw new ResourceNotFoundException("Product not Found!");
		}
		try {
			repository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Referential integrity failure!");
		}
	}

	private void copyDtoToEntity(ProductDTO dto, Product entity) {
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setPrice(dto.getPrice());
		entity.setImgUrl(dto.getImgUrl());
	}
	
}
