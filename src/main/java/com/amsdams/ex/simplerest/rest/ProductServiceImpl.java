package com.amsdams.ex.simplerest.rest;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
		this.productRepository = productRepository;
		this.productMapper = productMapper;
	}

	private final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

	private final ProductRepository productRepository;

	private final ProductMapper productMapper;
	


	@Override
	public ProductDTO save(ProductDTO productDTO) {
		log.debug("Request to save Product : {}", productDTO);
		Product product = productMapper.toEntity(productDTO);
		product = productRepository.save(product);
		ProductDTO result = productMapper.toDto(product);
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<ProductDTO> findAll(Pageable pageable) {
		log.debug("Request to get all Geocities");
		return productRepository.findAll(pageable).map(productMapper::toDto);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<ProductDTO> findOne(Long id) {
		log.debug("Request to get Product : {}", id);
		return productRepository.findById(id).map(productMapper::toDto);
	}

	@Override
	public void delete(Long id) {
		log.debug("Request to delete Product : {}", id);
		productRepository.deleteById(id);
	}

}
