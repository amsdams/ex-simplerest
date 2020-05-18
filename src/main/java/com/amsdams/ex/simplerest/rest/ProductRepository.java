package com.amsdams.ex.simplerest.rest;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findAllByPublishOn(LocalDate publishOn);
	List<Product> findAllByUnpublishOn(LocalDate unpublishOn);
	
	List<Product> findAllByArchiveOn(LocalDate now);
	
	List<Product> findAllByStatus(Status status);
}
