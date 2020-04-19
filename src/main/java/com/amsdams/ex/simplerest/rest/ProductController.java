package com.amsdams.ex.simplerest.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.amsdams.ex.simplerest.rest.errors.BadRequestAlertException;
import com.amsdams.ex.simplerest.rest.util.HeaderUtil;
import com.amsdams.ex.simplerest.rest.util.PaginationUtil;
import com.amsdams.ex.simplerest.rest.util.ResponseUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

	private static final String ENTITY_NAME = "product";

	@Value("${my.clientApp.name:default}")
	private String applicationName;

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@PostMapping("/")
	public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) throws URISyntaxException {
		log.debug("REST request to save Product : {}", productDTO);
		if (productDTO.getId() != null) {
			throw new BadRequestAlertException("A new product cannot already have an ID", ENTITY_NAME, "idexists");
		}
		ProductDTO result = productService.save(productDTO);
		return ResponseEntity.created(new URI("/api/geocities/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(applicationName, ENTITY_NAME, result.getId().toString()))
				.body(result);
	}

	@PutMapping("/")
	public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO) throws URISyntaxException {
		log.debug("REST request to update Product : {}", productDTO);
		if (productDTO.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		ProductDTO result = productService.save(productDTO);
		return ResponseEntity.ok()
				.headers(
						HeaderUtil.createEntityUpdateAlert(applicationName, ENTITY_NAME, productDTO.getId().toString()))
				.body(result);
	}

	@GetMapping("/")
	public ResponseEntity<List<ProductDTO>> getAllProducts(Pageable pageable) {
		log.debug("REST request to get a page of Products");
		Page<ProductDTO> page = productService.findAll(pageable);
		HttpHeaders headers = PaginationUtil
				.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
		return ResponseEntity.ok().headers(headers).body(page.getContent());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> getProduct(@PathVariable Long id) {
		log.debug("REST request to get Product : {}", id);
		Optional<ProductDTO> productDTO = productService.findOne(id);
		return ResponseUtil.wrapOrNotFound(productDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
		log.debug("REST request to delete Product : {}", id);
		productService.delete(id);
		return ResponseEntity.noContent()
				.headers(HeaderUtil.createEntityDeletionAlert(applicationName, ENTITY_NAME, id.toString())).build();
	}

}
