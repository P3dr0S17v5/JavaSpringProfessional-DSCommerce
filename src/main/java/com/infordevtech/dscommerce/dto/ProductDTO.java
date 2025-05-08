package com.infordevtech.dscommerce.dto;

import com.infordevtech.dscommerce.entities.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductDTO {
	
	private Long id;
	
	@Size(min = 3, max = 80, message = "Must be between 3 and 80 characters long!")
	@NotBlank(message= "Required Field!")
	private String name;
	
	@Size(min = 10, message = "Must have at least 10 characters!")
	@NotBlank(message= "Required Field!")
	private String description;
	
	@Positive(message= "The Price must be positive!")
	private Double price;
	
	private String imgUrl;
	
	/*
	public ProductDTO(Long id, String name, String description, Double price, String imgUrl) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
	}
	*/

	public ProductDTO(Product entity) {
		id = entity.getId();
		name = entity.getName();
		description = entity.getDescription();	//Contrutor recebe os dados e evita  que o service faça esse serviço
		price = entity.getPrice();
		imgUrl = entity.getImgUrl();
	}
	

	public ProductDTO() {
	}


	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Double getPrice() {
		return price;
	}

	public String getImgUrl() {
		return imgUrl;
	}
	
	
	

}
