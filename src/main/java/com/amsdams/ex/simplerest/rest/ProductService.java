package com.amsdams.ex.simplerest.rest;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

	ProductDTO save(ProductDTO productDTO);

	Page<ProductDTO> findAll(Pageable pageable);

	
	
	Optional<ProductDTO> findOne(Long id);

	void delete(Long id);

}